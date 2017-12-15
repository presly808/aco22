
function getBillById() {

    var id = document.getElementById("billId").value;

    // construct an HTTP request
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "http://localhost:8000/getBill?id=" + id, true);
    xhr.send();

    // response
    xhr.onloadend = function (resp) {
        var res = xhr.responseText;

        var p = document.createElement("p");
        var node = document.createTextNode("" + res);
        p.appendChild(node);

        var div = document.getElementById("bill");
        div.appendChild(p);

        console.log(resp + res);
    };
}