<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Edit meeting</title>
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- Material Design Bootstrap -->
    <link href="${pageContext.request.contextPath}/resources/css/mdb.min.css" rel="stylesheet">
</head>
<body>
<script href="${pageContext.request.contextPath}/resources/js/jquery-3.1.1.js" type="text/javascript"></script>
<div class="container">
    <div class="col-md-6">
        <form:form method="post" action="${pageContext.request.contextPath}/meeting/edit" modelAttribute="meeting">
            <form:input type="hidden" path="meetingId"/>
            <form:label path="subject">Тема</form:label>
            <form:input path="subject" type="text"/>
            <form:label path="datetime">Время проведения</form:label>
            <form:input path="datetime" type="datetime-local"/>
            <br/>

            <label for="department">Подразделение</label>
            <form:select path="department" multiple="false" name="department">
                <form:option value="0" label="Выберите подразделение"/>
                <%--@elvariable id="departmentList" type="java.util.List"--%>
                <form:options items="${departmentList}" itemValue="id" itemLabel="name"/>
            </form:select>
            <br/>

            <label for="responsibleEmployee">Ответственный</label>
            <form:select path="responsibleEmployee" multiple="false">
                <%--@elvariable id="departmentList" type="java.util.List"--%>
                <c:forEach var="employee" items="${employeesList}">

                    <form:option
                            value="${employee.id}">${employee.surname} ${employee.name} ${employee.patronymic} ${employee.department.name}</form:option>

                </c:forEach>
            </form:select>

            <br/>

            <table class="table">
                <thead>
                <tr>
                    <th>Ф.И.О</th>
                    <th>Возраст</th>
                    <th>Подразделение</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>

                    <%--@elvariable id="membersList" type="java.util.List"--%>
                <c:forEach items="${membersList}" var="member">
                    <tr>
                        <td><c:out
                                value="${member.surname} ${member.name.substring(0, 1)}. ${member.patronymic.substring(0, 1)}."/></td>
                        <td><%--@elvariable id="Period" type="java.time.Period"--%>
                                <%--@elvariable id="LocalDate" type="java.time.LocalDate"--%>
                            <c:out value="${Period.between(member.dateOfBirth, LocalDate.now()).getYears()}"/></td>
                        <td><c:out value="${member.department.name}"/></td>
                        <td>
                            <form action="${pageContext.request.contextPath}/meeting/delete?id=${member.id}"
                                  method="post">
                                <button class="btn btn-danger" type="submit">
                                    <c:out value="удалить"/>
                                </button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>

            <label for="employee">Участник</label>
            <select id="employee">

                    <%--@elvariable id="employeesList" type="java.util.List"--%>
                <c:forEach var="employee" items="${employeesList}">

                    <option value="${employee.id}">${employee.surname} ${employee.name} ${employee.patronymic}</option>

                </c:forEach>
            </select>

            <a href="">Dobavit</a>
            <button class="btn btn-default" type="submit">Сохранить</button>
        </form:form>
    </div>
</div>
</body>
</html>
