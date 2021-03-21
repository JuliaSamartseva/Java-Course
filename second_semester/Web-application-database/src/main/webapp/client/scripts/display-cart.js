window.onload = async () => {
    document.getElementById('logout').onclick = async function () {
        await fetch(`../servlets/logout`, {
            method: 'POST'
        });
        window.location = "http://localhost:8080/Web_application_database_war/login.jsp"
    }
}