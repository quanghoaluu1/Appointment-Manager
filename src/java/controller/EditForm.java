/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Appointment;
import utils.DBUtils;

/**
 *
 * @author quang
 */
public class EditForm extends HttpServlet {

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
        try{
            int id = Integer.parseInt(request.getParameter("id"));
            Appointment a = getAppointment(id);
            request.setAttribute("appointment", a);
            request.getRequestDispatcher("edit.jsp").forward(request, response);
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
     private static Appointment getAppointment(int id) throws SQLException, ClassNotFoundException{
        String sql = "select * from Appointments where id = ?";
        Appointment a = null;
        try(Connection conn = DBUtils.getConnection();
                PreparedStatement ptm = conn.prepareStatement(sql)){
            ptm.setInt(1, id);
            ResultSet rs = ptm.executeQuery();
            if(rs.next()){
                Date sqlDate = rs.getDate("appointmentDate");
                LocalDate localDate = sqlDate.toLocalDate();
                Time sqlTime = rs.getTime("appointmentTime");
                LocalTime localTime = sqlTime.toLocalTime();
                a = new Appointment(id, rs.getInt("userId"), localDate, localTime, rs.getString("purpose"), rs.getString("status"));
            }
    }
        return a;
    }
     public static void main(String[] args) {
        try {
            System.out.println(getAppointment(14).getPurpose());
        } catch (SQLException ex) {
            Logger.getLogger(EditForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EditForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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
