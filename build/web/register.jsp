<%-- 
    Document   : registerUser
    Created on : Jul 2, 2024, 5:38:58 PM
    Author     : quang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="Main" method="post">
    Username: <input type="text" name="userName" required><br>
    Password: <input type="password" name="password" required><br>
    Email: <input type="email" name="email" required><br>
    <input type="submit" name="action" value="register">
    
</form>
        <%
            String error = (String)request.getAttribute("error");
            if(error != null){
        %>
        <p style="color: red;"><%= error%></p>
        <%
            }
        %>
        <a href="login.jsp">Back to login</a>
    </body>
</html>
