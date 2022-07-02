/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
var websocket;
$(document).ready(function () {
    $(".body").css('height', ($(window).height()) + "px");
    var y = window.location.protocol === 'https:' ? 'wss://' :
            'ws://';
    websocket = new WebSocket(y + document.location.host +
            "/mavenproject1/wsSms");
    websocket.onopen = onOpen;
    websocket.onmessage = onMessage;
    websocket.onerror = onError;
    websocket.onclose = onClose;

    $('#btn-send').on('click', function (event) {
        if ($('#sms-send').val() === "")
        {
            alert("ingrese mensaje");
        } else if ($('#name').val() === "") {
            alert("ingrese nombre");
        } else {
            console.log('entro');
            websocket.send(JSON.stringify({nombre: $('#name').val(),
                mensaje: $('#sms-send').val()}));
        }
    });
});
function onOpen(evt) {
    console.log("conectado");
}
function onMessage(evt) {
    console.log('recibe');
    console.log(evt.data);
    data = JSON.parse(evt.data);
    var sms = '<div class="box-sms"> <div class="conatainer-sms"> <p class="name">' 
            + data.nombre + ':</p> <p class = "sms"> ' +
            data.mensaje + '</p></div></div>';
    if ($('.arriba').children().length > 0) {
        $('.arriba').children(':last').after(sms);
    } else {
        $('.arriba').append(sms);
    }
}
function onError(evt) {
    console.log(evt);
}
function onClose(evt) {
    console.log("desconectado");
}


