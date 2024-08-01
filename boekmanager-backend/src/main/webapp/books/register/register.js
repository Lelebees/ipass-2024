import BookService from "../bookService.js";

let service = new BookService()
document.forms.registerBook.addEventListener('submit', e => {
    e.preventDefault();
    let form = document.forms.registerBook
    service.registerBook(
        form.titleInput.value,
        form.ISBNInput.value,
        form.authorFirstNameInput.value,
        form.authorMiddleNameInput.value,
        form.authorLastNameInput.value,
        form.notesInput.value
    ).then(response =>
        console.log("gelukt!")
    )
})