/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huytvq.tbl_Article_Detail;

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
public class Article_DetailDAO implements Serializable {

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

    public boolean postNewCommentToArticleDetail(Article_DetailDTO dto) throws SQLException, NamingException {
        try {
            conn = DBHelper.getConnection();
            String sql = "INSERT INTO tbl_Article_Detail(ArticleID, CommentID)"
                    + " VALUES(?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, dto.getArticleID());
            ps.setString(2, dto.getCommentID());
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
