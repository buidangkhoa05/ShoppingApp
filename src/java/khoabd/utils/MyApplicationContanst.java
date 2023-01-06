/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoabd.utils;

/**
 *
 * @author Admin
 */
public class MyApplicationContanst {

//    public enum DispatchControllerConstant {
//
//        //html path
//        LOGIN_PAGE(""),
//        //jsp path
//        VIEW_CART("viewCart"),
//        //controller path
//        SHOPPING_VIEW_CONTROLLER("shoppingViewController"),
//        //account controller
//        LOGIN_CONTROLLER("loginController"),
//        LOGOUT_CONTROLLER("logoutController"),
//        FIRST_TIME_REQUEST_CONTROLLER("firstTimeRequestController"),
//        //account crud controller
//        SEARCH_LASTNAME_CONTROLLER("searchByLastnameController"),
//        DELETE_ACCOUNT_CONTROLLER("deleteAccountController"),
//        UPDATE_ACCOUNT_CONTROLLER("updateAccountController"),
//        CREATE_ACCOUNT_CONTROLLER("createAccountController"),
//        //product controller
//        ADD_ITEM_CONTROLLER("addItemController"),
//        REMOVE_ITEM_FORM_CART_CONTROLLER("removeItemFromCartController"),
//        VIEW_CART_CONTROLLER("viewCartController");
//        public final String value;
//
//        private DispatchControllerConstant(String value) {
//            this.value = value;
//        }
//    }//end dispatch controller constant 
    public enum FirstTimeRequestServletConstant {
        LOGIN_PAGE("loginPage"),
        SEARCH_JSP_PAGE("searchJspPage"),
        SHOPPING_VIEW_CONTROLLER("shoppingViewController");
        
        public final String value;

        private FirstTimeRequestServletConstant(String value) {
            this.value = value;
        }
    }

    public enum LoginServletConstant {
        SEARCH_JSP_PAGE("searchJspPage"),
        LOGIN_JSP_PAGE("loginJspPage"),
        FIRST_TIME_REQUEST(""),
        SHOPPING_VIEW_CONTROLLER("shoppingViewController");

        public final String value;

        private LoginServletConstant(String value) {
            this.value = value;
        }
    }

    public enum AddItemServletConstant {
        SHOPPING_JSP_PAGE("shoppingJspPage"),
        SHOPPING_VIEW_CONTROLLER("shoppingViewController");

        public final String value;

        private AddItemServletConstant(String value) {
            this.value = value;
        }
    }

    public enum CreateNewAccountServletConstant {

        START_PAGE(""),
        CREATE_ACCOUNT_JSP_PAGE("createAccountJspPage"),
        CREATE_ACCOUNT_PAGE("createAccountPage");

        public final String value;

        private CreateNewAccountServletConstant(String value) {
            this.value = value;
        }
    }

    public enum DeleteAccountServletConstant {
        LOGIN_PAGE("loginPage"),
         SEARCH_CONTROLLER("searchByLastnameController");

        public final String value;

        private DeleteAccountServletConstant(String value) {
            this.value = value;
        }
    }

    public enum ShoppingViewServletConstant {
        SHOPPING_JSP_PAGE("shoppingJspPage");

        public final String value;

        private ShoppingViewServletConstant(String value) {
            this.value = value;
        }
    }

    public enum ViewCartServletConstant {
        VIEW_CART("cartJspPage");

        public final String value;

        private ViewCartServletConstant(String value) {
            this.value = value;
        }
    }

    public enum LogoutServletConstant {
        SEARCH_JSP_PAGE("searchJspPage"),
        LOGIN_PAGE("loginPage"),
        FIRST_TIME_REQUEST("");

        public final String value;

        private LogoutServletConstant(String value) {
            this.value = value;
        }
    }

    public enum RemoveItemFromCartServletConstant {
        CART_JSP_PAGE("cartJspPage");

        public final String value;

        private RemoveItemFromCartServletConstant(String value) {
            this.value = value;
        }
    }

    public enum CheckoutServletConstant {
        CHECKOUT_JSP_PAGE("checkoutJspPage"),
        SHOPPING_VIEW_COTROLLER("shoppingViewController"),
        CART_JSP_PAGE("cartJspPage");

        public final String value;

        private CheckoutServletConstant(String value) {
            this.value = value;
        }
    }

    public enum UpdateAccountServletConstant {
        SEARCH_JSP_PAGE("searchJspPage"),
        SEARCH_CONTROLLER("searchByLastnameController"),
        UPDATE_JSP_PAGE("updateJspPage"),
        LOGIN_JSP_PAGE("loginJspPage");

        public final String value;

        private UpdateAccountServletConstant(String value) {
            this.value = value;
        }
    }

    public enum SearchLastNameServletConstant {
        SEARCH_JSP_PAGE("searchJspPage");

        public final String value;

        private SearchLastNameServletConstant(String value) {
            this.value = value;
        }
    }

    public enum AuthenticationFilterConstant {
        LOGIN_PAGE("loginPage"),
        NOT_FOUND_PAGE("404Page");

        public final String value;

        private AuthenticationFilterConstant(String value) {
            this.value = value;
        }
    }
    
    public enum AuthorizationFilterConstant {
        LOGIN_JSP_PAGE("loginJspPage"),
        NOT_FOUND_PAGE("404Page");
        
        public final String value;

        private AuthorizationFilterConstant(String value) {
            this.value = value;
        }
    }
    
    public enum CheckQuantityServletConstant {
        CART_JSP_PAGE("cartJspPage"), 
        CHECKOUT_JSP_PAGE("checkoutJspPage");
        
        public final String value;
        
        private CheckQuantityServletConstant(String value) {
            this.value = value;
        }
    }
    
    public enum SearchBookServletConstant {
        SEARCH_BOOK_JSP_PAGE("manageBookJspPage");
        
        public final String value;
        
        private SearchBookServletConstant(String value) {
            this.value = value;
        }
    }
    
     public enum DeleteProductServletConstant {
        SEARCH_BOOK_SERVLET("searchProductController"),
        LOGIN_PAGE("loginPage");
        
        public final String value;
        
        private DeleteProductServletConstant(String value) {
            this.value = value;
        }
    }
     
     public enum UpdateProductServletConstant {
        SEARCH_CONTROLLER("searchProductController"),
        UPDATE_PRODUCT_JSP_PAGE("updateProductJspPage"),
        LOGIN_PAGE("loginPage");
        
        public final String value;
        
        private UpdateProductServletConstant(String value) {
            this.value = value;
        }
    }

}
