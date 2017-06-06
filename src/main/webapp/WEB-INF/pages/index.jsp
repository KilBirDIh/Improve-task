<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Improve meetings</title>
    <link href="../resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- Material Design Bootstrap -->
    <link href="../resources/css/mdb.min.css" rel="stylesheet">

    <!-- Your custom styles (optional) -->
    <link href="../resources/css/style.css" rel="stylesheet">
</head>
<body>
<div>
    <form:form method="post" action="${pageContext.request.contextPath}/filter">
        <div class="row">
            <div class="col-md-4">
                <div class="md-form">
                    <form:input type="text" path="nameOfMeeting" id="nameOfMeeting" placeholder="Тема" class="form-control"/>
                    <br/>
                </div>
                <form:label path="beginDate" >Время проведения с </form:label>
                <form:input path="beginDate" type="date" />

                <form:label path="endDate">по </form:label>
                <form:input path="endDate" type="date"/>
            </div>
            <div class="col-md-3"></div>
            <div class="col-md-4">
                <label for="departments">Подразделение</label>
                <form:select path="departments" name="listDepartments">

                    <%--@elvariable id="departmentList" type="java.util.List"--%>
                <c:forEach var="department" items="${departmentList}">

                    <form:option value="${department.id}">${department.name}</form:option>

                </c:forEach>
                </form:select>

                <br/>

                <form:label path="withEmployer">С участием</form:label>
                <form:select path="withEmployer">

                    <%--@elvariable id="employeesList" type="java.util.List"--%>
                    <c:forEach var="employee" items="${employeesList}">

                        <form:option value="${employee.id}">${employee.surname} ${employee.name}</form:option>

                    </c:forEach>
                </form:select>
            </div>
        </div>

        <button type="submit" class="btn btn-default">Отфильтровать</button>
    </form:form>

</div>
</body>
</html>

