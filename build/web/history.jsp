<%-- 
    Document   : history
    Created on : Jul 6, 2024, 3:11:45 PM
    Author     : quang
--%>

<%@page import="java.util.List"%>
<%@page import="model.Appointment"%>
<%@page import="model.Appointment"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <p>Welcome, <%= session.getAttribute("userName")%></p>
        <a href="Logout">
            <button>
                Logout
            </button>
        </a>

        
        <%
            System.out.println(session.getAttribute("userId"));
            List<Appointment> list = (List<Appointment>) request.getAttribute("history");
            
            if (list != null && !list.isEmpty()) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <td>ID</td>
                    <td>Date</td>
                    <td>Time</td>
                    <td>Purpose</td>
                    <td>Status</td>
                    <td>Edit</td>
                    <td>Cancel</td>
                </tr>
            </thead>

            <tbody>
                <%
                    for (Appointment appointment : list) {
                        System.out.println(appointment.getId());

                %>
                <tr>
                    <td><%=appointment.getId()%></td>
                    <td><%=appointment.getDate()%></td>
                    <td><%=appointment.getTime()%></td>
                    <td><%=appointment.getPurpose()%></td>
                    <td><%= appointment.getStatus()%></td>
                    <td><a href="EditForm?id=<%=appointment.getId()%>"><button>Edit</button></a></td>
                    <td><a href="Cancel?id=<%=appointment.getId()%>"><button>Cancel</button></a></td>
                </tr>
                <%
                    }
                %>

            </tbody>
        </table>
        <%
        } else {
        %>
        <p>No Appointment to display</p>
        <%
            }
        %>
        <a href="Dashboard">Back to dashboard</a>
        <a href="View">View Appointment</a>
    </body>
</html>
