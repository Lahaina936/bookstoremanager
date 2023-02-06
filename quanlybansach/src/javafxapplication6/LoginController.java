/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxapplication6;

import classjava.connectSQL;
import classjava.getData;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author HDTC
 */
public class LoginController implements Initializable {

    @FXML
    private TextField text_user;
    @FXML
    private TextField text_pass;
    @FXML
    private Button btn_login;
    @FXML
    private Button btn_dangki;
    @FXML
    private ComboBox<String> combobox;

    /**
     * Initializes the controller class.
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    @FXML
    public void loginAdmin() throws ClassNotFoundException, SQLException, IOException{
        
         Connection connect =  (Connection) connectSQL.getconnect();
        
        String sqladmin = "SELECT * FROM admin WHERE user = ? and password = ?";
        String sqlcustomer = "SELECT * FROM user WHERE email = ? and password = ?";
        // admin is our table name
                
            Alert alert;
            if (combobox.getValue()=="guest"){
                     Stage stage = new Stage() ;
                   Parent root = FXMLLoader.load(getClass().getResource("/javafxapplication6/guest.fxml"));
                      Scene scene = new Scene( root);
                    stage.initStyle(StageStyle.TRANSPARENT);
                      stage.setScene(scene);
                       stage.show();
               }
        
            
            else if(text_user.getText().isEmpty() || text_pass.getText().isEmpty()||combobox.getValue()==null){
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all !");
                alert.showAndWait();
            }else{
                if (combobox.getValue()=="admin"){
                    PreparedStatement prepare = connect.prepareStatement(sqladmin);
                    prepare.setString(1, text_user.getText());
                    prepare.setString(2, text_pass.getText());
                    ResultSet result = prepare.executeQuery();
                    if(result.next()){
                    // IF CORRECT USERNAME AND PASSWORD
                    
                    
                    
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Login");
                    alert.showAndWait();
                    
                    // TO HIDE YOUR LOGIN FORM 
                    btn_login.getScene().getWindow().hide();
                    

                    // LINK YOUR DASHBOARD FORM : 
                      Stage stage = new Stage() ;
                   Parent root = FXMLLoader.load(getClass().getResource("/javafxapplication6/admin.fxml"));
        
                      Scene scene = new Scene( root);
                   
                    stage.initStyle(StageStyle.TRANSPARENT);
                      stage.setScene(scene);
                       stage.show();
                       
                        
                    
                   
                    
                    
                    
                }else{ // IF WRONG USERNAME OR PASSWORD
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong Username/Password");
                    alert.showAndWait();
                }
            }
               if (combobox.getValue()=="customer"){
                    PreparedStatement prepare = connect.prepareStatement(sqlcustomer);
                    prepare.setString(1, text_user.getText());
                    prepare.setString(2, text_pass.getText());
                    ResultSet result = prepare.executeQuery();
                    if(result.next()){
                    // IF CORRECT USERNAME AND PASSWORD
                    
                    
                    
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Login");
                    alert.showAndWait();
                    
                    // TO HIDE YOUR LOGIN FORM 
                    btn_login.getScene().getWindow().hide();
                    

                    // LINK YOUR DASHBOARD FORM : 
                      Stage stage = new Stage() ;
                   Parent root = FXMLLoader.load(getClass().getResource("/javafxapplication6/customer.fxml"));
        
                      Scene scene = new Scene( root);
                   
                    stage.initStyle(StageStyle.TRANSPARENT);
                      stage.setScene(scene);
                       stage.show();
                 getData.username= result.getString("username");
                 getData.user_id=result.getInt("user_id");
                    
                   
                    
                    
                    
                }else{ // IF WRONG USERNAME OR PASSWORD
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong Username/Password");
                    alert.showAndWait();  
          }
        }
        
    }
                }
    
    public void close(){
        System.exit(0);
    }
    public void dangki() throws IOException{
          Stage stage = new Stage() ;
                   Parent root = FXMLLoader.load(getClass().getResource("/javafxapplication6/dangki.fxml"));
                    Scene scene = new Scene( root);
                    stage.initStyle(StageStyle.TRANSPARENT);
                      stage.setScene(scene);
                       stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     ObservableList<String> list =FXCollections.observableArrayList("guest","customer","admin");
     combobox.setItems(list);
    }    
    
}
   

    

