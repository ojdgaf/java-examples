<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <title>Faculties | Edit</title>
    </head>
    <body>
    <a href="${pageContext.request.contextPath}/faculties">List of faculties</a>
    <hr/>
    <form:form action="${pageContext.request.contextPath}/faculties/${faculty.id}/update"
               modelAttribute="faculty"
               method="POST">
        Name: <form:input path="name"/>
        <input type="submit" value="Edit faculty"/>
    </form:form>
    </body>
</html>
