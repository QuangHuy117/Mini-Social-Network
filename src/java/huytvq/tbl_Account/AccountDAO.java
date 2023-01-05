/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huytvq.tbl_Account;

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
public class AccountDAO implements Serializable {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

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

    public boolean checkAccount(String email, String password) throws SQLException, NamingException {
        try {
            conn = DBHelper.getConnection();
            String sql = "SELECT Email, Password"
                    + " FROM tbl_Account"
                    + " WHERE Email = ? AND Password = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } finally {
            closeConnection();
        }
        return false;
    }

    public boolean createAccount(String email, String password) throws SQLException, NamingException {
        try {
            conn = DBHelper.getConnection();
            String sql = "Insert Into tbl_Account(Email, Password)"
                    + " Values(?,?) ";
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
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
