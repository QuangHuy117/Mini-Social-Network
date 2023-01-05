/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huytvq.tbl_Article;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class ArticleDTO implements Serializable{
    
    private String articleID, email, username, title, description, image;
    Date createDate;
    int statusID, numOfLike, numOfDislike;

    public ArticleDTO() {
    }

    public ArticleDTO(String articleID, String email, String username, String title, String description, String image, Date createDate, int statusID, int numOfLike, int numOfDislike) {
        this.articleID = articleID;
        this.email = email;
        this.username = username;
        this.title = title;
        this.description = description;
        this.image = image;
        this.createDate = createDate;
        this.statusID = statusID;
        this.numOfLike = numOfLike;
        this.numOfDislike = numOfDislike;
    }

    public ArticleDTO(String articleID, String email, String username, String title, String description, String image, Date createDate, int statusID) {
        this.articleID = articleID;
        this.email = email;
        this.username = username;
        this.title = title;
        this.description = description;
        this.image = image;
        this.createDate = createDate;
        this.statusID = statusID;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    public int getNumOfLike() {
        return numOfLike;
    }

    public void setNumOfLike(int numOfLike) {
        this.numOfLike = numOfLike;
    }

    public int getNumOfDislike() {
        return numOfDislike;
    }

    public void setNumOfDislike(int numOfDislike) {
        this.numOfDislike = numOfDislike;
    }
    
    
}
