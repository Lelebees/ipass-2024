import LoanerService from "../loanerService.js";

let service = new LoanerService();

document.forms.registerLoaner.addEventListener('submit', e => {
        e.preventDefault();
        let form = document.forms.registerLoaner;
        service.registerLoaner(
            form.firstNameInput.value,
            form.middleNameInput.value,
            form.lastNameInput.value,
            form.emailInput.value,
            form.phoneInput.value,
            form.houseNumberInput.value,
            form.streetInput.value,
            form.townInput.value,
            form.countryInput.value,
            form.postalCodeInput.value,
            form.notesInput.value
        ).then(response =>
            console.log("gelukt!")
        )
    }
);