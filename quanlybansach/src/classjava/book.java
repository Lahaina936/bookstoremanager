/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classjava;

/**
 *
 * @author HDTC
 */
public class book {
    private int bookid;
    private String bookname;
    private String catalog;
    private String author;
    private double price;
    private String imagelink;
    
    public book (int bookid, String bookname, String catalog, String author,  double price,String imagelink){
        this.bookid=bookid;
        this.bookname=bookname;
        this.catalog=catalog;
        this.author=author;
        this.price=price;
        this.imagelink=imagelink;
                                                    
    }

    public String getImagelink() {
        return imagelink;
    }

    public void setImagelink(String imagelink) {
        this.imagelink = imagelink;
    }

    public int getBookid() {
        return bookid;
    }

    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    
}
