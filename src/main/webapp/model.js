function model() {
    $.ajax({
        type: 'POST',
        crossdomain: true,
        url: 'http://localhost:8080/job4j_cars/load',
        data: {
            name: $('#selectMark').val()
        },
        dataType: 'json'
    }).done(function (data) {
        $('#selectModel').html(" ")
        for (var model of data) {
            var modelJSON = JSON.stringify(model)
            $('#selectModel').append(`<option value='${modelJSON}'>${model.name}</option>`)
        }
    })
}