/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huytvq.tbl_Emotions;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class EmotionDTO implements Serializable{
    
    private String articleID, email, status;

    public EmotionDTO() {
    }

    public EmotionDTO(String articleID, String email, String status) {
        this.articleID = articleID;
        this.email = email;
        this.status = status;
    }

    public String getArticleID() {
        return articleID;
    }

    public void setArticleID(String articleID) {
        this.articleID = articleID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
