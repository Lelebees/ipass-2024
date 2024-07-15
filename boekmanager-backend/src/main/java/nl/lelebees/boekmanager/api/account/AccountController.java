package nl.lelebees.boekmanager.api.account;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import nl.lelebees.boekmanager.domain.security.Account;
import nl.lelebees.boekmanager.security.CredentialHolder;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.security.Key;
import java.util.AbstractMap;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Path("/authentication")
public class AccountController {
    public static final Key key = MacProvider.generateKey();

    private String createToken(String username, String role) throws JwtException {
        Calendar expiration = Calendar.getInstance();
        expiration.add(Calendar.MINUTE, 38);

        return Jwts.builder()
                .setSubject(username)
                .setExpiration(expiration.getTime())
                .claim("role", role)
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"admin", "user"})
    public Response getAccount(@Context SecurityContext securityContext)
    {
        if (securityContext.getUserPrincipal() instanceof Account currentAccount)
        {
            return Response.ok().entity(currentAccount).build();
        }
        Map<String, String> errMsg = new HashMap<>();
        errMsg.put("error", "The account was not an instance of Account");
        return Response.status(500).entity(errMsg).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authenticateAccount(CredentialHolder desiredAccount)
    {
        try{
            String role = Account.validateLogin(desiredAccount.username, desiredAccount.password);
            if (role == null) throw new IllegalArgumentException("No user found");

            String token = createToken(desiredAccount.username, role);
            return Response.ok(new AbstractMap.SimpleEntry<>("JWT", token)).build();
        }
        catch (JwtException | IllegalArgumentException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}
