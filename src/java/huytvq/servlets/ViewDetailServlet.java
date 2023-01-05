/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huytvq.servlets;

import huytvq.tbl_Account_Info.Account_InfoDTO;
import huytvq.tbl_Article.ArticleDAO;
import huytvq.tbl_Article.ArticleDTO;
import huytvq.tbl_Comments.CommentDAO;
import huytvq.tbl_Comments.CommentDTO;
import huytvq.tbl_Emotions.EmotionDAO;
import huytvq.utils.MyConstants;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
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
@WebServlet(name = "ViewDetailServlet", urlPatterns = {"/ViewDetailServlet"})
public class ViewDetailServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ServletContext context = request.getServletContext();
        Properties siteMap = (Properties) context.getAttribute("SITE_MAP");
        HttpSession session = request.getSession();
        Account_InfoDTO dto = (Account_InfoDTO) session.getAttribute("USER");

        String url = (String) siteMap.get(MyConstants.VIEW_DETAIL_FEATURE.VIEW_DETAIL_PAGE);
        String articleID = request.getParameter("txtArticleID");
        try {
            EmotionDAO emoDAO = new EmotionDAO();
            ArticleDAO dao = new ArticleDAO();
            CommentDAO commentDAO = new CommentDAO();
            ArticleDTO articleDTO = dao.getArticleByID(articleID);
            if (articleDTO != null) {
                List<CommentDTO> listComment = commentDAO.getListCommentByArticleID(articleID);
                String status = emoDAO.checkEmotionStatus(articleDTO.getArticleID(), dto.getEmail()).trim();
                int numberComment = commentDAO.countNumberOfComment(articleID);
                request.setAttribute("NUMBER_COMMENT", numberComment);
                request.setAttribute("ARTICLE_DETAIL", articleDTO);
                request.setAttribute("LIST_CMT", listComment);
                request.setAttribute("LIKE_STATUS", status);
            }
        } catch (NamingException e) {
            log("Error NamingException at " + this.getClass().getName() + ": " + e.getMessage());
        } catch (SQLException e) {
            log("Error SQLException at " + this.getClass().getName() + ": " + e.getMessage());
        } catch (Exception e) {
            log("Error Exception at " + this.getClass().getName() + ": " + e.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
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
