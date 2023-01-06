/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoabd.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import khoabd.account.AccountDAO;
import khoabd.account.AccountDTO;
import khoabd.error.AuthorizationError;
import khoabd.utils.DBHelper;
import khoabd.utils.MyApplicationContanst.DeleteAccountServletConstant;

/**
 *
 * @author Admin
 */
@WebServlet(name = "DeleteAccountServlet", urlPatterns = {"/DeleteAccountServlet"})
public class DeleteAccountServlet extends HttpServlet {

//    private final String ERROR_PAGE = "error.html";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        //define error var
        AuthorizationError authorError = null;
        boolean authorErrFound = false;
        //define var
        AccountDTO user = null;
        HttpSession session = request.getSession(false);
        boolean resultStatus = false;
        String url = DBHelper.getUrl(this.getServletContext(),
                DeleteAccountServletConstant.LOGIN_PAGE.value);
        //define var get from req param
        String pk = request.getParameter("pk");
        String lastSearchValue = request.getParameter("lastSearchValue");

        try {
            //check: must login and has role is admin
            if (session == null) { // authentication check
                authorErrFound = true;
            } else {
                user = (AccountDTO) session.getAttribute("USER");
                if (user == null) {
                    authorErrFound = true;
                } else if (user.isRole() == false) { //authorization check
                    authorErrFound = true;
                }
            }
            //have author error or not
            if (authorErrFound) {
                authorError = new AuthorizationError();
                authorError.setAuthorError("You are not authorized to access!!!");
                request.setAttribute("AUTHOR_ERROR", authorError);
                return;
            } else {
                //user can't delete myself
                if (!pk.equals(user.getUsername())) {
                    //1.call dao
                    AccountDAO dao = new AccountDAO();
                    resultStatus = dao.deleteAccount(pk);
                } else {
                    resultStatus = true;
                }
            }
            //2.delete successfully and call SearchFunc
            if (resultStatus) {
                url = DBHelper.getUrl(this.getServletContext(),
                        DeleteAccountServletConstant.SEARCH_CONTROLLER.value)
                        + "?txtSearchValue=" + lastSearchValue;
            }
        } catch (SQLException ex) {
            log("Error at DeleteAccountServlet _SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("Error at DeleteAccountServlet Naming: " + ex.getMessage());
        } finally {
            response.sendRedirect(url);
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
