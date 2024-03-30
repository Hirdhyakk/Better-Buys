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
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;

/**
 *
 * @author akbri
 */
@WebServlet(name = "RegistrationServlet", urlPatterns = {"/RegistrationServlet"})
public class RegistrationServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter output = response.getWriter();
        String name, email, password;
        long phone;
        name = request.getParameter("name");
        email = request.getParameter("email");
        phone = Long.parseLong(request.getParameter("phone"));
        password = request.getParameter("password");
        String otpmessage = generateOTP(6);
//        output.write(name);
//        output.write(email);
//        output.write(phone+"");
//        output.write(password);
        MyConnection myConn = new MyConnection();
        myConn.createConnection();
        try {
            sendEmail(email, otpmessage);
            JOptionPane.showMessageDialog(null, "OTP send to " + email);
        } catch (AddressException ex) {
            Logger.getLogger(OTPServlet.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Something Went Wrong");
        }
        try {
            myConn.stmt.executeUpdate("delete from verification where emailId='" + email + "'");
            myConn.stmt.executeUpdate("insert into verification(name,emailId,phone,password,otp) values('" + name + "','" + email + "'," + phone + ",'" + password + "', '" + otpmessage + "');");
            response.sendRedirect("getOTP.html");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    static void sendEmail(String emailId, String otp) throws AddressException, IOException {
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
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress("hirdhyakhanna@gmail.com"));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(emailId));

            // Set Subject: header field
            message.setSubject("Confirmation OTP");

            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();

            // Now set the actual message
            messageBodyPart.setText("OTP : " + otp);

            // Create a multipar message
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
//            messageBodyPart = new MimeBodyPart();
//            String filename = "D:/Java Training/Java Email with Attachment/MyFile/Amit.txt";
//            String filenameAttachment = "Text.txt";
//            String filenameAttachment=filename.substring(filename.lastIndexOf('/')+1);
//            DataSource source = new FileDataSource(filename);
//            messageBodyPart.setDataHandler(new DataHandler(source));
//            messageBodyPart.setFileName(filenameAttachment);
//            multipart.addBodyPart(messageBodyPart);

            // Send the complete message parts
            message.setContent(multipart);

            // Send message
            Transport.send(message);

//            System.out.println("Sent message successfully....");

        } catch (MessagingException ex) {
            System.out.print("Exception is = " + ex);
        }
    }

    public static String generateOTP(int otpLength) {
        Random random = new Random();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < otpLength; i++) {
            sb.append(random.nextInt(10));
        }

        String otp = sb.toString();

        return otp;
    }
}
