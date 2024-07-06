/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.DBUtils;

/**
 *
 * @author quang
 */
public class Create extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String error = null;
        String url = null;
        try {
            HttpSession session = request.getSession();
            int userId = (int) session.getAttribute("userId");
            String dateStr = request.getParameter("date");
            String timeStr = request.getParameter("time");
            String purpose = request.getParameter("purpose");
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

            LocalDate date = LocalDate.parse(dateStr, dateFormatter);
            LocalTime time = LocalTime.parse(timeStr, timeFormatter);
            LocalDateTime now = LocalDateTime.now();
            if (date.isBefore(now.toLocalDate()) || date.isEqual(now.toLocalDate()) && time.isBefore(now.toLocalTime())) {
                error = "Don't create appointment in the past, okay ?";
                url = "createAppointment.jsp";
            } else {
                if (createAppointment(userId, date, time, purpose)) {
                    url = "Dashboard";

                } else {
                    error = "Create failed";
                    url = "createAppointment.jsp";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("error", error);
        request.getRequestDispatcher(url).forward(request, response);

    }

    private boolean createAppointment(int userId, LocalDate date, LocalTime time, String purpose) throws SQLException, ClassNotFoundException {
        String sql = "insert into Appointments(userId, appointmentDate, appointmentTime, purpose, status) values(?,?,?,?,?)";
        boolean check = false;
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ptm = conn.prepareStatement(sql)) {

            ptm.setInt(1, userId);
            ptm.setDate(2, Date.valueOf(date));
            ptm.setTime(3, Time.valueOf(time));
            ptm.setString(4, purpose);
            ptm.setString(5, "Scheduled");
            check = ptm.executeUpdate() > 0;
            if (check) {
                try {
                    String email = getUserEmail(userId);
                    LocalDateTime appointmentDate = LocalDateTime.of(date, time);
                    LocalDateTime reminderTime = appointmentDate.minusHours(24);
                    
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                           try{
                               sendEmail(email, "Remind Appointment", "You have an appointment at "+ time + " in "+ date +"\nPurpose: " + purpose);
                           }catch(Exception e){
                               e.printStackTrace();
                           }
                        }
                    }, java.util.Date.from(reminderTime.atZone(ZoneId.systemDefault()).toInstant()));
                    
                }catch(Exception e){
                    e.printStackTrace();
            }}

        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    private static void sendEmail(String recipient, String subject, String content) throws AddressException, MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.prot", "587");
        

        Session session;
        session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication("quanghoaluu1@gmail.com", "tdzn yxho fclu mpue");
            }
        });
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("quanghoaluu1@gmail.com"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
        message.setSubject(subject);
        message.setText(content);
        
        Transport.send(message);
    }
    
    private String getUserEmail(int userId) throws SQLException, ClassNotFoundException{
        String sql = "select email from Users where id = ?";
        String email = "";
        try(Connection conn = DBUtils.getConnection();
                PreparedStatement ptm = conn.prepareStatement(sql)){
            ptm.setInt(1, userId);
            ResultSet rs = ptm.executeQuery();
            if(rs.next()){
                email = rs.getString("email");
            }
            
        }
        return email;
    }
//    public static void main(String[] args) {
//        try{
//            sendEmail("thuytrangcancer@gmail.com", "Test", "Hello Chi Iu");
//            System.out.println("Send email success");
//        }catch(MessagingException e){
//            e.printStackTrace();
//            System.out.println("Send email failed: " + e.getMessage());
//        }
//    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
