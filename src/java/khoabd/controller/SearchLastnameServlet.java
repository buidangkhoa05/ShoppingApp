/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoabd.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import khoabd.account.AccountDAO;
import khoabd.account.AccountDTO;
import khoabd.utils.DBHelper;
import khoabd.utils.MyApplicationContanst.SearchLastNameServletConstant;

/**
 *
 * @author Admin
 */
public class SearchLastnameServlet extends HttpServlet {

//    private final String SEARCH_PAGE = "search.html";
//    private final String SEARCH_RESULT_PAGE = "search.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        System.out.println("Search last name servlet");
        String url = DBHelper.getUrl(this.getServletContext(),
                SearchLastNameServletConstant.SEARCH_JSP_PAGE.value);
        String searchValue = request.getParameter("txtSearchValue");


        try {
            //1, check valid search Value --> search
            if (searchValue.trim().length() > 0) {
                //call dao
                AccountDAO dao = new AccountDAO();
                dao.searchLastName(searchValue);
                //procces
                List<AccountDTO> result = dao.getAccountList();
                //sent view cotroller to view la set attribute vao scope
                // send to view
                request.setAttribute("SEARCH_RESULT", result);
                if (!result.isEmpty()) {
                    System.out.println("search result is not empty");
                }
            }
        } catch (SQLException ex) {
            log("Error at SearchLastNameServlet _SQL:" + ex.getMessage());
        } catch (NamingException ex) {
            log("Error at SearchLastNameServlet _Naming:" + ex.getMessage());
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
