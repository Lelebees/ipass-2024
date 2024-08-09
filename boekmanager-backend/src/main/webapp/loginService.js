export default class LoginService {

    login(username, password) {
        return fetch(window.location.origin + "/api/authentication", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                username: username,
                password: password
            })
        }).then(response => {
            if (response.ok) {
                return response.json()
            }
            console.log("Failed to log in: " + response.status + " " + response.statusText + " | " + response)
        })
    }

    setCookie(name, value, days = 7, path = '/') {
        const expires = new Date(Date.now() + days * 864e5).toUTCString()
        document.cookie = name + '=' + encodeURIComponent(value) + '; expires=' + expires + '; path=' + path
    }

    getCookie(name) {
        return document.cookie.split('; ').reduce((r, v) => {
            const parts = v.split('=')
            return parts[0] === name ? decodeURIComponent(parts[1]) : r
        }, '')
    }

    deleteCookie(name, path = '/') {
        this.setCookie(name, '', -1, path)
    }

}