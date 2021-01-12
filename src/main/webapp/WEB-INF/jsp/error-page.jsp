<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ops error!</title>
</head>
<body>
<h3>Error Details</h3>
<strong>Status Code</strong>: ${statusCode} <br>
<strong>Requested URI</strong>: ${reqUri} <br>
<a href="${pageContext.request.contextPath}/do/">Come back!!!</a>
</body>
</html>
