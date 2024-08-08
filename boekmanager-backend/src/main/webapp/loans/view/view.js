import LoanService from "../loanService.js";

let service = new LoanService()

let promise = service.getLoans()
let box = document.getElementById("loans")

promise.then(json => {
    for (let index in json) {
        let loan = json[index]
        let loanBox = document.createElement("div")
        loanBox.innerHTML = "<p>" + loan.book.title + "</p> " +
            "<p>" + loan.book.author.lastName + ", " + loan.book.author.firstName + " " + loan.book.author.middleName + "</p>" +
            "<p>Geleend door:</p>" +
            "<p>" + loan.loaner.name.firstName + " " + loan.loaner.name.middleName + " " + loan.loaner.lastName + "</p>"
        box.appendChild(loanBox)
    }
})