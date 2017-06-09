<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Improve meetings</title>
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- Material Design Bootstrap -->
    <link href="${pageContext.request.contextPath}/resources/css/mdb.min.css" rel="stylesheet">

</head>
<body>
<div class="container">
    <form:form method="post" action="${pageContext.request.contextPath}/filter" modelAttribute="filter">
        <div class="row">
            <div class="col-md-4">
                <div class="md-form">
                    <form:label path="name">Тема</form:label>
                    <br/>
                    <form:input type="text" path="name" name="name" class="form-control"/>
                    <br/>
                </div>
                <form:label path="beginDate" >Время проведения с </form:label>
                <form:input path="beginDate" name="beginDate" type="date" />

                <form:label path="endDate">по </form:label>
                <form:input path="endDate" name="endDate" type="date"/>
            </div>
            <div class="col-md-3"></div>
            <div class="col-md-4">
                <label for="department">Подразделение</label>
                <form:select path="department" multiple="false">
                    <form:option value="0" label="Выберите подразделение" />
                    <%--@elvariable id="departmentList" type="java.util.List"--%>
                    <form:options items="${departmentList}" itemValue="id" itemLabel="name" />
                </form:select>

                <br/>

                <form:label path="employee">С участием</form:label>
                <form:select path="employee" name="employee" multiple="false">

                    <form:option value="0" label="Выберите участника"/>
                    <%--@elvariable id="employeesList" type="java.util.List"--%>
                    <c:forEach var="employee" items="${employeesList}">

                        <form:option value="${employee.id}">${employee.surname} ${employee.name}</form:option>

                    </c:forEach>
                </form:select>
            </div>
        </div>

        <button type="submit" class="btn btn-default">Отфильтровать</button>
        <%--@elvariable id="meetingList" type="java.util.List"--%>
            <table class="table">
                <thead>
                <tr>
                    <th>Дата</th>
                    <th>Тема</th>
                    <th>Подразделение</th>
                    <th>Ответственный</th>
                    <th>Состав</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach items="${meetingList}" var="meeting">
                    <tr>
                        <td><c:out value="${meeting.meetingDateTime.toLocalDate()}" /></td>
                        <td><a href="${pageContext.request.contextPath}/meeting/edit?id=${meeting.id}"><c:out value="${meeting.subject}" /></a></td>
                        <td><c:out value="${meeting.responsibleDepartment.name}" /></td>
                        <td><c:out value="${meeting.responsibleEmployee.surname} ${meeting.responsibleEmployee.name.substring(0, 1)}. ${meeting.responsibleEmployee.patronymic.substring(0, 1)}." /></td>
                        <td><c:out value="${meeting.membersOfMeeting.size()}"/> </td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
    </form:form>
    <a href="${pageContext.request.contextPath}/meeting/create" class=" btn btn-default">Создать новое</a>

</div>
</body>
</html>

