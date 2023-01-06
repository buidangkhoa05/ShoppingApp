/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoabd.controller;

import java.io.IOException;
import java.util.Properties;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import khoabd.utils.DBHelper;
import khoabd.utils.MyApplicationContanst;

/**
 *
 * @author Admin
 */
@WebServlet(name = "DispatchController", urlPatterns = {"/DispatchController"})
public class DispatchController extends HttpServlet {

// contanst variable - containt path
//    private final String LOGIN_PAGE = "login.html";
//    private final String LOGIN_CONTROLLER = "LoginServlet";
//    private final String SEARCH_LASTNAME_CONTROLLER = "SearchLastnameServlet";
//    private final String DELETE_CONTROLLER = "DeleteServlet";
//    private final String FIRST_TIME_REQUEST_CONTROLLER = "FirstTimeRequestServlet";
//    private final String UPDATE_ACCOUNT_CONTROLLER = "UpdateAccountServlet";
//    private final String ADD_ITEM_CONTROLLER = "AddItemServlet";
//    private final String VIEW_CART_CONTROLLER = "cart.jsp";
//    private final String REMOVE_ITEM_FORM_CART = "RemoveItemFromCartServlet";
//    private final String LOGOUT_CONTROLLER = "LogoutServlet";
//    private final String CREATE_ACCOUNT_CONTROLLER = "CreateNewAccountServlet";
    
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//
//        //use context initialization parameter
//        //1 get context 
//        ServletContext context = this.getServletContext();
//        //2 get properties from context 
//        Properties siteMaps = (Properties) context.getAttribute("SITE_MAP");
//        //end 
//
//        String url = siteMaps
//                .getProperty(DispatchControllerConstant.LOGIN_PAGE.value);
//
//        //which button did user click?
//        String button = request.getParameter("btAction");
//
//        try {
//            if (button == null) {
//                System.out.println("First time request");
//                //first time sent request
//                url = siteMaps.getProperty(
//                        DispatchControllerConstant.FIRST_TIME_REQUEST_CONTROLLER.value);
//            } else if (button.equals("Login")) {
//                url = siteMaps.getProperty(
//                        DispatchControllerConstant.LOGIN_CONTROLLER.value);
//            } else if (button.equals("Search")) {
//                System.out.println("searching");
//                url = siteMaps.getProperty(
//                        DispatchControllerConstant.SEARCH_LASTNAME_CONTROLLER.value);
//            } else if (button.equals("Delete")) {
//                System.out.println("deleting");
//                url = siteMaps.getProperty(
//                        DispatchControllerConstant.DELETE_ACCOUNT_CONTROLLER.value);
//            } else if (button.equals("Update")) {
//                System.out.println("updating");
//                url = siteMaps.getProperty(
//                        DispatchControllerConstant.UPDATE_ACCOUNT_CONTROLLER.value);
//            } else if (button.equals("Add books to your cart")) {
//                System.out.println("Adding item");
//                url = siteMaps.getProperty(
//                        DispatchControllerConstant.ADD_ITEM_CONTROLLER.value);
//            } else if (button.equals("viewYourCart")) {
//                System.out.println("view cart");
//                url = siteMaps.getProperty(
//                        DispatchControllerConstant.VIEW_CART_CONTROLLER.value);
//            } else if (button.equals("Remove Selected Item")) {
//                System.out.println("removing item");
//                url = siteMaps.getProperty(
//                        DispatchControllerConstant.REMOVE_ITEM_FORM_CART_CONTROLLER.value);
//            } else if (button.equals("Logout")) {
//                System.out.println("logout");
//                url = siteMaps.getProperty(
//                        DispatchControllerConstant.LOGOUT_CONTROLLER.value);
//            } else if (button.equals("Create New Account")) {
//                System.out.println("creating new account");
//                url = siteMaps.getProperty(
//                        DispatchControllerConstant.CREATE_ACCOUNT_CONTROLLER.value);
//            } else if (button.equals("shoppingView")) {
//                System.out.println("Shopping view");
//                url = DBHelper.getUrl(this.getServletContext(), 
//                        DispatchControllerConstant.SHOPPING_VIEW_CONTROLLER.value);
//            }
//        }catch (NullPointerException ex) {
//            log("Error _NULLPOINTER: " + ex.getMessage());
//        } finally {
//            RequestDispatcher rd = request.getRequestDispatcher(url);
//            rd.forward(request, response);
//        }
//    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>

}
