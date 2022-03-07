function reg() {
    $.ajax({
        type: 'POST',
        crossdomain: true,
        url: 'http://localhost:8080/job4j_cars/reg.do',
        data: {
            name: $('#name').val(),
            password : $('#password').val()
        },
        dataType: 'json'
    }).done(function (data) {
        if (data === 'Пользователь с таким именем уже существует') {
            alert('Пользователь с таким именем уже существует')
            document.location.href = "http://localhost:8080/job4j_cars/reg.html";
        }
    }).fail(function () {
        document.location.href = "http://localhost:8080/job4j_cars";
    });
}