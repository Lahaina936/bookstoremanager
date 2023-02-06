/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxapplication6;

import classjava.book;
import classjava.connectSQL;
import classjava.getData;
import classjava.hoadon;
import classjava.order;
import java.awt.event.ActionEvent;
import java.io.IOException;
import static java.lang.System.in;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.*;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HDTC
 */
public class CustomerController implements Initializable {

    @FXML
    private Label text_welcome;
    @FXML
    private Button btn_menubook;
    @FXML
    private Button btn_cart;
    @FXML
    private Button btn_logout;
    @FXML
    private AnchorPane anchor_menubook;
    @FXML
    private Label label_id;
    @FXML
    private Label label_name;
    @FXML
    private Label label_catalog;
    @FXML
    private Label label_author;
    @FXML
    private Label label_price;
    @FXML
    private ImageView imagebook;
    @FXML
    private Button btn_addtocart;
    @FXML
    private AnchorPane anchor_cart;
    @FXML
    private TableView<order> tableview_cart;
    @FXML
    private Label label_bookidcart;
    
    @FXML
    private Label label_namebookcart;
    @FXML
    private Label label_totalcart;
    @FXML
    private Button btn_pay;
    @FXML
    private TableColumn<book, Integer> col_id_menubook;
    @FXML
    private TableColumn<book,String> col_name_menubook;
    @FXML
    private TableColumn<book,String> col_catalog_menubook;
    @FXML
    private TableColumn<book,String> col_author_menubook;
    @FXML
    private TableColumn<book,Double> col_price_menubook;
    @FXML
    private TableColumn<order, Integer> col_id_cart;
    @FXML
    private TableColumn<order, String> col_name_cart;
    @FXML
    private TableColumn<order, String> col_catalog_cart;
    @FXML
    private TableColumn<order, String> col_author_cart;
    
    @FXML
    private TableColumn<order,Double> col_price_cart;
    @FXML
    private TableView<book> tableview_menubook;
    private Image image;
    @FXML
    private TableColumn<order, Integer> col_quantity_cart;
    @FXML
    private Spinner<Integer> spin_quantity;
    @FXML
    private ImageView imagebook_cart;

