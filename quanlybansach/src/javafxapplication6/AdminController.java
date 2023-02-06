/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxapplication6;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author HDTC
 */
public class AdminController implements Initializable {

    @FXML
    private Button btnclose;
    @FXML
    private Button btn_trangchu;
    @FXML
    private Button btn_menusach;
    @FXML
    private Button btn_donhang;
    @FXML
    private BorderPane view;
    @FXML
    private Button logout;

    /**
     * Initializes the controller class.
     */
    @FXML
     public void close(){
        System.exit(0);
    }
    @FXML
     public void switchtrangchu() throws IOException{
         AnchorPane adminview =FXMLLoader.load(getClass().getResource("/javafxapplication6/dashboard.fxml"));
         view.setCenter(adminview);
     }
    @FXML
     public void switchmenusach() throws IOException{
         AnchorPane adminview =FXMLLoader.load(getClass().getResource("/javafxapplication6/menusachadmin.fxml"));
         view.setCenter(adminview);
     }
    @FXML
     public void switchhoadon() throws IOException{
         AnchorPane adminview =FXMLLoader.load(getClass().getResource("/javafxapplication6/menuhoadon.fxml"));
         view.setCenter(adminview);
     }
     public void logout() throws IOException{
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");
            Optional<ButtonType> option = alert.showAndWait();
            
            if(option.get().equals(ButtonType.OK)){
                
                // HIDE YOUR DASHBOARD
                logout.getScene().getWindow().hide();
                // LINK YOUR LOGIN FORM
                Parent root = FXMLLoader.load(getClass().getResource("/javafxapplication6/login.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.initStyle(StageStyle.TRANSPARENT);

                stage.setScene(scene);
                stage.show();
            }
         }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
