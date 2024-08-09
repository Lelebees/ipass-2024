import LoginService from "../loginService.js";

export default class LoanerService {
    loginService = new LoginService();
    registerLoaner(firstName, middleName, lastName, email, phoneNumber, houseNumber, streetName, townName, country, postalCode, notes) {
        return fetch(window.location.origin +"/api/loaners", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + this.loginService.getCookie("token")
            },
            body: JSON.stringify({
                name: {
                    firstName: firstName,
                    middleName: middleName,
                    lastName: lastName
                },
                email: email,
                phoneNumber: phoneNumber,
                address: {
                  houseNumber: houseNumber,
                  streetName: streetName,
                  townName: townName,
                  country: country,
                  postalCode: postalCode
                },
                notes: notes
            })
        })
            .then(function (response) {
                if (response.ok) return response.json();
                else throw "something went wrong! status:" + response.status;
            })
            .catch(error => console.log(error))
    }
}