/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classjava;

/**
 *
 * @author HDTC
 */
public class transacsion {
    private  int transacsion_id;
    private int user_id;
    private String username;
    private double total;
    public transacsion(int transacsion_id,int user_id,String username,double total) {
        this.total=total;
        this.transacsion_id=transacsion_id;
        this.user_id=user_id;
        this.username=username;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
}
