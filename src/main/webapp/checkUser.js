$(document).ready(function () {
    $.ajax({
        type: 'GET',
        crossdomain: true,
        url: 'http://localhost:8080/job4j_cars/auth.do',
    }).done(function () {
        $('#add').append('<p><button><a href="upload">Добавить объявление</a></button></p>')
    }).fail(function () {
        $('#add').html(' ')
    });
});