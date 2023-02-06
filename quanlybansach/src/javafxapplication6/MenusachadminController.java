/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxapplication6;

import classjava.book;
import classjava.connectSQL;
import classjava.getData;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * FXML Controller class
 *
 * @author HDTC
 */
public class MenusachadminController implements Initializable {

    @FXML
    private TableColumn<book,Integer> availableBooks_col_bookid;
    @FXML
    private TableColumn<book,String> availableBooks_col_bookname;
    @FXML
    private TableColumn<book,String> availableBooks_col_catalog;
    @FXML
    private TableColumn<book,String> availableBooks_col_author;
    @FXML
    private TableColumn<book,Double> availableBooks_col_price;
    @FXML
    private TableView<book> availableBooks_tableView;
    @FXML
    private ImageView availableBooks_imageView;
    
    @FXML
    private TextField availableBooks_bookname;
    @FXML
    private TextField availableBooks_catalog;
    @FXML
    private TextField availableBooks_author;
    @FXML
    private TextField availableBooks_price;
    private Image image;
    @FXML
    private Label labelbookid;
    @FXML
    private AnchorPane menubookadmin_form;

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
        
        availableBooks_col_bookid.setCellValueFactory(new PropertyValueFactory<>("bookid"));
        availableBooks_col_bookname.setCellValueFactory(new PropertyValueFactory<>("bookname"));
        availableBooks_col_author.setCellValueFactory(new PropertyValueFactory<>("author"));
        availableBooks_col_catalog.setCellValueFactory(new PropertyValueFactory<>("catalog"));
        availableBooks_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
         availableBooks_tableView.setItems(availableBooksList);
    }
    @FXML
    public void availableBooksSelect(){
        book bookD = availableBooks_tableView.getSelectionModel().getSelectedItem();
        int num = availableBooks_tableView.getSelectionModel().getSelectedIndex();
        
        if((num - 1) < -1){ return; }
        
        labelbookid.setText(String.valueOf(bookD.getBookid()));
        availableBooks_bookname.setText(bookD.getBookname());
        availableBooks_author.setText(bookD.getAuthor());
        availableBooks_catalog.setText(bookD.getCatalog());
        availableBooks_price.setText(String.valueOf(bookD.getPrice()));
        getData.path = bookD.getImagelink();
        
        String uri = "file:"+bookD.getImagelink();
         image = new Image(uri, 101, 97, false, true);
        
        availableBooks_imageView.setImage(image);
    }
     public void availableBooksAdd() throws ClassNotFoundException, ClassNotFoundException, SQLException{
        Connection connect = connectSQL.getconnect();
        String sql = "INSERT INTO menu ( bookname,catalog, price, imagelink, author) "
                + "VALUES(?,?,?,?,?)";
       String sqlcheck ="SELECT * FROM menu WHERE bookname= '"+availableBooks_bookname.getText() +"'";
       PreparedStatement preparecheck = connect.prepareStatement(sqlcheck);
       ResultSet resultcheck = preparecheck.executeQuery();
       
            Alert alert;
            
            if(
                    availableBooks_bookname.getText().isEmpty()
                    || availableBooks_author.getText().isEmpty()
                    || availableBooks_catalog.getText().isEmpty()
                    || availableBooks_price.getText().isEmpty()
                    || getData.path == null || "".equals(getData.path)){
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            }else if (resultcheck.next())
                      {
                          alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("namebook already exist");
                    alert.showAndWait();
                      } 
                  else{
                    String uri = getData.path;
                    uri = uri.replace("\\", "\\\\");
                
                    
                    PreparedStatement prepare = connect.prepareStatement(sql);
                    prepare.setString(1, availableBooks_bookname.getText());
                    prepare.setString(2, availableBooks_catalog.getText());
                    prepare.setString(3, availableBooks_price.getText());
                    prepare.setString(4, uri);
                    prepare.setString(5, availableBooks_author.getText());
                    
                    prepare.executeUpdate();
                    
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();
                    
                    // TO BE UPDATED THE TABLEVIEW 
                    availableBooksShowListData();
                    // CLEAR FIELDS
                    availableBooksClear();
                   }
            }
     
        
        
    
    
    public void availableBooksUpdate() throws ClassNotFoundException, SQLException{
        
        String uri = getData.path;
        uri = uri.replace("\\", "\\\\");
        
        String sql = "UPDATE menu SET bookname = '"
                +availableBooks_bookname.getText()+"', author = '"
                +availableBooks_author.getText()+"', catalog = '"
                +availableBooks_catalog.getText()+"', price = '"
                +availableBooks_price.getText()+"', imagelink = '"
                +uri+"' WHERE product_id = '"+labelbookid.getText()+"'";
        
        Connection connect = connectSQL.getconnect();
        
        try{
            Alert alert;
            
            if(
                     availableBooks_bookname.getText().isEmpty()
                    || availableBooks_author.getText().isEmpty()
                    || availableBooks_catalog.getText().isEmpty()
                    || availableBooks_price.getText().isEmpty()
                    || getData.path == null || getData.path == ""){
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            }else{
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Book ID: " + labelbookid.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();
                
                if(option.get().equals(ButtonType.OK)){
                    Statement statement = connect.createStatement();
                    statement.executeUpdate(sql);
                    
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successful Updated!");
                    alert.showAndWait();
                    
                    // TO BE UPDATED THE TABLEVIEW 
                    availableBooksShowListData();
                    // CLEAR FIELDS
                    availableBooksClear();
                }
            }
        }catch(ClassNotFoundException | SQLException e){}
        
    }
    
    public void availableBooksDelete() throws ClassNotFoundException, SQLException{
        
        String sql = "DELETE FROM menu WHERE product_id = '"
                +labelbookid.getText()+"'";
        
        Connection connect = connectSQL.getconnect();
        
        try{
            Alert alert;
            
            if(labelbookid.getText().isEmpty()
                    || availableBooks_bookname.getText().isEmpty()
                    || availableBooks_author.getText().isEmpty()
                    || availableBooks_catalog.getText().isEmpty()
                    || availableBooks_price.getText().isEmpty()
                    || getData.path == null || getData.path == ""){
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please choose book");
                alert.showAndWait();
            }else{
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE Book ID: " + labelbookid.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();
                
                if(option.get().equals(ButtonType.OK)){
                    Statement statement = connect.createStatement();
                    statement.executeUpdate(sql);
                    
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successful Delete!");
                    alert.showAndWait();
                    
                    // TO BE UPDATED THE TABLEVIEW 
                    availableBooksShowListData();
                    // CLEAR FIELDS
                    availableBooksClear();
                }
            }
        }catch(ClassNotFoundException | SQLException e){}
        
    }
    
    public void availableBooksClear(){
        labelbookid.setText("");
        availableBooks_bookname.setText("");
        availableBooks_author.setText("");
        availableBooks_catalog.setText("");
        availableBooks_price.setText("");
        
        getData.path = "";
        
        availableBooks_imageView.setImage(null);
    }
    
    public void avaialableBooksInsertImage(){
        
        FileChooser open = new FileChooser();
        open.setTitle("Open Image File");
        open.getExtensionFilters().add(new ExtensionFilter("File Image", "*jpg", "*png"));
        
        File file = open.showOpenDialog(menubookadmin_form.getScene().getWindow());
        
        if(file != null){
            getData.path = file.getAbsolutePath();
            
            image = new Image(file.toURI().toString(), 101, 97, false, true) {};
            availableBooks_imageView.setImage(image);
        }
        
    }
     

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            availableBooksShowListData();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MenusachadminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
     
    