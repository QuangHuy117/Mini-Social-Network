/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huytvq.servlets;

import huytvq.tbl_Account_Info.Account_InfoDAO;
import huytvq.tbl_Account_Info.Account_InfoDTO;
import huytvq.tbl_Article.ArticleDAO;
import huytvq.tbl_Article.ArticleDTO;
import huytvq.tbl_Article_Detail.Article_DetailDAO;
import huytvq.tbl_Article_Detail.Article_DetailDTO;
import huytvq.tbl_Comments.CommentDAO;
import huytvq.tbl_Comments.CommentDTO;
import huytvq.utils.MyConstants;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
@WebServlet(name = "PostCommentServlet", urlPatterns = {"/PostCommentServlet"})
public class PostCommentServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        ServletContext context = request.getServletContext();
        Properties siteMap = (Properties) context.getAttribute("SITE_MAP");

        String url = (String) siteMap.get(MyConstants.POST_COMMENT_FEATURE.VIEW_DETAIL_ACTION);
        HttpSession session = request.getSession();
        Account_InfoDTO dto = (Account_InfoDTO) session.getAttribute("USER");
        String articleID = request.getParameter("txtArticleID");
        String comment = request.getParameter("txtComment");

        try {
            Calendar calendar = Calendar.getInstance();
            Date createDate = calendar.getTime();
            CommentDAO commentDAO = new CommentDAO();
            Account_InfoDAO userDAO = new Account_InfoDAO();
            ArticleDAO articleDAO = new ArticleDAO();
            ArticleDTO articleDTO = articleDAO.getArticleByID(articleID);
            Account_InfoDTO user = userDAO.getUserInfo(dto.getEmail());

            if (articleDTO != null) {
                if (user != null) {
                    String newestCommentID = commentDAO.getNewestComment();
                    String commentID = "";
                    if (newestCommentID.equals("")) {
                        commentID = "CM-" + "1";
                    } else {
                        String[] tmp = newestCommentID.trim().split("-");
                        commentID = "CM-" + (Integer.parseInt(tmp[1]) + 1);
                    }
                    CommentDTO commentDTO = new CommentDTO();
                    commentDTO.setCommentID(commentID);
                    commentDTO.setComment(comment);
                    commentDTO.setEmail(dto.getEmail());
                    commentDTO.setDate(createDate);
                    commentDTO.setStatusID(2);
                    boolean checkPost = commentDAO.postNewComment(commentDTO);
                    if (checkPost) {
                        Article_DetailDAO articleDetailDAO = new Article_DetailDAO();
                        Article_DetailDTO articleDetailDTO = new Article_DetailDTO();
                        articleDetailDTO.setArticleID(articleID);
                        articleDetailDTO.setCommentID(commentID);
                        boolean checkPostDetail = articleDetailDAO.postNewCommentToArticleDetail(articleDetailDTO);
                        if (checkPostDetail) {
//                            url = (String) siteMap.get(MyConstants.DELETE_COMMENT_FEATURE.VIEW_DETAIL_ACTION);
                            url += "?txtArticleID=" + articleID;
                            response.sendRedirect(url);
                        }
                    }
                } else {
                    String error = "Account has been deleted!!!";
                    request.setAttribute("ERROR_STRING", error);
                    RequestDispatcher rd = request.getRequestDispatcher(url);
                    rd.forward(request, response);
                }
            }
            url += "?txtArticleID=" + articleID;
            response.sendRedirect(url);
        } catch (NamingException e) {
            log("Error NamingException at " + this.getClass().getName() + ": " + e.getMessage());
        } catch (SQLException e) {
            log("Error SQLException at " + this.getClass().getName() + ": " + e.getMessage());
        } catch (Exception e) {
            log("Error Exception at " + this.getClass().getName() + ": " + e.getMessage());
        } 
//        finally {
//
//        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
