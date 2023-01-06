/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoabd.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import khoabd.account.AccountDTO;
import khoabd.error.AuthorizationError;
import khoabd.product.ProductDAO;
import khoabd.utils.DBHelper;
import khoabd.utils.MyApplicationContanst;

/**
 *
 * @author Admin
 */
@WebServlet(name = "DeleteProductServlet", urlPatterns = {"/DeleteProductServlet"})
public class DeleteProductServlet extends HttpServlet {
    
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
                MyApplicationContanst.DeleteProductServletConstant.LOGIN_PAGE.value);
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
                    ProductDAO dao = new ProductDAO();
                    resultStatus = dao.deleteProduct(pk);
            }
            //2.delete successfully and call SearchFunc
            if (resultStatus) {
                System.out.println("Delete successful");
                url = DBHelper.getUrl(this.getServletContext(),
                        MyApplicationContanst.DeleteProductServletConstant.SEARCH_BOOK_SERVLET.value)
                        + "?txtSearchValue=" + lastSearchValue;
                System.out.println(url);
            }
        } catch (SQLException ex) {
            log("Error at DeleteAccountServlet _SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("Error at DeleteAccountServlet Naming: " + ex.getMessage());
        } finally {
            response.sendRedirect(url);
        }
    }



@Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
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
