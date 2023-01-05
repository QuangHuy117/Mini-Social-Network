/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huytvq.tbl_Article_Detail;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class Article_DetailDTO implements Serializable{
    
    private String articleID, commentID;

    public Article_DetailDTO() {
    }

    public Article_DetailDTO(String articleID, String commentID) {
        this.articleID = articleID;
        this.commentID = commentID;
    }

    public String getArticleID() {
        return articleID;
    }

    public void setArticleID(String articleID) {
        this.articleID = articleID;
    }

    public String getCommentID() {
        return commentID;
    }

    public void setCommentID(String commentID) {
        this.commentID = commentID;
    }
    
    
    
}
