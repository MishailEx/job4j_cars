$(document).ready(function () {
    $.ajax({
        type: 'POST',
        crossdomain: true,
        url: 'http://localhost:8080/job4j_cars/showAnn',
        dataType: 'json'
    }).done(function (rsl) {
        for (var i of rsl.images) {
            $('#image').append(`<img style="margin: 10px" src="http://localhost:8080/job4j_cars/download?name=${i.pathToImg}&nameAnn=${rsl.name}" width="160px" height="160px">`)
        }
        $('#nameAnn').append(`<h4>${rsl.car.name} - ${rsl.price} руб.</h4>`)
        $('#bodyName').append(`Кузов: ${rsl.car.body.name}`)
        $('#trans').append(`${rsl.car.transmission.name}`)
        $('#engineName').append(`Топливо: ${rsl.car.engine.name}`)
        $('#capName').append(`Обьем: ${rsl.car.capacity}`)
        $('#mileage').append(`Пробег: ${rsl.car.mileage} тыс.км`)
        $('#create').append(`Размещенно: ${rsl.created}`)
        $('#description').append(`${rsl.description}`)
    })
})