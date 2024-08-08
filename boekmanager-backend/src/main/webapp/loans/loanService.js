import LoginService from "../loginService.js";

export default class LoanService {
    loginService = new LoginService();
    loanBook(book, loaner, loanDate, returnDate) {
        return fetch("../../api/loans", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + this.loginService.getCookie("token")
            },
            body: JSON.stringify({
                bookId: book,
                loanerId: loaner,
                loanedAt: loanDate,
                toReturnAt: returnDate
            })
        })
    }

    getLoans() {
        return fetch("../api/loans", {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + this.loginService.getCookie("token")
            }
        }).then(response => {
            console.log(response)
            if (response.ok) {
                return response.json()
            }
            console.log("ERROR!")
        })
    }
}