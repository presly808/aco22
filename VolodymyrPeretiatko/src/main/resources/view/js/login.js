
    function sendLoginForm() {

        var nameValue = document.getElementById("login").value;
        var passwordValue = document.getElementById("password").value;

        var keyValueObj = {name: nameValue, password: passwordValue};

        // construct an HTTP request
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "http://localhost:8000/login", true);
        xhr.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');

        // send the collected data as JSON
        xhr.send(JSON.stringify(keyValueObj));

        xhr.onloadend = function (resp) {
            // get access token and put in cookies
            console.log("req has been sucessful\n" + resp)
        };
        //window.location.replace("/view/html/user-page.html");
    }