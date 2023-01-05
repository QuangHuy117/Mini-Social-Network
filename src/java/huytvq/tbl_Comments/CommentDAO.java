/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huytvq.tbl_Comments;

import huytvq.utils.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author Admin
 */
public class CommentDAO implements Serializable {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    private void closeConnection() throws SQLException, NamingException {
        if (rs != null) {
            rs.close();
        }
        if (ps != null) {
            ps.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public List<CommentDTO> getListCommentByArticleID(String articleID) throws SQLException, NamingException {
        List<CommentDTO> list = null;
        CommentDTO dto = null;

        try {
            conn = DBHelper.getConnection();
            String sql = "SELECT c.CommentID, c.Comment, c.Email, c.CreateDate, c.StatusID, (SELECT a.Name"
                    + "	FROM tbl_Account_Info a"
                    + "	WHERE a.Email = c.Email) AS Username"
                    + " FROM tbl_Article a, tbl_Article_Detail ac, tbl_Comments c"
                    + " WHERE a.ArticleID = ac.ArticleID"
                    + " AND ac.CommentID = c.CommentID"
                    + " AND a.ArticleID = ?"
                    + " AND c.StatusID = 2"
                    + " ORDER BY c.CreateDate DESC";
            ps = conn.prepareStatement(sql);
            ps.setString(1, articleID);
            rs = ps.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                String commentID = rs.getString("CommentID");
                String comment = rs.getString("Comment");
                String email = rs.getString("email");
                Date date = rs.getDate("CreateDate");
                int statusID = rs.getInt("StatusID");
                String username = rs.getString("Username");

                dto = new CommentDTO(commentID, comment, email, username, date, statusID);
                list.add(dto);
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public int countNumberOfComment(String articleID) throws SQLException, NamingException {
        int count = 0;
        try {
            conn = DBHelper.getConnection();
            String sql = "SELECT COUNT(ac.CommentID)"
                    + " FROM tbl_Article a, tbl_Article_Detail ac, tbl_Comments c"
                    + " WHERE a.ArticleID = ac.ArticleID"
                    + " AND ac.CommentID = c.CommentID"
                    + " AND a.ArticleID = ?"
                    + " AND c.StatusID = 2";
            ps = conn.prepareStatement(sql);
            ps.setString(1, articleID);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } finally {
            closeConnection();
        }
        return count;
    }

    public boolean deleteComment(String commentID) throws SQLException, NamingException {

        try {
            conn = DBHelper.getConnection();
            String sql = "UPDATE tbl_Comments"
                    + " SET StatusID = 5"
                    + " WHERE CommentID = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, commentID);
            int row = ps.executeUpdate();
            if (row > 0) {
                return true;
            }
        } finally {
            closeConnection();
        }
        return false;
    }

    public boolean postNewComment(CommentDTO dto) throws SQLException, NamingException {

        try {
            conn = DBHelper.getConnection();
            String sql = "INSERT INTO tbl_Comments(CommentID, Comment, Email, CreateDate, StatusID)\n"
                    + " VALUES(?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, dto.getCommentID());
            ps.setString(2, dto.getComment());
            ps.setString(3, dto.getEmail());
            ps.setTimestamp(4, new Timestamp(dto.getDate().getTime()));
            ps.setInt(5, dto.getStatusID());
            int row = ps.executeUpdate();
            if (row > 0) {
                return true;
            }
        } finally {
            closeConnection();
        }
        return false;
    }

    public String getNewestComment() throws SQLException, NamingException {
        String commentID = "";
        try {
            conn = DBHelper.getConnection();
            String sql = "SELECT CommentID"
                    + " FROM tbl_Comments"
                    + " WHERE CreateDate = (SELECT MAX(CreateDate)"
                    + "	FROM tbl_Comments)";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                commentID = rs.getString(1);
            }
        } finally {
            closeConnection();
        }
        return commentID;
    }

}
