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
import huytvq.tbl_Emotions.EmotionDAO;
import huytvq.tbl_Emotions.EmotionDTO;
import huytvq.utils.MyConstants;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
@WebServlet(name = "MakeEmotionsServlet", urlPatterns = {"/MakeEmotionsServlet"})
public class MakeEmotionsServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        ServletContext context = request.getServletContext();
        Properties siteMap = (Properties) context.getAttribute("SITE_MAP");

        String url = (String) siteMap.get(MyConstants.MAKE_EMOTION_FEATURE.VIEW_DETAIL_ACTION);
        HttpSession session = request.getSession();
        Account_InfoDTO user = (Account_InfoDTO) session.getAttribute("USER");
        String status = request.getParameter("txtStatus");
        String articleID = request.getParameter("txtArticleID");

        try {
            EmotionDAO emotionDAO = new EmotionDAO();
            ArticleDAO articleDAO = new ArticleDAO();
            Account_InfoDAO userDAO = new Account_InfoDAO();
            ArticleDTO articleDTO = articleDAO.getArticleByID(articleID);
            Account_InfoDTO dto = userDAO.getUserInfo(user.getEmail());
            if (articleDTO != null) {
                if (dto != null) {
                    String tmpStatus = emotionDAO.checkEmotionStatus(articleID, user.getEmail()).trim();
                    if (tmpStatus != null && !tmpStatus.isEmpty()) {
                        if (tmpStatus.equals(status)) {
                            emotionDAO.updateEmotion(new EmotionDTO(articleID, user.getEmail(), "None"));
                            if (status.equals("Like")) {
                                status = "UnLike";
                            } else {
                                status = "UnDislike";
                            }
                        } else {
                            emotionDAO.updateEmotion(new EmotionDTO(articleID, user.getEmail(), status));
                            if (status.equals("Like") && !tmpStatus.equals("None")) {
                                status = "DislikeToLike";
                            } else if (status.equals("Dislike") && !tmpStatus.equals("None")) {
                                status = "LikeToDislike";
                            }
                        }
                    } else {
                        emotionDAO.addEmotion(new EmotionDTO(articleID, user.getEmail(), status));
                    }
                    articleDTO = articleDAO.getArticleByID(articleID);
                    boolean checkUpdateStatus = articleDAO.updateArticleEmotion(articleDTO, status);
                    if (checkUpdateStatus) {
                        url += "?txtArticleID=" + articleID;
                        response.sendRedirect(url);
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
//            RequestDispatcher rd = request.getRequestDispatcher(url);
//            rd.forward(request, response);
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
