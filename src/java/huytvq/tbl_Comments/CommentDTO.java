/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huytvq.tbl_Comments;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class CommentDTO implements Serializable {

    private String commentID, comment, email, username;
    Date date;
    int statusID;

    public CommentDTO() {
    }

    public CommentDTO(String commentID, String comment, String email, String username, Date date, int statusID) {
        this.commentID = commentID;
        this.comment = comment;
        this.email = email;
        this.username = username;
        this.date = date;
        this.statusID = statusID;
    }

    public String getCommentID() {
        return commentID;
    }

    public void setCommentID(String commentID) {
        this.commentID = commentID;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
