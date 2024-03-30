/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mylogin;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

/**
 *
 * @author akbri
 */
@WebServlet(name = "MyServlet", urlPatterns = {"/MyServlet"})
public class MyServlet extends HttpServlet {
//    Connection conn;
//    Statement stmt;
//    ResultSet rs;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    void createConnection()
//    {
//        try
//        {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            conn=DriverManager.getConnection("jdbc:mysql://localhost:3312/testdbms", "root", "root");
//            stmt=conn.createStatement();
//        }
//        catch(ClassNotFoundException | SQLException e)
//        {
//            JOptionPane.showMessageDialog(null,"Exception is "+e.getMessage());
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
        response.setContentType("text/html");
        PrintWriter output = response.getWriter();
        String username = request.getParameter("uname");
        String password = request.getParameter("pass");
        try {
            MyConnection myConn = new MyConnection();
            myConn.createConnection();
            myConn.rs = myConn.stmt.executeQuery("select * from user where email='" + username + "' and password='" + password + "'");
            if (myConn.rs.next()) {
                //JOptionPane.showMessageDialog(null,"Login successfull ");
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                session.setAttribute("password", password);
                output.write("Success");
                response.sendRedirect("Home.html");
            } else {
                JOptionPane.showMessageDialog(null, "Either username/password is not correct ");
                response.sendRedirect("login.html");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Exception is " + e.getMessage());
        }
    }
}
