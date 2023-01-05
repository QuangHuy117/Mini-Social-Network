/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huytvq.tbl_Article;

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
public class ArticleDAO implements Serializable {

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

    public List<ArticleDTO> getAllArticleByEmail(String email) throws SQLException, NamingException {
        List<ArticleDTO> list = null;
        ArticleDTO dto = null;
        try {
            conn = DBHelper.getConnection();
            String sql = "SELECT ArticleID, Title, Article_Description, Image, CreateDate, NumOfLike, NumOfDislike, StatusID, (SELECT Name"
                    + "	FROM tbl_Account_Info ac"
                    + "	WHERE a.Email = ac.Email) AS UserName"
                    + " FROM tbl_Article a"
                    + " WHERE Email = ? AND a.StatusID = 2";
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                String articleID = rs.getString("ArticleID");
                String title = rs.getString("Title");
                String description = rs.getString("Article_Description");
                String image = rs.getString("Image");
                Date createDate = rs.getDate("CreateDate");
                int numOfLike = rs.getInt("NumOfLike");
                int numOfDislike = rs.getInt("NumOfDislike");
                int statusID = rs.getInt("StatusID");
                String name = rs.getString("UserName");

                dto = new ArticleDTO(articleID, email, name, title, description, image, createDate, statusID, numOfLike, numOfDislike);
                list.add(dto);
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public List<ArticleDTO> getListArticle(int curPage, int totalItemInPage) throws SQLException, NamingException {
        List<ArticleDTO> list = null;
        ArticleDTO dto = null;
        try {
            conn = DBHelper.getConnection();
            String sql = "DECLARE @totalItem AS int = ?"
                    + " SELECT ArticleID, Email, Title, Article_Description, Image, CreateDate, NumOfLike, NumOfDislike, StatusID, (SELECT Name"
                    + "	FROM tbl_Account_Info ac"
                    + "	WHERE a.Email = ac.Email) AS UserName"
                    + " FROM tbl_Article a"
                    + " WHERE StatusID = 2"
                    + " ORDER BY CreateDate DESC"
                    + " OFFSET (? - 1) * @totalItem ROWS"
                    + " FETCH NEXT @totalItem ROWS ONLY;";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, totalItemInPage);
            ps.setInt(2, curPage);
            rs = ps.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                String articleID = rs.getString("ArticleID");
                String email = rs.getString("Email");
                String title = rs.getString("Title");
                String description = rs.getString("Article_Description");
                String image = rs.getString("Image");
                Date createDate = rs.getDate("CreateDate");
                int numOfLike = rs.getInt("NumOfLike");
                int numOfDislike = rs.getInt("NumOfDislike");
                int statusID = rs.getInt("StatusID");
                String name = rs.getString("UserName");

                dto = new ArticleDTO(articleID, email, name, title, description, image, createDate, statusID, numOfLike, numOfDislike);
                list.add(dto);
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public ArticleDTO getArticleByID(String id) throws SQLException, NamingException {
        ArticleDTO dto = null;
        try {
            conn = DBHelper.getConnection();
            String sql = "SELECT ArticleID, Email, Title, Article_Description, Image, CreateDate, NumOfLike, NumOfDislike, StatusID, (SELECT Name"
                    + "	FROM tbl_Account_Info ac"
                    + "	WHERE a.Email = ac.Email) AS UserName"
                    + " FROM tbl_Article a"
                    + " WHERE ArticleID = ? AND StatusID = 2";
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                String articleID = rs.getString("ArticleID");
                String email = rs.getString("Email");
                String title = rs.getString("Title");
                String description = rs.getString("Article_Description");
                String image = rs.getString("Image");
                Date createDate = rs.getDate("CreateDate");
                int numOfLike = rs.getInt("NumOfLike");
                int numOfDislike = rs.getInt("NumOfDislike");
                int statusID = rs.getInt("StatusID");
                String name = rs.getString("UserName");

                dto = new ArticleDTO(articleID, email, name, title, description, image, createDate, statusID, numOfLike, numOfDislike);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public boolean checkArticleStatus(String id) throws SQLException, NamingException {
        try {
            conn = DBHelper.getConnection();
            String sql = "SELECT StatusID"
                    + " FROM tbl_Article"
                    + " WHERE ArticleID = ? AND StatusID = 2";
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } finally {
            closeConnection();
        }
        return false;
    }

    public boolean updateArticleEmotion(ArticleDTO dto, String status) throws SQLException, NamingException {

        try {
            if (status.equals("Like")) {
                dto.setNumOfLike(dto.getNumOfLike() + 1);
            } else if (status.equals("Dislike")) {
                dto.setNumOfDislike(dto.getNumOfDislike() + 1);
            } else if (status.equals("UnLike")) {
                dto.setNumOfLike(dto.getNumOfLike() - 1);
            } else if (status.equals("UnDislike")) {
                dto.setNumOfDislike(dto.getNumOfDislike() - 1);
            } else if (status.equals("LikeToDislike")) {
                dto.setNumOfLike(dto.getNumOfLike() - 1);
                dto.setNumOfDislike(dto.getNumOfDislike() + 1);
            } else if (status.equals("DislikeToLike")) {
                dto.setNumOfDislike(dto.getNumOfDislike() - 1);
                dto.setNumOfLike(dto.getNumOfLike() + 1);
            }
            conn = DBHelper.getConnection();
            String sql = "UPDATE tbl_Article "
                    + " SET NumOfLike = ?, NumOfDislike = ?"
                    + " WHERE ArticleID = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, dto.getNumOfLike());
            ps.setInt(2, dto.getNumOfDislike());
            ps.setString(3, dto.getArticleID());
            int row = ps.executeUpdate();
            if (row > 0) {
                return true;
            }
        } finally {
            closeConnection();
        }
        return false;
    }

    public boolean deleteArticle(String id) throws SQLException, NamingException {

        try {
            conn = DBHelper.getConnection();
            String sql = "UPDATE tbl_Article"
                    + " SET StatusID = 5"
                    + " WHERE ArticleID = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            int row = ps.executeUpdate();
            if (row > 0) {
                return true;
            }
        } finally {
            closeConnection();
        }
        return false;
    }

    public int countArticle() throws SQLException, NamingException {
        int count = 0;
        try {
            conn = DBHelper.getConnection();
            String sql = "SELECT COUNT(ArticleID)"
                    + " FROM tbl_Article"
                    + " WHERE StatusID = 2";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } finally {
            closeConnection();
        }
        return count;
    }

    public List<ArticleDTO> searchListArticle(String search, int curPage, int totalItem) throws SQLException, NamingException {
        List<ArticleDTO> list = null;
        ArticleDTO dto = null;
        try {
            conn = DBHelper.getConnection();
            String sql = "DECLARE @totalItem AS int = ?"
                    + " SELECT ArticleID, Email, Title, Article_Description, Image, CreateDate, NumOfLike, NumOfDislike, StatusID, (SELECT Name"
                    + "	FROM tbl_Account_Info ac"
                    + "	WHERE a.Email = ac.Email) AS UserName"
                    + " FROM tbl_Article a"
                    + " WHERE a.Article_Description LIKE ? AND StatusID = 2"
                    + " ORDER BY CreateDate DESC"
                    + " OFFSET (? - 1) * @totalItem ROWS"
                    + " FETCH NEXT @totalItem ROWS ONLY;";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, totalItem);
            ps.setString(2, "%" + search + "%");
            ps.setInt(3, curPage);
            rs = ps.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                String articleID = rs.getString("ArticleID");
                String email = rs.getString("Email");
                String title = rs.getString("Title");
                String description = rs.getString("Article_Description");
                String image = rs.getString("Image");
                Date createDate = rs.getDate("CreateDate");
                int numOfLike = rs.getInt("NumOfLike");
                int numOfDislike = rs.getInt("NumOfDislike");
                int statusID = rs.getInt("StatusID");
                String name = rs.getString("UserName");

                dto = new ArticleDTO(articleID, email, name, title, description, image, createDate, statusID, numOfLike, numOfDislike);

                list.add(dto);
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public int countSearchArticle(String search) throws SQLException, NamingException {
        int count = 0;
        try {
            conn = DBHelper.getConnection();
            String sql = "SELECT COUNT(ArticleID)"
                    + " FROM tbl_Article"
                    + " WHERE Article_Description LIKE ? AND StatusID = 2";
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + search + "%");
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } finally {
            closeConnection();
        }
        return count;
    }

    public boolean createArticle(ArticleDTO dto) throws SQLException, NamingException {

        try {
            conn = DBHelper.getConnection();
            String sql = "INSERT INTO tbl_Article(ArticleID, Email, Title, Article_Description, Image, CreateDate, StatusID)"
                    + " VALUES(?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, dto.getArticleID());
            ps.setString(2, dto.getEmail());
            ps.setString(3, dto.getTitle());
            ps.setString(4, dto.getDescription());
            ps.setString(5, dto.getImage());
            ps.setTimestamp(6, new Timestamp(dto.getCreateDate().getTime()));
            ps.setInt(7, dto.getStatusID());
            int row = ps.executeUpdate();
            if (row > 0) {
                return true;
            }
        } finally {
            closeConnection();
        }
        return false;
    }

    public String getNewestArticle() throws SQLException, NamingException {
        String articleID = "";
        try {
            conn = DBHelper.getConnection();
            String sql = "SELECT ArticleID"
                    + " FROM tbl_Article"
                    + " WHERE CreateDate = (SELECT MAX(CreateDate)"
                    + "	FROM tbl_Article)";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                articleID = rs.getString(1);
            }
        } finally {
            closeConnection();
        }
        return articleID;
    }

}
