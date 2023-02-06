/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxapplication6;

import classjava.connectSQL;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author HDTC
 */
public class DashboardController implements Initializable {

    @FXML
    private Label doanh_thu;
    @FXML
    private Label luong_khach;
    @FXML
    private Label tiem_nang;
    @FXML
    private Label so_don;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            // TODO
            Connection connect = connectSQL.getconnect();
            
            String sql1 ="SELECT SUM(totalpay) FROM transacsion" ;
            PreparedStatement prepare1 = connect.prepareStatement(sql1);
            ResultSet result1 = prepare1.executeQuery();
            if(result1.next())
            doanh_thu.setText(String.valueOf( result1.getDouble("SUM(totalpay)")));
            
            String sql2 ="SELECT COUNT(user_id) FROM user" ;
            PreparedStatement prepare2 = connect.prepareStatement(sql2);
            ResultSet result2 = prepare2.executeQuery();
            if(result2.next())
            luong_khach.setText(String.valueOf(result2.getInt("COUNT(user_id)")));
            String sql3 ="SELECT COUNT(transacsion_id) FROM transacsion" ;
            PreparedStatement prepare3 = connect.prepareStatement(sql3);
            ResultSet result3 = prepare3.executeQuery();
            if(result3.next())
            so_don.setText(String.valueOf(result3.getInt("COUNT(transacsion_id)")));
            String sql4 ="SELECT  username,transacsion.user_id,SUM(totalpay) FROM transacsion,quanlybansach.user where transacsion.user_id=user.user_id group by user_id order by SUM(totalpay) LIMIT 1" ;
            PreparedStatement prepare4 = connect.prepareStatement(sql4);
            ResultSet result4 = prepare4.executeQuery();
            if(result4.next())
            tiem_nang.setText(result4.getString("username"));
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
   
    }    
    
}
