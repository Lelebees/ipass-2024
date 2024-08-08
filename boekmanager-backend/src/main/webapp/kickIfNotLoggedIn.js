import LoginService from "./loginService.js";

let service = new LoginService()

if (service.getCookie("token") == null || service.getCookie("token") === ""){
    window.location.replace(window.location.origin + "/tomcat_app/login")
}