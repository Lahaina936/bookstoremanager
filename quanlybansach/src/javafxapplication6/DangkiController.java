/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxapplication6;

import classjava.connectSQL;
import classjava.getData;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author HDTC
 */
public class DangkiController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private TextField gmail;
    @FXML
    private TextField password;
    @FXML
    private TextField adderess;
    @FXML
    private Button btn_dangki;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     public void dangki() throws ClassNotFoundException, ClassNotFoundException, SQLException, IOException{
        Connection connect = connectSQL.getconnect();
        String sql = "INSERT INTO user ( username,email,password,address) "
                + "VALUES(?,?,?,?)";
       String sqlcheck ="SELECT * FROM user WHERE username= '"+username.getText() +"'";
       PreparedStatement preparecheck = connect.prepareStatement(sqlcheck);
       ResultSet resultcheck = preparecheck.executeQuery();
       Alert alert;
       
            if(
                    username.getText().isEmpty()
                    || gmail.getText().isEmpty()
                    || password.getText().isEmpty()
                    || adderess.getText().isEmpty() ){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            }
                else if (resultcheck.next())
                      {
                          alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("user already exist");
                    alert.showAndWait();
                      } 
       else {
           PreparedStatement prepare = connect.prepareStatement(sql);
                    prepare.setString(1, username.getText());
                    prepare.setString(2, gmail.getText());
                    prepare.setString(3, password.getText());
                    prepare.setString(4, adderess.getText());
                    
                    prepare.executeUpdate();
                    
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();
                    btn_dangki.getScene().getWindow().hide();
               
                Parent root = FXMLLoader.load(getClass().getResource("/javafxapplication6/login.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.initStyle(StageStyle.TRANSPARENT);
       }
     }
}
