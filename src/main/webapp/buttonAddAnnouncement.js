$(document).ready(function () {
    $.ajax({
        type: 'GET',
        crossdomain: true,
        url: 'http://localhost:8080/job4j_cars/auth.do',
        dataType: 'json'
    }).done(function () {
        $('#add').append('<p><button class="buttonAll" style="margin: 2px 1px 1px;padding: 1px; width: 180px">' +
            '<a href="upload" style="color: black">Добавить объявление</a></button></p>')
    }).fail(function () {
        $('#add').html(' ')
    });
});