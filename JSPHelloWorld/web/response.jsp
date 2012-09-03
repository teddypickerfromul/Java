<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <jsp:useBean id="mybean" scope="page" class="NameHandler.NameHandler" />
    <body>
        <div align="center">
        <jsp:setProperty name="mybean" property="name" />
        <h1>Hello <jsp:getProperty name="mybean" property="name" /> ! </h1>
        </div>
    </body>
</html>
