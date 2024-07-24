export default class RegisterBookService {
    registerBook(title, ISBN, firstName, middleName, lastName, notes) {
        return fetch("../api/books", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
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