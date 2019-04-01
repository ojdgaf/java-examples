<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <title>Spring Application Example</title>
    </head>
    <body>
        <form:form action="${pageContext.request.contextPath}/logout" method="POST">
            <input type="submit" value="logout"/>
        </form:form>
        <hr/>
        <h1>Home Page</h1>
        <a href="${pageContext.request.contextPath}/faculties">List of faculties</a>
    </body>
</html>
