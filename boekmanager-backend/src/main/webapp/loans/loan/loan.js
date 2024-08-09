import LoanService from "../loanService.js";
import LoginService from "../../loginService.js";

let loanService = new LoanService()
let loginService = new LoginService()
let selectBook = document.getElementById("bookInput")

let selectLoaner = document.getElementById("loanerInput")
let form = document.forms.loanBook
form.addEventListener('submit', e => {
    e.preventDefault()
    loanService.loanBook(
        form.bookInput.value,
        form.loanerInput.value,
        form.loanDate.value,
        form.returnDate.value
    ).then(r => console.log("gelukt!"))
})
fetch(window.location.origin + "/api/books", {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + loginService.getCookie("token")
        }
    }
).then(response => {
    if (response.ok) {
        return response.json()
    }
    console.log("Not good! " + response.status + ": " + response.statusText)
})
    .then(json => {
        for (const jsonKey in json) {
            let book = json[jsonKey]
            let option = document.createElement('option')
            option.value = book.id
            option.innerHTML = book.title + " - " + book.author.lastName + ", " + book.author.firstName + " " + book.author.middleName
            selectBook.appendChild(option)
        }
    })

fetch(window.location.origin + "/api/loaners", {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + loginService.getCookie("token")
        }
    }
).then(response => {
    if (response.ok) {
        return response.json()
    }
    throw new err
})
    .then(json => {
        for (const jsonKey in json) {
            let loaner = json[jsonKey]
            let option = document.createElement('option')
            option.value = loaner.id
            option.innerHTML = loaner.name.firstName + " " + loaner.name.middleName + " " + loaner.name.lastName
            selectLoaner.appendChild(option)
        }
    })