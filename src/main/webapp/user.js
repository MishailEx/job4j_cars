$.ajax({
    type: 'GET',
    crossdomain: true,
    url: 'http://localhost:8080/job4j_cars/auth.do',
    dataType: 'json'
}).done(function (data) {
    $('#info').append(`<div style="float: right; margin-top: 7px">
        <button class="btn btn-primary" style="width: 100px; float: right; height: 35px; margin-right: 6px"><a href="logout">Выйти</a></button></div>`);
    $('#info').append(`<div style="float: right; margin-top: 5px">
        <a class="nav-link" style="color:white; display: block;">${data.name}</a></div>`);
}).fail(function () {
    $('#info').append(`<div style="float: right; margin-top: 7px">
        <button class="btn btn-primary"
         style="width: 100px; float: right; height: 35px; margin-right: 6px">
         <a href="login.html" style="display: block;">Войти</a>
         </button></div>`);
})