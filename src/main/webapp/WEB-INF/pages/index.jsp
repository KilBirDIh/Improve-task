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
    </form:form>

</div>
</body>
</html>

