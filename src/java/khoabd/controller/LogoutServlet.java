/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoabd.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import khoabd.utils.DBHelper;
import khoabd.utils.MyApplicationContanst.LogoutServletConstant;

/**
 *
 * @author Admin
 */
@WebServlet(name = "LogoutServlet", urlPatterns = {"/LogoutServlet"})
public class LogoutServlet extends HttpServlet {

//    private String SUCCESS_PAGE = "DispatchController";
//    private String ERROR_PAGE = "";
 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String url = DBHelper.getUrl(this.getServletContext(), 
                LogoutServletConstant.SEARCH_JSP_PAGE.value);
        String fromSrc = request.getParameter("fromSrc");
        
        try  {
            System.out.println("logout servlet running");
            Cookie[] cookies = request.getCookies();
            //delete cookie
            if (cookies != null) {
                //remove cookie
                Cookie lastCookie = cookies[cookies.length - 1];
                lastCookie.setValue("");
                lastCookie.setMaxAge(0);
                response.addCookie(lastCookie);
                //remove session
                HttpSession session = request.getSession(false);
                if (session != null) {
                    session.removeAttribute("USER");
                    session.setMaxInactiveInterval(0);
                    session.invalidate();
                }
                url = DBHelper.getUrl(this.getServletContext(), 
                        LogoutServletConstant.FIRST_TIME_REQUEST.value);
            }
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
