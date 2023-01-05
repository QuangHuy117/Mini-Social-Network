/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huytvq.servlets;

import huytvq.tbl_Account.AccountCreateError;
import huytvq.tbl_Account.AccountDAO;
import huytvq.tbl_Account_Info.Account_InfoDAO;
import huytvq.tbl_Account_Info.Account_InfoDTO;
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
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Admin
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ServletContext context = request.getServletContext();
        Properties siteMap = (Properties) context.getAttribute("SITE_MAP");

        String url = (String) siteMap.get(MyConstants.REGISTER_FEATURE.REGISTER_PAGE);
        String email = request.getParameter("txtReEmail").trim();
        String name = request.getParameter("txtReName").trim();
        String password = request.getParameter("txtPassword").trim();
        password = DigestUtils.sha256Hex(password);
        AccountCreateError error = new AccountCreateError();

        Account_InfoDTO infoDTO = new Account_InfoDTO();
        Account_InfoDAO infoDAO = new Account_InfoDAO();

        AccountDAO accountDAO = new AccountDAO();

        try {
            boolean check = accountDAO.createAccount(email, password);
            if (check) {
                infoDTO.setEmail(email);
                infoDTO.setName(name);
                infoDTO.setRoleID(2);
                infoDTO.setStatusID(MyConstants.STATUS_NEW);
                check = infoDAO.createAccount_Info(infoDTO);
                if (check) {
                    url = (String) siteMap.get(MyConstants.REGISTER_FEATURE.LOGIN_PAGE);
                }
            }
        } catch (NamingException e) {
            log("Error NamingException at " + this.getClass().getName() + ": " + e.getMessage());
        } catch (SQLException e) {
            String errorMsg = e.getMessage();
            log("Error SQLException at " + this.getClass().getName() + ": " + errorMsg);
            if (errorMsg.contains("duplicate")) {
                error.setEmailIsExisted("Email is Existed!!!");
                request.setAttribute("ERROR_STRING", error);
            }
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
