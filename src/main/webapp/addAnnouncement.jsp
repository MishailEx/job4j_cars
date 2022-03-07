<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script
            src="https://code.jquery.com/jquery-3.6.0.js"
            integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
            crossorigin="anonymous">
    </script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<script src="loadDateCars.js" defer></script>
<script src="model.js" defer></script>
<script src="buttonAddAnnouncement.js"></script>
<script src="addAnn.js"></script>
<script src="user.js" defer></script>

<header class="header">
    <div class="header_firstLine" style="height: 50px; width: 100%; float: left; background: #ea6161">
        <div class="headFirst" id="main" style="width: 10%;">
            <p><a href="index.html">На главную</a></p>
        </div>
        <div class="headFirst" id="auth" style="width: 20%;">
            <p><a href="login.html">Авторизация</a></p>
        </div>
        <div class="headFirst" id="reg" style="width: 20%;">
            <p><a href="reg.html">Регистрация</a></p>
        </div>
        <div class="headFirst" id="info" style="width: 20%; height: 100%; float: right;"></div>
        <div class="headFirst" id="add" style="width: 20%; float: right;"></div>
    </div>
</header>
<body>
<div id="content">
    <div id="workWithImg" style="float: left; width: 100%; margin: 10px;">
        <div style="float: left;">
            <form method="POST" enctype="multipart/form-data"
                  action="<c:url value='/upload'/>">
                Загрузите фото: <input type="file" multiple name="file"><br/>
                <br/>
                <button type="submit" value="">Разместить фото</button>
            </form>
        </div>
        <div id="image" style="float: left; width: 100%">
            <div>
                <c:forEach var="im" items="${images}">
                    <div>
                        <img style="margin: 5px; float: left;" src="<c:url value='/download?name=${im}'/>" width="130px" height="130px"/>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
    <div id="select" style="width: 100%; float: left;">
        <div style="float: left; margin: 10px">
            <select name="mark" id="selectMark" onchange="model()" style="width: 180px; height: 30px" required>
                <option value="" disabled selected>Выберите марку</option>
            </select>
        </div>
        <div style="float: left; margin: 10px">
            <select name="model" id="selectModel" style="width: 180px; height: 30px" required>
                <option value="" disabled selected>Выберите модель</option>
            </select>
        </div>
        <div style="float: left; margin: 10px">
            <select id="selectBody" style="width: 180px; height: 30px">
                <option value="" disabled selected>Тип кузова</option>
            </select>
        </div>
        <div style="float: left; margin: 10px">
            <select id="selectEngine" style="width: 180px; height: 30px" required>
                <option value="" disabled selected>Тип топлива</option>
            </select>
        </div>
        <div style="float: left; margin: 10px">
            <select id="selectTransmission" style="width: 180px; height: 30px" required>
                <option value="" selected>Коробка передач</option>
            </select>
        </div>
        <div style="float: left; margin: 10px">
            <select id="selectYearEnd" style="width: 180px; height: 30px" required >
                <option value="" selected>Год</option>
            </select>
        </div>
    </div>
    <div id="enter" style="float: left; width: 100%">
        <div style="float: left; margin: 10px;">
            <input required id="mileage" name="mileage" style="float: left; width: 180px; height: 30px" type="number" step="1" min="0" placeholder="Пробег">
        </div>
        <div style="float: left; margin: 10px;">
            <input required id="capacity" name="capacity" style="float: left; width: 180px; height: 30px" type="number" step="0.1" min="0.6"
                   placeholder="Обьем">
        </div>
    </div>

    <div style="width: 100%; height: auto">
        <textarea required id="description" style="float: left; margin: 10px; height: 100px; width: 380px; resize: none"
                  class="form-control"
                  name="description" placeholder="Текст объявления"></textarea>
        <div style="float: left; margin: 10px;">
            <input id="price" name="price" style="float: left;" type="number" step="0.01" min="0" placeholder="Цена"> ₽
        </div>
    </div>
    <div style="float: left; margin: 10px">
        <button type="button" onclick="go()" class="s">Разместить обьявление</button>
    </div>
</div>

</body>
</html>
