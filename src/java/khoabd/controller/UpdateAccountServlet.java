/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoabd.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import khoabd.account.AccountDAO;
import khoabd.account.AccountDTO;
import khoabd.account.AccountUpdateError;
import khoabd.error.AuthorizationError;
import khoabd.utils.DBHelper;
import khoabd.utils.MyApplicationContanst;
import khoabd.utils.MyApplicationContanst.UpdateAccountServletConstant;

/**
 *
 * @author Admin
 */
@WebServlet(name = "UpdateAccountServlet", urlPatterns = {"/UpdateAccountServlet"})
public class UpdateAccountServlet extends HttpServlet {

//    private String ERROR_PAGE = "errorUpdate.html";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        //define var
        HttpSession session = request.getSession(false);
        AccountDTO user = null;
        //define error 
        boolean authorErrFound = false;
        boolean errFound = false;
        AccountUpdateError errors = null;
        AuthorizationError authorError = null;
         boolean resultStatus = false;
        //define var get from req parameter to update
        boolean isAdmin = false;
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String fullname = request.getParameter("txtFullname");
        String checkAdmin = request.getParameter("checkAdmin");
        String lastSearchValue = request.getParameter("lastSearchValue");
        //define url
        String url = DBHelper.getUrl(this.getServletContext(),
                UpdateAccountServletConstant.SEARCH_CONTROLLER.value)
                + "?txtSearchValue=" + lastSearchValue;

        try {
            //check: must login and has role is admin
            if (session == null) { //authentication check
                authorErrFound = true;
            } else {
                user = (AccountDTO) session.getAttribute("USER");
                if (user == null) {
                    authorErrFound = true;
                } else if (user.isRole() == false) { //authorization check
                    authorErrFound = true;
                }
            }
            if (authorErrFound) {
                authorError = new AuthorizationError();
                authorError.setAuthorError("You are not authorized to access!!!");
                request.setAttribute("AUTHOR_ERROR", authorError);
                url = DBHelper.getUrl(this.getServletContext(),
                MyApplicationContanst.UpdateAccountServletConstant.LOGIN_JSP_PAGE.value);
                return;
            }
            //validation input for update
            if (password.trim().length() < 6
                    || password.trim().length() > 20) {
                errFound = true;
                if (errors == null) {
                    errors = new AccountUpdateError();
                }
                errors.setPasswordLengthError("Password is required input from  6 to 20 character");
            }
            if (fullname.trim().length() < 6
                    || fullname.trim().length() > 20) {
                errFound = true;
                if (errors == null) {
                    errors = new AccountUpdateError();
                }
                errors.setFullnameLenghtError("Fullname is required input from  6 to 20 character");
            }
            //check: have user error or not 
            if (errFound) {
                request.setAttribute("UPDATE_ERROR", errors);
                url = DBHelper.getUrl(this.getServletContext(),
                        UpdateAccountServletConstant.UPDATE_JSP_PAGE.value);
                return;
            }
            //convert  checked param var to boolean var  
            if (checkAdmin != null) {
                isAdmin = true;
            }
            //call dao to updte
            AccountDAO dao = new AccountDAO();
             resultStatus = dao.update(username, password, fullname, isAdmin);
            if (resultStatus) {
                //url rewritting for recall search func
                url = DBHelper.getUrl(this.getServletContext(),
                        UpdateAccountServletConstant.SEARCH_CONTROLLER.value)
                        + "?txtSearchValue=" + lastSearchValue;
            }
        } catch (SQLException ex) {
            log("Error at UpdateAccountServlet _SQL:" + ex.getMessage());
        } catch (NamingException ex) {
            log("Error at UpdateAccountServlet _Naming:" + ex.getMessage());
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
