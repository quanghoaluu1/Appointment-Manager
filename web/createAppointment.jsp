<%-- 
    Document   : createAppointment
    Created on : Jul 3, 2024, 6:57:56 PM
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
        
        <h1>Create Appointment</h1>
        <form action="Main" method="POST">
            Date<input type="date" name="date" required=""><br>
            Time<input type="time" name="time" required=""><br>
            Purpose<input type="text" name="purpose" required=""><br>
            <input type="submit" name="action" value="create">
        </form>
        <%
            String error = (String)request.getAttribute("error");
            if(error != null){
                
            
        %>
        <p style="color: red;"><%= error %></p>
        <%
            }
        %>
        <a href="Dashboard">Back to dashboard</a>
    </body>
</html>
