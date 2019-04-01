<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <title>Faculties | Create</title>
    </head>
    <body>
        <a href="${pageContext.request.contextPath}/faculties">List of faculties</a>
        <hr/>
        <form:form action="${pageContext.request.contextPath}/faculties/store"
                   modelAttribute="faculty"
                   method="POST">
            Name: <form:input path="name"/>
            <input type="submit" value="Create faculty"/>
        </form:form>
    </body>
</html>
