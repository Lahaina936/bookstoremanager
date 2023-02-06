/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classjava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author HDTC
 */
public class connectSQL {
    

    public static Connection getconnect() throws ClassNotFoundException, SQLException{
  
 
        Class.forName("com.mysql.cj.jdbc.Driver");
         return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/quanlybansach", "root", "taolaquang");
    
        
}
}

