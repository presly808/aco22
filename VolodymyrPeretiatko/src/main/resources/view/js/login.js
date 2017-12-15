function sendLoginForm() {

    var nameVal = document.getElementById("login").value;
    var passVal = document.getElementById("password").value;

    var keyValueObj = {name: nameVal, password: passVal};

    // construct an HTTP request
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8000/login", true);
    xhr.setRequestHeader("Content-Type", "application/json; charset=UTF-8");

    // send the collected data as JSON
    xhr.send(JSON.stringify(keyValueObj));

    // response
    xhr.onloadend = function (resp) {

        var res = xhr.responseText;
        //console.log(nameVal + " is logged - " + res)

        if (res === "false") {
            var element = document.getElementById("result");
            element.src = "pics/faild.jpg";
        }

    };
}