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
        return fetch("../../api/loans", {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + this.loginService.getCookie("token")
            }
        }).then(response => {
            if (response.ok) {
                return response.json()
            }
            console.log("ERROR! " + response.status + ": " + response.statusText)
        })
    }

    returnBook(loanId) {
        return fetch("../../api/loans/" + loanId, {
            method: "DELETE",
            headers: {
                "Authorization": "Bearer " + this.loginService.getCookie("token")
            }
        }).then(response => {
            if (response.ok) {
                return response.body
            }
            console.log("Error deleting loan " + loanId + ". API provided unexpected response " + response.status + " " + response.text())
            console.log(response)
        })
    }
}