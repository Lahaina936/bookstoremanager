/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classjava;

/**
 *
 * @author HDTC
 */
public class hoadon {
    private int transacsion_id;
    private int user_id;
    private String username;
    private Double totalpay;
    public hoadon(int transacsion_id,int user_id,String username,Double totalpay){
        this.transacsion_id=transacsion_id;
        this.user_id=user_id;
        this.username=username;
        this.totalpay=totalpay;        
    }

    public int getTransacsion_id() {
        return transacsion_id;
    }

    public void setTransacsion_id(int transacsion_id) {
        this.transacsion_id = transacsion_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getTotalpay() {
        return totalpay;
    }

    public void setTotalpay(Double totalpay) {
        this.totalpay = totalpay;
    }
    
}
