
function getBillById() {

    var id = document.getElementById("billId").value;

    // construct an HTTP request
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "http://localhost:8000/getBill?id=" + id, true);
    xhr.send();

    // response
    xhr.onloadend = function (resp) {

        var res = xhr.responseText;
        console.log(res)

    };
}