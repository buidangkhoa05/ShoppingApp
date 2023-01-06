/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoabd.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
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
import khoabd.account.AccountDTO;
import khoabd.cart.CartObject;
import khoabd.error.CheckoutError;
import khoabd.checkoutService.CheckoutService;
import khoabd.order.OrderDAO;
import khoabd.order.OrderDTO;
import khoabd.utils.DBHelper;
import khoabd.utils.MyApplicationContanst.CheckoutServletConstant;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CheckoutServlet", urlPatterns = {"/CheckoutServlet"})
public class CheckoutServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = DBHelper.getUrl(this.getServletContext(),
                CheckoutServletConstant.CHECKOUT_JSP_PAGE.value);

        CheckoutError error = new CheckoutError();
        boolean haveError = false;
        String regex = "^[0-9]{10}$";
        CheckoutService service = null;

        //define param var to insert to db
        String guestNanme = request.getParameter("textGuestName");
        String phoneNumber = request.getParameter("textPhoneNumber");
        String address = request.getParameter("textAddress");
        BigDecimal totalPrice = new BigDecimal(request.getParameter("txtTotalPrice"));

        try {
            HttpSession session = request.getSession(false);
            if (session == null) {
                return;
            }
            CartObject cart = (CartObject) session.getAttribute("CART");
            if (cart == null) {
                return;
            }
            //define user information
            AccountDTO dto = (AccountDTO) session.getAttribute("USER");
            if (dto == null) {
                if (guestNanme.trim().length() < 6 || guestNanme.trim().length() > 20) {
                    error.setGuestNameLengthError("GuestName is must 6-20 char");
                    haveError = true;
                } else {
                    dto = new AccountDTO("guest", "", guestNanme, false);
                }
            }
            //catch user error
            if (phoneNumber.trim().length() != 10) {
                error.setPhoneNumberLengthError("Phone number is must 10 char");
                haveError = true;
            } else if (!phoneNumber.matches(regex)) {
                error.setPhonenumberFormatError("Phone number is not match format");
                 haveError = true;
            }
            if (address.trim().length() < 10) {
                error.setAddressLengthError("Address is must least 10 char");
                haveError = true;
            }
            //Have error or not
            if (haveError) {
                request.setAttribute("CHECKOUT_ERROR", error);
                request.setAttribute("guestname", guestNanme);
                request.setAttribute("phonenumber", phoneNumber);
                request.setAttribute("address", address);
                return;
            } else {
                boolean insertStatus = false;
                //call service to interact with db
                //create order obj
                long time = System.currentTimeMillis();
                OrderDTO order = new OrderDTO(dto, new java.sql.Date(time), totalPrice, address, phoneNumber);
                //init service
                service = new CheckoutService(order, cart.getItems());
                //load order detail obj to service
                service.loadOrderDetails();
                //call service dao 
                insertStatus = service.insertDB();
                if (insertStatus) {
                    session.setAttribute("CART", null);
                    url = DBHelper.getUrl(this.getServletContext(),
                            CheckoutServletConstant.SHOPPING_VIEW_COTROLLER.value);
                }
            }

        } catch (SQLException ex) {
            log("Error at CheckoutServlet _SQL: " + ex.toString());
        } catch (NamingException ex) {
            log("Error at CheckoutServlet _Naming: " + ex.toString());
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
