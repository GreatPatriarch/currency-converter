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
    xmlRequest.open("GET", "/currencies/all", true);
    xmlRequest.send();
}

function fillCurrency( dropdown,jsonParsed) {
    for (let i = 0; i < jsonParsed.length; i++) {
        var option = document.createElement("option");
        option.setAttribute("value", jsonParsed[i].name);
        option.innerText = jsonParsed[i].name;
        dropdown.appendChild(option);
    }

    $('.selectpicker').selectpicker('refresh');
}

$("#convertButton").click(function() {

    var from = $('#fromInput').val();
    var to = $('#toInput').val();
    var value = $('#amountInput').val();

    // Make the AJAX call
    $.ajax({
        type: "POST",
        url: "/currencies/convert",
        data: JSON.stringify({
            "from": from,
            "to": to,
            "value": value
        }),
        contentType: "application/json;",
        success: function (data) {
            $("#convertResult").val(data);
        },
        error: function (data) {
            alert('There was a problem converting');
        }
    });
});

