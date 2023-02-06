/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classjava;

/**
 *
 * @author HDTC
 */
public class order {
    private int order_id;
    private int transacsion_id;
    private int product_id;
    private String bookname;
    private String author;
    private String catalog;
    private int quantity;
    private double amount;
    private String imagelink;
    public order (int order_id,int transacsion_id,int product_id,String bookname,String author,String catalog,int quantity,double amount,String imagelink) {
                this.order_id=order_id;
                this.transacsion_id=transacsion_id;
                this.product_id=product_id;
                this.bookname=bookname;
                this.quantity=quantity;
                this.amount=amount;
                this.author=author;
                this.catalog=catalog;
                this.imagelink=imagelink;
    }
    public order (int product_id,String bookname,String author,String catalog,int quantity,double amount,String imagelink) {
                this.product_id=product_id;
                this.bookname=bookname;
                this.quantity=quantity;
                this.amount=amount;
                this.author=author;
                this.catalog=catalog;
                this.imagelink=imagelink;
    }

    public String getImagelink() {
        return imagelink;
    }

    public void setImagelink(String imagelink) {
        this.imagelink = imagelink;
    }


    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getTransacsion_id() {
        return transacsion_id;
    }

    public void setTransacsion_id(int transacsion_id) {
        this.transacsion_id = transacsion_id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

 

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    
}
