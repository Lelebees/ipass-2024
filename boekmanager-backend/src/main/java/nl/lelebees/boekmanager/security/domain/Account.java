package nl.lelebees.boekmanager.security.domain;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


public class Account implements Principal {
    static List<Account> allUsers = new ArrayList<>();
    private final String username;
    private final String password;
    private final String role;

    public Account(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
        if (!allUsers.contains(this)) {
            allUsers.add(this);
        }
    }

    public static Account getAccountByName(String userName) {
        for (Account account : allUsers) {
            if (account.getUsername().equals(userName)) {
                return account;
            }
        }
        return null;
    }

    public static String validateLogin(String username, String password) {
        Account selectedUser = getAccountByName(username);
        if (selectedUser == null || !selectedUser.password.equals(password)) {
            return null;
        }
        return selectedUser.getRole();
    }

    public String getUsername() {
        return this.username;
    }

    public String getRole() {
        return this.role;
    }

    @Override
    public String getName() {
        return this.username;
    }

}
