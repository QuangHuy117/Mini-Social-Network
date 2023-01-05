/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huytvq.servlets;

import huytvq.tbl_Account.AccountDAO;
import huytvq.tbl_Account_Info.Account_InfoDAO;
import huytvq.tbl_Account_Info.Account_InfoDTO;
import huytvq.utils.MyConstants;
import huytvq.utils.SendMailUtil;
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
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Admin
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        ServletContext context = request.getServletContext();
        Properties siteMap = (Properties) context.getAttribute("SITE_MAP");

        String email = request.getParameter("txtEmail").trim();
        String password = request.getParameter("txtPassword").trim();
        String url = (String) siteMap.get(MyConstants.LOGIN_FEATURE.LOGIN_PAGE);
        boolean sendCode;
        Account_InfoDTO dto = null;
        try {
            password = DigestUtils.sha256Hex(password);
            AccountDAO accountDAO = new AccountDAO();
            boolean check = accountDAO.checkAccount(email, password);
            Account_InfoDAO infoDAO = new Account_InfoDAO();
            dto = infoDAO.getUserInfo(email);
            if (check && dto != null) {
                if (dto.getStatusID() == MyConstants.STATUS_ACTIVE) {
                    HttpSession session = request.getSession();
                    session.setAttribute("USER", dto);
                    response.sendRedirect((String) siteMap.get(MyConstants.LOGIN_FEATURE.HOME_PAGE_ACTION));
                    return;
                }
                if (dto.getStatusID() == MyConstants.STATUS_NEW) {
                    sendCode = true;
                    HttpSession session = request.getSession();
                    session.setAttribute("EMAIL", dto.getEmail());
                    url = (String) siteMap.get(MyConstants.LOGIN_FEATURE.VERIFY_PAGE_HTML);
                    RequestDispatcher rd = request.getRequestDispatcher(url);
                    rd.forward(request, response);
                    if (sendCode) {
                        SendMailUtil sm = new SendMailUtil();
                        String code = sm.getRandomCode();
                        if (code.length() == 6) {
                            boolean send = sm.sendMail(email, Integer.parseInt(code));
                            if (send) {
                                session.setAttribute("VERIFY_CODE", code);
                            }
                        }
                    }
                    return;
                }
            } else {
                String error = "Email or Password invalid!";
                request.setAttribute("ERROR_STRING", error);
            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } catch (NamingException e) {
            log("Error NamingException at " + this.getClass().getName() + ": " + e.getMessage());
        } catch (SQLException e) {
            log("Error SQLException at " + this.getClass().getName() + ": " + e.getMessage());
        } catch (Exception e) {
            log("Error Exception at " + this.getClass().getName() + ": " + e.getMessage());
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
