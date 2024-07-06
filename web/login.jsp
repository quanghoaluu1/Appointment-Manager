<%-- 
    Document   : login
    Created on : Jul 2, 2024, 5:33:51 PM
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
    <input type="submit" name="action" value="login">
</form>
<%
    String error = request.getParameter("error");
    if(error != null){ %>
    <p style="color:red"><%=error%></p>
   <% 
    }else if ("invalid".equals(error)) {
%>
    <p style="color:red">Invalid username or password</p>
<%
    }
%>
<a href="register.jsp">Register</a>
    </body>
</html>
