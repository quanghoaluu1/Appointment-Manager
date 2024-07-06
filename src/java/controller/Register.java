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
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.DBUtils;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author quang
 */
public class Register extends HttpServlet {

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
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String url = null;
        
        try {
            boolean valid = true;
            if (userNameExisted(userName)) {
                valid = false;
                url = "register.jsp";
                request.setAttribute("error", "Duplicated user Name");
            }
            if (!isValidPassword(password)){
                valid = false;
                url = "register.jsp";
                request.setAttribute("error", "Password must be [5,50] \nMust contain numbers, letter and special character");
            }
            if(valid){
                if(registerUser(userName, password, email)){
                    url = "login.jsp";
                }
                else{
                    
                    request.setAttribute("error", "Register failed");
                    url = "register.jsp";
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", e.getMessage());
        }
        request.getRequestDispatcher(url).forward(request, response);
    }

    private boolean registerUser(String userName, String password, String email) throws SQLException, ClassNotFoundException {
        String sql = "insert into Users(username, password, email) values (?,?,?)";
        boolean check = false;
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ptm = conn.prepareStatement(sql)) {
            ptm.setString(1, userName);
            ptm.setString(2, hashedPassword);
            ptm.setString(3, email);
            check = ptm.executeUpdate() > 0;
        }
        return check;
    }

    private boolean userNameExisted(String userName) throws SQLException, ClassNotFoundException {
        String sql = "select *  from Users where userName = ?";
        boolean check = false;
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ptm = conn.prepareStatement(sql)) {
            ptm.setString(1, userName);
            ResultSet rs = ptm.executeQuery();
            check = rs.next();
        }
        return check;
    }
    
    private boolean isValidPassword(String password){
        final String PASSWORD_PATTERN = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{5,50}$";
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
//    public static void main(String[] args) {
//        
//        try {
//            System.out.println(isValidPassword("1"));
//        }catch(Exception e){
//            e.printStackTrace();
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
