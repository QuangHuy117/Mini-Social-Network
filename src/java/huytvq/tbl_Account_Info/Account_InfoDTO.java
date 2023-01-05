/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huytvq.tbl_Account_Info;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class Account_InfoDTO implements Serializable{
    
    private String email, name;
    int roleID, statusID;

    public Account_InfoDTO() {
    }

    public Account_InfoDTO(String email, String name, int roleID, int statusID) {
        this.email = email;
        this.name = name;
        this.roleID = roleID;
        this.statusID = statusID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }
    
    
}
