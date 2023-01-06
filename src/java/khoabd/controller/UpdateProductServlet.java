/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoabd.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
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
import khoabd.product.ProductDAO;
import khoabd.product.ProductError;
import khoabd.utils.DBHelper;
import khoabd.utils.MyApplicationContanst;

/**
 *
 * @author Admin
 */
@WebServlet(name = "UpdateProductServlet", urlPatterns = {"/UpdateProductServlet"})
public class UpdateProductServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        //define var
        HttpSession session = request.getSession(false);
        AccountDTO user = null;
        //define error 
        boolean authorErrFound = false;
        boolean errFound = false;
        ProductError errors = null;
        AuthorizationError authorError = null;
        boolean resultStatus = false;
        //define var get from req parameter to update
        boolean isAdmin = false;
        String sku = request.getParameter("txtSku");
        String name = request.getParameter("txtName");
        String description = request.getParameter("txtDescription");
        BigDecimal price = new BigDecimal("0");
        String sPrice = request.getParameter("txtPrice");
        int quantity = 0;
        String sQuantity = request.getParameter("txtQuantity");
        String lastSearchValue = request.getParameter("lastSearchValue");
        //define url
        String url = DBHelper.getUrl(this.getServletContext(),
                MyApplicationContanst.UpdateProductServletConstant.SEARCH_CONTROLLER.value)
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
                        MyApplicationContanst.UpdateProductServletConstant.LOGIN_PAGE.value);
                return;
            }
            //validation input for update
            if (name == null || name.length() <= 0) {
                errFound = true;
                if (errors == null) {
                    errors = new ProductError();
                    errors.setNameLengthError("Name must have length > 0");
                }
            }
            // price and  quantity must be number and not negative
            try {
                price = new BigDecimal(sPrice);
                quantity = Integer.parseInt(sQuantity);
                if (quantity < 0 || price.compareTo(BigDecimal.ZERO) < 0) {
                    if (errors == null) {
                        errors = new ProductError();
                    }
                    errors.setNumberFormatError("Price and quantity must be >= 0");
                    errFound = true;
                }
            } catch (Exception e) {
                if (errors == null) {
                    errors = new ProductError();
                }
                errors.setNumberFormatError("Price and quantity must be number");
                errFound = true;
            }
            //check: have user error or not 
            if (errFound) {
                request.setAttribute("UPDATE_ERROR", errors);
                url = DBHelper.getUrl(this.getServletContext(),
                        MyApplicationContanst.UpdateProductServletConstant.UPDATE_PRODUCT_JSP_PAGE.value);
                return;
            }
            //call dao to updte
            ProductDAO dao = new ProductDAO();
            resultStatus = dao.update(sku, name, description, price, quantity);
            if (resultStatus) {
                //url rewritting for recall search func
                url = DBHelper.getUrl(this.getServletContext(),
                        MyApplicationContanst.UpdateProductServletConstant.SEARCH_CONTROLLER.value)
                        + "?txtSearchValue=" + lastSearchValue;
                System.out.println(url);
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
