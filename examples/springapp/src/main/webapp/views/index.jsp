<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <title>Spring Application Example</title>
    </head>
    <body>
        <a href="${pageContext.request.contextPath}/logout">Logout</a>
        <hr/>
            <h1>Home Page</h1>
        <a href="${pageContext.request.contextPath}/faculties">List of faculties</a>
    </body>
</html>
