export default class LoanerService {
    registerLoaner(firstName, middleName, lastName, email, phoneNumber, houseNumber, streetName, townName, country, postalCode, notes) {
        return fetch("../api/loaners", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
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