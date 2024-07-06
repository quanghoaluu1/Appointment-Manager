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
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Appointment;
import utils.DBUtils;

/**
 *
 * @author quang
 */
public class Dashboard extends HttpServlet {

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
            HttpSession session = request.getSession();
            int userId = (int)session.getAttribute("userId");
            List<Appointment> list = getAppointment(userId);
            if(list != null){
                request.setAttribute("list", list);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("List: " + request.getAttribute("list"));
        if(request.getAttribute("list") == null){
            System.out.println("Null List");
        }
        request.getRequestDispatcher("dashBoard.jsp").forward(request, response);
        
    }
    private static List<Appointment> getAppointment(int userId){
        String sql = "select * from Appointments where userId = ? and status = 'Scheduled'";
        List<Appointment> list = new ArrayList<>();
        try(Connection conn = DBUtils.getConnection();
                PreparedStatement ptm = conn.prepareStatement(sql)){
            ptm.setInt(1, userId);
            ResultSet rs = ptm.executeQuery();
            while(rs.next()){
                Date sqlDate = rs.getDate("appointmentDate");
                LocalDate localDate = sqlDate.toLocalDate();
                Time sqlTime = rs.getTime("appointmentTime");
                LocalTime localTime = sqlTime.toLocalTime();
                list.add(new Appointment(rs.getInt("id"), userId, localDate, localTime, rs.getString("purpose"), rs.getString("status")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public static void main(String[] args) {
        List<Appointment> list = getAppointment(1);
        for(Appointment a : list){
            System.out.println(a.getUserId());
            System.out.println(a.getDate());
            System.out.println(a.getTime());
            System.out.println(a.getPurpose());
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
