import LoginService from "../loginService.js";

let loginForm = document.forms.login
let service = new LoginService()
loginForm.addEventListener('submit', e => {
    e.preventDefault()
    service.login(loginForm.usernameInput.value, loginForm.passwordInput.value)
        .then(json => {
            service.setCookie("token", json.JWT)
        }).then(() => window.location.replace("../"))
})