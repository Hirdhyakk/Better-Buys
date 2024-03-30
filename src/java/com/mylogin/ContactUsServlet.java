/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mylogin;

import com.sun.mail.smtp.SMTPMessage;
import java.awt.HeadlessException;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

/**
 *
 * @author akbri
 */
@WebServlet(name = "ContactUsServlet", urlPatterns = {"/ContactUsServlet"})
public class ContactUsServlet extends HttpServlet {

    String firstname, lastname, email, comments;
    long phone;

    void sendEmail(String email) throws IOException {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        Session session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("hirdhyakhanna@gmail.com", "iwwknehkrdpmevnv");
            }
        });
        try {
            String adminEmail = "hirdhyakhanna@gmail.com";
            SMTPMessage sendMessage = new SMTPMessage(session);
            sendMessage.setFrom(new InternetAddress("hirdhyakhanna@gmail.com"));
            sendMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            sendMessage.setRecipients(Message.RecipientType.CC, InternetAddress.parse(adminEmail));
            sendMessage.setSubject("Feedback Submission confirmation");
            sendMessage.setText("Hello " + firstname+" "+lastname + "\nYour details are as follows\nName = " + firstname+" "+lastname + "\nEmail = " + email + "\nPhone = " + phone + "\nComments = " + comments);
            Transport.send(sendMessage);
            JOptionPane.showMessageDialog(null, "Message Sent Successfully");
        } catch (HeadlessException | MessagingException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter output = response.getWriter();
        firstname = request.getParameter("firstname");
        lastname = request.getParameter("lastname");
        email = request.getParameter("emailId");
        phone = Long.parseLong(request.getParameter("phone"));
        comments = request.getParameter("comments");
        MyConnection myConn = new MyConnection();
        myConn.createConnection();
        try {
            myConn.stmt.executeUpdate("insert into contactus(name,email,phone,comments) values('" + firstname+" "+lastname + "','" + email + "'," + phone + ",'" + comments + "');");
            sendEmail(email);
            response.sendRedirect("index.html");
        } catch (IOException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
