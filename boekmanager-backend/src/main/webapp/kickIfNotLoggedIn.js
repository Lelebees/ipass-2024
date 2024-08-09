import LoginService from "./loginService.js";

let service = new LoginService()

if (service.getCookie("token") == null || service.getCookie("token") === "") {
    window.location.replace(window.location.origin + "/login")
}

fetch(window.origin +"/api/authentication", {
    method: "GET",
    headers: {
        Authorization: "Bearer " + service.getCookie("token")
    }
}).then(response => {
    if (response.status !== 200) {
        console.log(response)
        service.deleteCookie("token", "/")
        window.location.replace(window.location.origin + "/login")
    }
})