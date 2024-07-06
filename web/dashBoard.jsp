<%-- 
    Document   : dashBoard
    Created on : Jul 2, 2024, 5:40:31 PM
    Author     : quang
--%>

<%@page import="java.util.List"%>
<%@page import="model.Appointment"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
        <link href='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.8/main.min.css' rel='stylesheet' />
    <script src="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.14/index.global.min.js"></script>
    
    <script>

        document.addEventListener('DOMContentLoaded', function() {
            var calendarEl = document.getElementById('calendar');
            var calendar = new FullCalendar.Calendar(calendarEl, {
                initialView: 'dayGridMonth',
                events: [
                    <%
                        List<Appointment> list = (List<Appointment>)request.getAttribute("list");
                        for (Appointment appointment : list) {
                            String event = String.format(
                                "{ id: '%s', title: '%s', start: '%sT%s' },",
                                appointment.getId(),
                                appointment.getPurpose(),
                                appointment.getDate(),
                                appointment.getTime()
                            );
                            out.print(event);
                        }
                    %>
                ]
            });
            calendar.render();
        });

    </script>
    </head>
    <body>
        <p>Welcome, <%= session.getAttribute("userName")%></p>
        <a href="Logout">
            <button>
                Logout
            </button>
        </a>
        <div id="calendar"></div>
        
        <a href="View">View All Appointment</a>
        <a href="createAppointment.jsp">Create new Appointment</a>
    </body>
</html>
