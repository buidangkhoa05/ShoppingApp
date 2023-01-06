/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoabd.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import khoabd.cart.CartObject;
import khoabd.error.CheckoutError;
import khoabd.checkoutService.CheckoutService;
import khoabd.utils.DBHelper;
import khoabd.utils.MyApplicationContanst.CheckQuantityServletConstant;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CheckQuantityServlet", urlPatterns = {"/CheckQuantityServlet"})
public class CheckQuantityServlet extends HttpServlet {

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

        CheckoutService service = null;

        String url = DBHelper.getUrl(this.getServletContext(),
                CheckQuantityServletConstant.CART_JSP_PAGE.value);

        try {
            HttpSession session = request.getSession(false);
            if (session == null) {
                return;
            }
            CartObject cart = (CartObject) session.getAttribute("CART");
            if (cart == null) {
                return;
            }
            //check: quantity item in order is valid and if invalid return cart.jsp
            service = new CheckoutService(cart.getItems());
            service.checkQuantity();
            Map<String, CheckoutError> quantityError = service.getQuantityErrorList();
            if (quantityError != null) {
                request.setAttribute("QUANTITY_ERROR", quantityError);
                return;
            }
            url = DBHelper.getUrl(this.getServletContext(),
                    CheckQuantityServletConstant.CHECKOUT_JSP_PAGE.value);
        } catch (SQLException ex) {
            log("Error at CheckQuantityServlet _SQL: " + ex.toString());
        } catch (NamingException ex) {
            log("Error at CheckQuantityServlet _Naming: " + ex.toString());
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
