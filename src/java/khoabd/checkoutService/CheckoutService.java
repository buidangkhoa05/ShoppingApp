/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoabd.checkoutService;

import khoabd.error.CheckoutError;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import khoabd.order.OrderDAO;
import khoabd.order.OrderDTO;
import khoabd.orderDetail.OrderDetailDAO;
import khoabd.orderDetail.OrderDetailDTO;
import khoabd.product.ProductDAO;
import khoabd.product.ProductDTO;
import khoabd.utils.DBHelper;

/**
 *
 * @author Admin
 */
public class CheckoutService implements Serializable {

    private OrderDTO order;
    private List<OrderDetailDTO> orderDetails;
    private Map<String, ProductDTO> cart;
    private Map<String, CheckoutError> quantityErrorList;

    public CheckoutService(OrderDTO order, Map<String, ProductDTO> cart) {
        this.order = order;
        this.cart = cart;
    }

    public CheckoutService(Map<String, ProductDTO> cart) {
        this.cart = cart;
    }

    public OrderDTO getOrder() {
        return order;
    }

    public void setOrder(OrderDTO order) {
        this.order = order;
    }

    public List<OrderDetailDTO> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailDTO> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Map<String, CheckoutError> getQuantityErrorList() {
        return quantityErrorList;
    }

    public void checkQuantity() throws SQLException, NamingException {
        Connection con = null;
        CheckoutError error;
        int quantityBuy;
        int quantityCanBuy;
        try {
            con = DBHelper.makeConnection();
            for (String sku : this.cart.keySet()) {

                error = null;
                quantityBuy = this.cart.get(sku).getQuantity();
                quantityCanBuy = getQuantityCanBuy(con, sku);
                if (quantityBuy > quantityCanBuy) {
                    if (this.quantityErrorList == null) {
                        this.quantityErrorList = new HashMap<>();
                    }
                    error = new CheckoutError();
                    error.setQuantityError("Quantity isn't enough");
                    this.quantityErrorList.put(sku, error);
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    public int getQuantityCanBuy(Connection con, String sku) throws SQLException, NamingException {
        int quantity = 0;
        CallableStatement stm = null;
        try {
            String sql = "{? = call fn_getQuantityCanBuy(?) }";
            stm = con.prepareCall(sql);
            stm.registerOutParameter(1, Types.INTEGER);
            stm.setString(2, sku);
            stm.execute();
            quantity = stm.getInt(1);
        } finally {
            if (stm != null) {
                stm.close();
            }
        }
        return quantity;
    }

    public void loadOrderDetails() {
        if (order == null || cart == null) {
            return;
        }
        if (this.orderDetails == null) {
            this.orderDetails = new ArrayList<>();
        }
        for (ProductDTO item : cart.values()) {
            if (item != null) {
                //create order detail obj
                OrderDetailDTO dto
                        = new OrderDetailDTO(item.getSku(), order.getId(), item.getQuantity(), item.getPrice());
                //add to orderDetails
                this.orderDetails.add(dto);
            }
        }
    }

    public boolean insertDB() throws SQLException, NamingException {
        boolean result = false;
        //get order and order detail from checkout service
        OrderDTO orderDTO = this.getOrder();
        List<OrderDetailDTO> orderDetailList = this.getOrderDetails();

        Connection con = null;

        try {
            con = DBHelper.makeConnection();
            if (con == null) {
                return false;
            }
            con.setAutoCommit(false);
            //stat transaction block
            //call order DAO
            OrderDAO orderDAO = new OrderDAO();
            int orderId = 0;
            if (orderDTO.isGuest()) {
                orderId = orderDAO.insertOrderForGuest(con, orderDTO);
            } else {
                orderId = orderDAO.insertOrderForUser(con, orderDTO);
            }
            //call order detail DAO
            OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
            if (orderId != 0) {
                for (OrderDetailDTO orderDetailDTO : orderDetailList) {
                    orderDetailDAO.insertOrderDetail(con, orderId, orderDetailDTO);
                }
            }
            //end transaction block
            //commit change
            con.commit();
            result = true;
            con.setAutoCommit(true);
        } catch (SQLException ex) {
            con.rollback();
            throw ex;
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

}
