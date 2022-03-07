function findCar() {
    $.ajax({
        type: 'POST',
        crossdomain: true,
        url: 'http://localhost:8080/job4j_cars/find',
        data: {
            mark: $('#selectMark').val(),
            model: $('#selectModel').val(),
            engine: $('#selectEngine').val(),
            body: $('#selectBody').val(),
            transmission: $('#selectTransmission').val(),
            yearStart: $('#selectYearSt').val(),
            yearEnd: $('#selectYearEnd').val()
        },
        dataType: 'json'
    }).done(function (data) {
        $('#tbody').html("")
        for (var rsl of data) {
            $('#tbody').append(`<div id="announcement">
                    <div id="ann2" onclick="showAllCar()" style="width: 70%">
                    <div id="img"><img src="http://localhost:8080/job4j_cars/download?name=${rsl.images[0].pathToImg}&nameAnn=${rsl.name}" width="140px" height="140px"></div>
                    <div id="dataCar">
                        <div id="nameAnn""><a href="http://localhost:8080/job4j_cars/showAnn?idAnn=${rsl.id}"><h4>${rsl.car.name}</h4></a></div>
                        <div style="margin-left: 25px">
                        <div id="bodyName">Кузов: ${rsl.car.body.name}</div>
                        <div id="trans">${rsl.car.transmission.name}</div>
                        <div id="engineName">Топливо: ${rsl.car.engine.name}</div>
                        <div id="capName">Обьем: ${rsl.car.capacity}</div>
                        </div>
                    </div>
                    <div id="dataRest" >
                        <div id="price"><p id="pr">${rsl.price} руб.</p></div>
                        <div id="mileage">Пробег: ${rsl.car.mileage} тыс.км</div>
                        <div id="create">Размещенно: ${rsl.created}</div>
                    </div>
                    </div>
                  </div>`)
        }
    }).fail(function () {
        $('#tbody').html("");
        alert("обьявлений не найденно")
    });
}