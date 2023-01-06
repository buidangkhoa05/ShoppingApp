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
import khoabd.account.AccountCreateError;
import khoabd.account.AccountDAO;
import khoabd.account.AccountDTO;
import khoabd.utils.DBHelper;
import khoabd.utils.MyApplicationContanst.CreateNewAccountServletConstant;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CreateNewAccountServlet", urlPatterns = {"/CreateNewAccountServlet"})
public class CreateNewAccountServlet extends HttpServlet {

//    private final String LOGIN_PAGE = "login.html";
//    private final String CREATE_ACCOUNT_VIEW = "createNewAccount.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String fullname = request.getParameter("txtFullnname");
        String confirm = request.getParameter("txtConfirm");
        
        String url = DBHelper.getUrl(this.getServletContext(), 
                CreateNewAccountServletConstant.CREATE_ACCOUNT_PAGE.value);

        boolean errFound = false;
        AccountCreateError errors = new AccountCreateError();

        try {
            //1.check all user err -> 4err
            if (username.trim().length() < 6 || username.trim().length() > 20) {
                errFound = true;
                errors.setUserNammeLengthError("Username is required input from 6 to 20 character");
            }
            if (password.trim().length() < 6 || password.trim().length() > 20) {
                errFound = true;
                errors.setPasswordLengthError("Password is required input from  6 to 20 character");
            }
            if (fullname.trim().length() < 6 || fullname.trim().length() > 20) {
                errFound = true;
                errors.setFullnameLenghtError("Fullname is required input from  6 to 20 character");
            }
            if (!confirm.trim().equals(password.trim())) {
                errFound = true;
                errors.setCofirmNotMatch("Confirm must be match password");
            }
            if (errFound) {
                //1.1 cache error
                request.setAttribute("CREATE_ERRORS", errors);
                //1.2 tranfer to inform users
                url = DBHelper.getUrl(this.getServletContext(), 
                        CreateNewAccountServletConstant.CREATE_ACCOUNT_JSP_PAGE.value);
            } else {
                //2 call dao
                AccountDAO dao = new AccountDAO();
                AccountDTO dto = new AccountDTO(username, password, fullname, false);
                boolean result = dao.createAccounnt(dto);
                if (result) {
                    url = DBHelper.getUrl(this.getServletContext(), 
                CreateNewAccountServletConstant.START_PAGE.value);
                }
            }
        } catch (SQLException ex) {
            String msg = ex.getMessage();
            log("create account _SQL " + msg);
            if (msg.contains("duplicate")) {
                errors.setUsernameIsExisted(username + " is existed");
                request.setAttribute("CREATE_ERROR", errors);
            }
        } catch (NamingException ex) {
            log("create account _Naming " + ex.getMessage());
        } catch (Exception ex) {
            log("Create account error: " + ex.getMessage());
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
