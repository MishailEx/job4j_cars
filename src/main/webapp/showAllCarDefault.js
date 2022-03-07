// $(document).ready(function () {
//     $.ajax({
//         type: 'GET',
//         crossdomain: true,
//         url: 'http://localhost:8080/job4j_cars/find',
//         dataType: 'json'
//     }).done(function (data) {
//         $('#tbody').html("")
//         for (var rsl of data) {
//             $('#tbody').append(`<tr><td>${rsl.description}</td>
//                     <td>${rsl.car.mark.name}</td>
//                     <td>${rsl.car.model.name}</td>
//                     <td>${rsl.car.body.name}</td>
//                     <td>${rsl.car.engine.name}</td>
//                     <td>ajnj</td>
//                     <td>${rsl.price}</td>
//                     <td> ${rsl.created}</td>
//                     </tr>`)
//         }
//     }).fail(function () {
//         $('#tbody').html("");
//         alert("обьявлений не найденно")
//     });
// })