function fun1() {
    $.ajax({
        type: 'POST',
        crossdomain: true,
        url: 'http://localhost:8080/job4j_cars/auth.do',
        data: {
            name: $('#name').val(),
            password : $('#password').val()
        },
        dataType: 'json'
    }).done(function (data) {
        if (data === 'Не верный имя или пароль' || data === 'Не верный пароль') {
            alert(data)
        }
    }).fail(function () {
        location.href = "http://localhost:8080/job4j_cars";
    });
}