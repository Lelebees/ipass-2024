import LoginService from "./loginService.js";

let loginService = new LoginService()
document.getElementById("logoutButton").addEventListener('click', () => {
    loginService.deleteCookie("token")
    window.location.reload()
})