/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoabd.controller;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import khoabd.cart.CartObject;
import khoabd.product.ProductDTO;
import khoabd.utils.DBHelper;
import khoabd.utils.MyApplicationContanst.RemoveItemFromCartServletConstant;

/**
 *
 * @author Admin
 */
@WebServlet(name = "RemoveItemFromCartServlet", urlPatterns = {"/RemoveItemFromCartServlet"})
public class RemoveItemFromCartServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String url = DBHelper.getUrl(this.getServletContext(), 
                RemoveItemFromCartServletConstant.CART_JSP_PAGE.value);
        
        try {
            //1 cus goes to his cart places
            HttpSession session = request.getSession(false);
            if (session != null) {
                //2 cus take his cart
                CartObject cart = (CartObject) session.getAttribute("CART");
                if (cart != null) {
                    //3 cus checks existed items
                    Map<String, ProductDTO> items = cart.getItems();
                    if (items != null) {
                        //4 cus get all selected items
                        String[] selectedItems = request.getParameterValues("chkItem");
                        if (selectedItems != null) {
                            //5 cus take all selected item from cart
                            for (String item : selectedItems) {
                                cart.removeItemFromCart(item);
                            }
                            session.setAttribute("CART", cart);
                            //6 syt reference grid
                        }//end user had selected
                    }
                } //end cart have been exist
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
