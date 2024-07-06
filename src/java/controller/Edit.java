/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class Edit extends HttpServlet {

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
        int id = Integer.parseInt(request.getParameter("id"));
        String date = request.getParameter("date");
        String time = request.getParameter("time");
        String purpose = request.getParameter("purpose");
        String url = "edit.jsp";
        try{
            
            if(editAppointment(id, date, time, purpose)){
                request.setAttribute("appointment", id);
                url = "Dashboard";
            }else{
                url = "edit.jsp";
                request.setAttribute("error", "Edit failed");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        request.getRequestDispatcher(url).forward(request, response);
    }
    private boolean editAppointment(int id, String date, String time, String purpose) throws SQLException, ClassNotFoundException{
        String sql = "update Appointments set appointmentDate = ?, appointmentTime = ?, purpose = ? where id = ?";
        boolean check = false;
        try(Connection conn = DBUtils.getConnection();
                PreparedStatement ptm = conn.prepareStatement(sql)){
            ptm.setString(1, date);
            ptm.setString(2, time);
            ptm.setString(3, purpose);
            ptm.setInt(4, id);
            check = ptm.executeUpdate() > 0;
        }
        return check;
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
