/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoabd.orderDetail;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Admin
 */
public class OrderDetailDTO implements Serializable{
    private String sku;
    private int orderId;
    private int quantity;
    private BigDecimal totalPrice;
    private int id;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(String sku, int orderId, int quantity, BigDecimal totalPrice, int id) {
        this.sku = sku;
        this.orderId = orderId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.id = id;
    }

    public OrderDetailDTO(String sku, int orderId, int quantity, BigDecimal totalPrice) {
        this.sku = sku;
        this.orderId = orderId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
