/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mylogin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author akbri
 */
public class MyConnection {
   Connection conn;
        Statement stmt;
        ResultSet rs;
    void createConnection()
    {
//         Connection conn;
//        Statement stmt;
//        ResultSet rs;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/betterbuys", "root", "hirdhya0306");
            stmt=conn.createStatement();
        }
        catch(ClassNotFoundException | SQLException e)
        {
            JOptionPane.showMessageDialog(null,"Exception is "+e.getMessage());
        }
    }
}
