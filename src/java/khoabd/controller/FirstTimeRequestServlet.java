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
import khoabd.utils.DBHelper;
import khoabd.utils.MyApplicationContanst.FirstTimeRequestServletConstant;

/**
 *
 * @author Admin
 */
@WebServlet(name = "FirstTimeRequestServlet", urlPatterns = {"/FirstTimeRequestServlet"})
public class FirstTimeRequestServlet extends HttpServlet {

//    private final String LOGIN_PAGE = "login.html";
//    private final String SEARCH_PAGE = "search.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = DBHelper.getUrl(this.getServletContext(),
                FirstTimeRequestServletConstant.LOGIN_PAGE.value);

        try {
            //1. get cookies
            Cookie[] cookies = request.getCookies();
            //2. read last cookies
            if (cookies != null) {
                Cookie lastCookie = cookies[cookies.length - 1];
                String username = lastCookie.getName();
                String password = lastCookie.getValue();
                //3.call dao check login
                AccountDAO dao = new AccountDAO();
                AccountDTO result = dao.checkLogin(username, password);
                //4. process
                if (result != null) {
                    if (result.isRole()) {
                        url = DBHelper.getUrl(this.getServletContext(),
                            FirstTimeRequestServletConstant.SEARCH_JSP_PAGE.value);
                    } else {
                    url = DBHelper.getUrl(this.getServletContext(),
                            FirstTimeRequestServletConstant.SHOPPING_VIEW_CONTROLLER.value);
                    }
                    HttpSession session = request.getSession();
                    session.setAttribute("USER", result);
                }
            }
        } catch (SQLException ex) {
            log("Error at FirstTimeRequestServlet _SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("Error at FirstTimeRequestServlet _Naming: " + ex.getMessage());
        } finally {
           response.sendRedirect(url);
            //tai sao sendRedirect
            /*client se tao new request then sent to server this new request
            not contain cookies*/
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
