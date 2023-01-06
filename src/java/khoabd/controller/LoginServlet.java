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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import khoabd.account.AccountDAO;
import khoabd.account.AccountDTO;
import khoabd.account.AccountLoginError;
import khoabd.utils.DBHelper;
import khoabd.utils.MyApplicationContanst;
import khoabd.utils.MyApplicationContanst.LoginServletConstant;

/**
 *
 * @author phong
 */
public class LoginServlet extends HttpServlet {

//    private final String INVALID_PAGE = "invalid.html";
//    private final String INVALID_PAGE = "invalidPage";
//    private final String SEARCH_PAGE = "search.html";
//    private final String SEARCH_PAGE = "search.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = DBHelper.getUrl(this.getServletContext(),
                LoginServletConstant.FIRST_TIME_REQUEST.value);
        HttpSession session = request.getSession(true);
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");

        try {
            //check: Have USER in session and cookie
            if (session != null) {
                AccountDTO user = (AccountDTO) session.getAttribute("USER");
                if (user != null) {
                    return;
                }
            }
            System.out.println("login servlet running");
            // Call Model/DAO - new object & call mesthod
            AccountDAO dao = new AccountDAO();
//            boolean result = accoundDAO.checkLogin(username, password);
            AccountDTO result = dao.checkLogin(username, password);
            if (result == null) {
                //user isnt existed
                AccountLoginError error = new AccountLoginError();
                error.setUsernameOrPasswordNotValid("Username or Password is wrong");
                request.setAttribute("LOGIN_ERROR", error);
                url = DBHelper.getUrl(this.getServletContext(),
                        LoginServletConstant.LOGIN_JSP_PAGE.value);
                return;
            }
            //call dao login is successfully
            
            //add account to cookie 
            Cookie cookie = new Cookie(username, password);
            cookie.setMaxAge(60 * 3);//second
            response.addCookie(cookie);
            //add account to session
            session.setAttribute("USER", result);
            if (!result.isRole()) {
                url = DBHelper.getUrl(this.getServletContext(),
                   LoginServletConstant.SHOPPING_VIEW_CONTROLLER.value);
            } else {
                url = DBHelper.getUrl(this.getServletContext(),
                    LoginServletConstant.SEARCH_JSP_PAGE.value);
            }
        } catch (SQLException ex) {
            log("error at LoginServlet  _SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("error at LoginServlet  _Naming: " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
//            response.sendRedirect(url);
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
