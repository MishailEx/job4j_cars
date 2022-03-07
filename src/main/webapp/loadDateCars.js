$(document).ready(function () {
    $.ajax({
        type: 'GET',
        crossdomain: true,
        url: 'http://localhost:8080/job4j_cars/load',
        dataType: 'json'
    }).done(function (data) {
        var body = data[0]
        var mark = data[1]
        var engine = data[2]
        var transmission = data[3]
        var year = data[4]
        for (var o of body) {
            var bodyJSON = JSON.stringify(o)
            $('#selectBody').append(`<option value='${bodyJSON}'>${o.name}</option>`)
        }
        for (var m of mark) {
            var markJSON = JSON.stringify(m)
            $('#selectMark').append(`<option value='${markJSON}'>${m.name}</option>`)
        }
        for (var e of engine) {
            var engineJSON = JSON.stringify(e)
            $('#selectEngine').append(`<option value='${engineJSON}'>${e.name}</option>`)
        }
        for (var t of transmission) {
            var transJson = JSON.stringify(t)
            $('#selectTransmission').append(`<option value='${transJson}'>${t.name}</option>`)
        }
        for (var ys of year) {
            var yearStJson = JSON.stringify(ys)
            $('#selectYearSt').append(`<option value='${yearStJson}'>${ys.name}</option>`)
        }
        for (var ye of year) {
            var yearEndJson = JSON.stringify(ye)
            $('#selectYearEnd').append(`<option value='${yearEndJson}'>${ye.name}</option>`)
        }
    });
});