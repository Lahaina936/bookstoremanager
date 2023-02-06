/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxapplication6;

import classjava.book;
import classjava.connectSQL;
import classjava.hoadon;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author HDTC
 */
public class MenuhoadonController implements Initializable {

    @FXML
    private TableView<hoadon> menuhoadon_table;
    @FXML
    private TableColumn<hoadon, Integer> col_iddonhang;
    @FXML
    private TableColumn<hoadon, Integer> col_idkhachhang;
    @FXML
    private TableColumn<hoadon, String> col_tenkhachhang;
    @FXML
    private TableColumn<hoadon, Double> col_dongia;

    /**
     * Initializes the controller class.
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public void showhoadon() throws ClassNotFoundException, SQLException{
        ObservableList<hoadon> listhoadon = FXCollections.observableArrayList();
        String sql ="SELECT transacsion.transacsion_id,transacsion.totalpay,user.user_id,user.username FROM transacsion, user WHERE transacsion.user_id=user.user_id;";  
         Connection connect = connectSQL.getconnect();
         PreparedStatement prepare = connect.prepareStatement(sql);
         ResultSet result = prepare.executeQuery();
         hoadon hoadon;
         while(result.next()){
             hoadon = new hoadon(result.getInt("transacsion_id"), result.getInt("user_id")
                       , result.getString("username")
                       , result.getDouble("totalpay"));
             listhoadon.add(hoadon);
         }
         col_tenkhachhang.setCellValueFactory(new PropertyValueFactory<>("username"));
         col_iddonhang.setCellValueFactory(new PropertyValueFactory<>("transacsion_id"));
         col_idkhachhang.setCellValueFactory(new PropertyValueFactory<>("user_id"));
         col_dongia.setCellValueFactory(new PropertyValueFactory<>("totalpay"));
         menuhoadon_table.setItems(listhoadon);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            showhoadon();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MenuhoadonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
