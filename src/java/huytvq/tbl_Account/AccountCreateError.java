/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huytvq.tbl_Account;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class AccountCreateError implements Serializable{
    
    private String emailIsExisted;

    public String getEmailIsExisted() {
        return emailIsExisted;
    }

    public void setEmailIsExisted(String emailIsExisted) {
        this.emailIsExisted = emailIsExisted;
    }
    
    
}
