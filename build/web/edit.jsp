<%-- 
    Document   : edit
    Created on : Jul 5, 2024, 6:46:30 PM
    Author     : quang
--%>

<%@page import="model.Appointment"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            System.out.println(request.getParameter("id"));
            Appointment a = (Appointment)request.getAttribute("appointment");
        %>
        <form action="Main" method="POST">
            Date<input type="date" name="date" value="<%= a.getDate()%>" required=""><br>
            Time<input type="time" name="time" value="<%= a.getTime()%>" required=""><br>
            Purpose<input type="text" name="purpose" value="<%=a.getPurpose()%>" required=""><br>
            <input type="hidden" name="id" value="<%=a.getId()%>">
            <input type="submit" name="action" value="edit">
            
        </form>
    </body>
</html>
