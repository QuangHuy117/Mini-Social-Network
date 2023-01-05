/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huytvq.tbl_Account_Info;

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
public class Account_InfoDAO implements Serializable {

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

    public Account_InfoDTO getUserInfo(String email) throws SQLException, NamingException {
        Account_InfoDTO dto = null;

        try {
            conn = DBHelper.getConnection();
            String sql = "SELECT Email, Name, RoleID, StatusID"
                    + " FROM tbl_Account_Info"
                    + " WHERE Email = ? AND (StatusID = 2 OR StatusID = 1)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                String name = rs.getString("Name");
                int roleID = rs.getInt("RoleID");
                int statusID = rs.getInt("StatusID");

                dto = new Account_InfoDTO(email, name, roleID, statusID);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public boolean createAccount_Info(Account_InfoDTO dto) throws SQLException, NamingException {
        try {
            conn = DBHelper.getConnection();
            String sql = "Insert Into tbl_Account_Info(Email, Name, RoleID, StatusID)"
                    + " Values(?,?,?,?) ";
            ps = conn.prepareStatement(sql);
            ps.setString(1, dto.getEmail());
            ps.setString(2, dto.getName());
            ps.setInt(3, dto.getRoleID());
            ps.setInt(4, dto.getStatusID());
            int row = ps.executeUpdate();
            if (row > 0) {
                return true;
            }
        } finally {
            closeConnection();
        }
        return false;
    }

    public boolean verifyAccount(String email) throws SQLException, NamingException {
        try {
            conn = DBHelper.getConnection();
            String sql = "UPDATE tbl_Account_Info"
                    + " SET StatusID = 2"
                    + " WHERE Email = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
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
