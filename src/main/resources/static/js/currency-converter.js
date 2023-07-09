window.onload = function () {
    alert("Something")
}
function getAllCurrencies() {
    var from = document.getElementById("fromInput");
    var to = document.getElementById("toInput");
    var xmlRequest = new XMLHttpRequest();

    xmlRequest.onreadystatechange = function() {
        if(this.readyState === 4 && this.status === 200) {
            var jsonParsed = JSON.parse(this.responseText);
            fillCurrency(from, jsonParsed);
            fillCurrency(to, jsonParsed);
        }
    };
    xmlRequest.open("GET", "http://localhost:8080/currencies/all", true);
    xmlRequest.send();
}

function fillCurrency( dropdown,jsonParsed) {
    for (let i = 0; i < jsonParsed.length; i++) {
        var option = document.createElement("option");
        option.setAttribute("value", jsonParsed[i].name);
        option.innerText = jsonParsed[i].name;
        dropdown.appendChild(option);
    }
}