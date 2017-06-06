<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Improve meetings</title>
</head>
<body>
<div>
    <form>
        <div class="row">
            <div class="col-md-4">
                <div class="md-form">
                    <input type="text" id="nameOfMeeting" placeholder="Тема" class="form-control">
                    <br/>
                </div>
                <label for="beginDate">Время проведения с </label>
                <input type="date" id="beginDate">

                <label for="endDate">по </label>
                <input type="date" id="endDate">
            </div>
            <div class="col-md-3"></div>
            <div class="col-md-4">
                <label for="department">Подразделение</label>
                <select id="department">
                </select>
                <br/>
                <label for="withEmployer">С участием</label>
                <select id="withEmployer">
                </select>
            </div>
        </div>
        <button class="btn btn-default" onclick="filter();">Отфильтровать</button>
    </form>

    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
        Создать новое совещание
    </button>
</div>
</body>
</html>

