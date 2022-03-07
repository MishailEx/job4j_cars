function go() {
    $.ajax({
        type: 'POST',
        crossdomain: true,
        url: 'http://localhost:8080/job4j_cars/ann',
        data: {
            description: $('#description').val(),
            price: $('#price').val(),
            mark: $('#selectMark').val(),
            model: $('#selectModel').val(),
            engine: $('#selectEngine').val(),
            body: $('#selectBody').val(),
            transmission: $('#selectTransmission').val(),
            mileage: $('#mileage').val(),
            capacity: $('#capacity').val(),
            year: $('#selectYearEnd').val()

        },
        dataType: 'json'
    }).fail(function () {
        location.href = 'http://localhost:8080/job4j_cars'
    });
}