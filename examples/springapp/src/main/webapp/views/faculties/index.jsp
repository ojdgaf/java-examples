<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Faculties | Index</title>
</head>
<body>
    <a href="${pageContext.request.contextPath}/faculties/create">Create faculty</a>
    <hr/>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="faculty" items="${faculties}">
                <tr>
                    <td>${faculty.id}</td>
                    <td>${faculty.name}</td>
                    <td><a href="${pageContext.request.contextPath}/faculties/${faculty.id}">show</a></td>
                    <td><a href="${pageContext.request.contextPath}/faculties/${faculty.id}/edit">edit</a></td>
                    <td>
                        <form:form action="${pageContext.request.contextPath}/faculties/${faculty.id}/delete" method="POST">
                            <input type="submit" value="delete"/>
                        </form:form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
