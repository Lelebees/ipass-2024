import LoginService from "../loginService.js";

export default class BookService {
    loginService = new LoginService()


    registerBook(title, ISBN, firstName, middleName, lastName, notes) {
        return fetch("../../api/books", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + this.loginService.getCookie("token")
            },
            body: JSON.stringify({
                title: title,
                ISBN: ISBN,
                author: {
                    firstName: firstName,
                    middleName: middleName,
                    lastName: lastName
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