import LoanService from "../loanService.js";

let service = new LoanService()

let promise = service.getLoans()
let box = document.getElementById("loans")

promise.then(json => {
    for (let index in json) {
        let loan = json[index]
        let loanBox = document.createElement("div")
        loanBox.className = "loanBox"
        loanBox.innerHTML = `<div class="loanInfo">
            <p class='bookTitle'>${loan.book.title}</p>
            <p class='author'>${loan.book.author.lastName}, ${loan.book.author.firstName} ${loan.book.author.middleName}</p>
            <p>Geleend door:</p>
            <p class='loaner'>${loan.loaner.name.firstName} ${loan.loaner.name.middleName} ${loan.loaner.name.lastName}</p>
            </div>
            <button id="return-${loan.id}">Neem In</button>
`
        box.appendChild(loanBox)
        document.getElementById("return-" + loan.id).addEventListener('click', e => {
            console.log("gelukt!")
            service.returnBook(loan.id).then(() => location.reload())
        })
    }
})