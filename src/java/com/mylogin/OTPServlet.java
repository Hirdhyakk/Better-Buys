/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mylogin;

import java.awt.HeadlessException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

/**
 *
 * @author hp
 */
@WebServlet(name = "OTPServlet", urlPatterns = {"/OTPServlet"})
public class OTPServlet extends HttpServlet {

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
        String email = request.getParameter("emailId");
        String otp = request.getParameter("otp");
//        Long otp = Long.valueOf(request.getParameter("otp"));
        ResultSet rs;
        try {
            MyConnection myConn = new MyConnection();
            myConn.createConnection();
            rs = myConn.stmt.executeQuery("Select * from verification where emailId='" + email + "';");
            rs.next();
            String checkotp = rs.getString("otp");
//            JOptionPane.showMessageDialog(null, checkotp);
//            myConn.rs = myConn.stmt.executeQuery("select * from verification where emailId='" + email + "' and otp='" + otp + "'");
//            if (rs.next()) {
//                myConn.rs = myConn.stmt.executeQuery("Selet * from verificaton where emailId='" + email + "';");
            if (otp.equals(checkotp)) {
                String name = rs.getString("name");
                Long phone = Long.valueOf(rs.getString("phone"));
                String password = rs.getString("password");
//                myConn.stmt.executeUpdate("insert into verification(name,emailId,phone,password,otp) values('" + name + "','" + email + "'," + phone + ",'" + password + "', '" + otpmessage + "');");
                myConn.stmt.executeUpdate("insert into user values ('" + name + "', '" + email + "', " + phone + ", '" + password + "');");
                myConn.stmt.executeUpdate("delete from verification where emailId='"+email+"';");
                JOptionPane.showMessageDialog(null, "User Added Successfully");
//                response.sendRedirect("login.html");
            } else {
                JOptionPane.showMessageDialog(null, "Invalid OTP");
            }
        } catch (HeadlessException | NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, "User Already Exists");
        }
        response.sendRedirect("login.html");
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