    /**
     * Initializes the controller class.
     * @return 
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
     public ObservableList<book> availableBooksListData() throws ClassNotFoundException, SQLException{
        ObservableList<book> listData = FXCollections.observableArrayList();
        String sql = "SELECT *FROM menu";
        
        Connection connect = connectSQL.getconnect();
        
        try{
           PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();
            
            book bookD;
            
            while(result.next()){
                bookD = new book(result.getInt("product_id"), result.getString("bookname")
                       , result.getString("catalog")
                        ,result.getString("author")
                        , result.getDouble("price")
                        , result.getString("imagelink"));
                
                listData.add(bookD);
            }
        }catch(SQLException e){}
        return listData;
    }
    
    private ObservableList<book> availableBooksList;
    public void availableBooksShowListData() throws ClassNotFoundException, SQLException{
        availableBooksList = availableBooksListData();
        
        col_id_menubook.setCellValueFactory(new PropertyValueFactory<>("bookid"));
        col_name_menubook.setCellValueFactory(new PropertyValueFactory<>("bookname"));
        col_author_menubook.setCellValueFactory(new PropertyValueFactory<>("author"));
        col_catalog_menubook.setCellValueFactory(new PropertyValueFactory<>("catalog"));
        col_price_menubook.setCellValueFactory(new PropertyValueFactory<>("price"));
         tableview_menubook.setItems(availableBooksList);
    }
    @FXML
    public void availableBooksSelect(){
        book bookD = tableview_menubook.getSelectionModel().getSelectedItem();
        int num = tableview_menubook.getSelectionModel().getSelectedIndex();
        
        if((num - 1) < -1){ return; }
        
        label_id.setText(String.valueOf(bookD.getBookid()));
        label_name.setText(bookD.getBookname());
        label_author.setText(bookD.getAuthor());
        label_catalog.setText(bookD.getCatalog());
        label_price.setText(String.valueOf(bookD.getPrice()));
        
        
        String uri = "file:"+bookD.getImagelink();
         image = new Image(uri, 101, 97, false, true);
        
        imagebook.setImage(image);
    }
    
    
    
    public ObservableList<order> availablelístorder() throws ClassNotFoundException, SQLException{
        ObservableList<order> listData = FXCollections.observableArrayList();
        get_id_transacsion();
        String sql = "SELECT * FROM quanlybansach.order , menu WHERE quanlybansach.order.product_id=menu.product_id AND transacsion_id = "+transacsion_id ;
        
        Connection connect = connectSQL.getconnect();
        
        try{
           PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();
            
            order order;
            
            while(result.next()){
                order = new order(result.getInt("order_id"), result.getInt("transacsion_id")
                       , result.getInt("product_id"), result.getString("bookname")
                       , result.getString("author")
                        ,result.getString("catalog")
                         ,result.getInt("quantity")
                        , result.getDouble("amount")
                        , result.getString("imagelink"));
                
                listData.add(order);
            }
        }catch(SQLException e){}
        return listData;
    }
    @FXML
    public void available_order_Select() throws ClassNotFoundException, SQLException{
        
        order order = tableview_cart.getSelectionModel().getSelectedItem();
        int num = tableview_cart.getSelectionModel().getSelectedIndex();
        
        if((num - 1) < -1){ return; }
        
        label_bookidcart.setText(String.valueOf(order.getProduct_id()));
        label_namebookcart.setText(order.getBookname());
        spin_quantity.getValueFactory().setValue(order.getQuantity());
        String uri = "file:"+order.getImagelink();
         image = new Image(uri, 93, 86, false, true);
        
        imagebook_cart.setImage(image);
    }
    
    ObservableList<order> listorder;
    public void ShowListorder() throws ClassNotFoundException, SQLException{
         listorder =availablelístorder();
        col_id_cart.setCellValueFactory(new PropertyValueFactory<>("product_id"));
        col_name_cart.setCellValueFactory(new PropertyValueFactory<>("bookname"));
        col_author_cart.setCellValueFactory(new PropertyValueFactory<>("author"));
        col_catalog_cart.setCellValueFactory(new PropertyValueFactory<>("catalog"));
        col_quantity_cart.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        col_price_cart.setCellValueFactory(new PropertyValueFactory<>("amount"));
        
        tableview_cart.setItems(listorder);
        
    }
    private int transacsion_id;
    public void get_id_transacsion() throws ClassNotFoundException, SQLException{
        
        String sql = "SELECT MAX(transacsion_id) FROM quanlybansach.order";
        int check = 0 ;
        Connection connect = connectSQL.getconnect();
        
        try{
            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();
            
            if(result.next()){
                transacsion_id = result.getInt("MAX(transacsion_id)");
            }
            
            String checkData = "SELECT MAX(transacsion_id) FROM transacsion";
            
            prepare = connect.prepareStatement(checkData);
            result = prepare.executeQuery();
            
            if(result.next()){
                check = result.getInt("MAX(transacsion_id)");
            }
            
            if(transacsion_id == 0){
                transacsion_id += 1;
            }else if(check == transacsion_id){
                transacsion_id =check+1;
            }
            
        }catch(Exception e){e.printStackTrace();}
        
    }
    
    
    @FXML
    public void addorder () throws ClassNotFoundException, SQLException{
           book bookD = tableview_menubook.getSelectionModel().getSelectedItem();
        int num = tableview_menubook.getSelectionModel().getSelectedIndex();
        Alert alert;
        if((num - 1) < -1){ return; }
        Connection connect = connectSQL.getconnect();
        get_id_transacsion() ;
        
        String sql= "INSERT INTO quanlybansach.order (transacsion_id,product_id,quantity,amount)" + " VALUES ( ?, ? ,? ,? )";
         PreparedStatement pstm = connect.prepareStatement(sql);
        pstm.setString(1,String.valueOf(transacsion_id));
        pstm.setString(2,String.valueOf(bookD.getBookid()));
        pstm.setString(3,String.valueOf(1));
        pstm.setString(4,String.valueOf(bookD.getPrice()));
        pstm.executeUpdate();
        alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();
    } 
    
    public void totalmoney() throws ClassNotFoundException, ClassNotFoundException, SQLException{
        get_id_transacsion();
        Connection connect = connectSQL.getconnect();
        PreparedStatement prepare = connect.prepareStatement("SELECT SUM(amount) FROM quanlybansach.order WHERE transacsion_id= "+transacsion_id);
        ResultSet result = prepare.executeQuery();
        if(result.next()){
                Double total = result.getDouble("SUM(amount)");
            label_totalcart.setText("$" + String.valueOf(total));
        }
        else label_totalcart.setText("0$");
    }
    @FXML
    public void pay() throws ClassNotFoundException, SQLException{
        
        get_id_transacsion();
        Alert alert;
        Connection connect = connectSQL.getconnect();
        PreparedStatement prepare = connect.prepareStatement("SELECT SUM(amount) FROM quanlybansach.order WHERE transacsion_id= "+transacsion_id);
        ResultSet result = prepare.executeQuery();
        if(result.next()){
                Double total = result.getDouble("SUM(amount)");
        String sql = "INSERT INTO transacsion(user_id,totalpay) VALUES(?, ?)";
         prepare = connect.prepareStatement(sql);
        prepare.setString(1,String.valueOf(getData.user_id));
        prepare.setString(2,String.valueOf(total));
        prepare.executeUpdate();
         alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Pay!");
                    alert.showAndWait();
         tableview_cart.setItems(null);
         transacsion_id=-1;
        }
        else{
             alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
        }
    }
    
    public void logout(){
        try{
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");
            Optional<ButtonType> option = alert.showAndWait();
            
            if(option.get().equals(ButtonType.OK)){
                
                
                btn_logout.getScene().getWindow().hide();
                
                Parent root = FXMLLoader.load(getClass().getResource("/javafxapplication6/login.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

               
                stage.setScene(scene);
                stage.show();
            }
            
        }catch(IOException e){e.printStackTrace();}
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) { // TODO
       try {
            totalmoney();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ShowListorder();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
      try {
            availableBooksShowListData();
                    } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        
            SpinnerValueFactory.IntegerSpinnerValueFactory spinner = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 1);
        spin_quantity.setValueFactory(spinner);
       
        
        
        
        }
    

    @FXML
    private void switchForm(javafx.event.ActionEvent event) throws ClassNotFoundException, SQLException {
         if(event.getSource() == btn_menubook){
            anchor_menubook.setVisible(true);
            anchor_cart.setVisible(false);
        }
         if(event.getSource() == btn_cart){
            anchor_cart.setVisible(true);
            anchor_menubook.setVisible(false);
            totalmoney();
            ShowListorder();
             SpinnerValueFactory.IntegerSpinnerValueFactory spinner = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 1);
        spin_quantity.setValueFactory(spinner);
        
        spin_quantity.valueProperty().addListener(new ChangeListener<Integer>() {
            Alert alert;
           @Override
           public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
              
         
         order order = tableview_cart.getSelectionModel().getSelectedItem();
           int num = tableview_cart.getSelectionModel().getSelectedIndex();
        
           if((num - 1) < -1){ 
            
                return;
           }
           else {
          
               
                   Connection connect = null;
               try {
                   connect = connectSQL.getconnect();
              
                 Statement prepare = null;
            
                 prepare = connect.createStatement();
             
                
    
                     ResultSet  result = null;
               
                   result = prepare.executeQuery("SELECT * FROM menu WHERE product_id="+order.getProduct_id());
               
                 
                   
                   int price= 0;
               
             
                 if (result.next())
                     price = result.getInt("price");
             
            
             
                    int amout = price * (spin_quantity.getValue());
              

                     
               
                   prepare = connect.createStatement();
              
               
                 prepare.executeUpdate("UPDATE quanlybansach.order SET quantity = "+spin_quantity.getValue()+",amount = "+amout+" WHERE product_id = "+order.getProduct_id());
                   totalmoney();
                   ShowListorder();
              }catch(SQLException e){} catch (ClassNotFoundException ex) {
                 Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
             }
               
            }
           }
        }); 
         }}
        
        
        
        
    }


    





   
