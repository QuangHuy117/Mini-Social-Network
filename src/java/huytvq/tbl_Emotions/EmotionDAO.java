/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huytvq.tbl_Emotions;

import huytvq.utils.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author Admin
 */
public class EmotionDAO implements Serializable {

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

    public String checkEmotionStatus(String id, String email) throws SQLException, NamingException {
        String statusID = "";
        try {
            conn = DBHelper.getConnection();
            String sql = "SELECT e.Status"
                    + " FROM tbl_Emotions e, tbl_Article a"
                    + " WHERE a.ArticleID = ?"
                    + " AND a.ArticleID = e.ArticleID"
                    + " AND e.Email = ?"
                    + " AND a.StatusID = 2";
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                statusID = rs.getString("Status");
            }
        } finally {
            closeConnection();
        }
        return statusID;
    }

    public boolean addEmotion(EmotionDTO dto) throws SQLException, NamingException {

        try {
            conn = DBHelper.getConnection();
            String sql = "INSERT INTO tbl_Emotions(ArticleID, Email, Status)"
                    + " VALUES(?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, dto.getArticleID());
            ps.setString(2, dto.getEmail());
            ps.setString(3, dto.getStatus());
            int row = ps.executeUpdate();
            if (row > 0) {
                return true;
            }
        } finally {
            closeConnection();
        }
        return false;
    }

    public boolean updateEmotion(EmotionDTO dto) throws SQLException, NamingException {

        try {
            conn = DBHelper.getConnection();
            String sql = "UPDATE tbl_Emotions"
                    + " SET Status = ?"
                    + " WHERE ArticleID = ? AND Email = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, dto.getStatus());
            ps.setString(2, dto.getArticleID());
            ps.setString(3, dto.getEmail());
            int row = ps.executeUpdate();
            if (row > 0) {
                return true;
            }
        } finally {
            closeConnection();
        }
        return false;
    }

}
