/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoabd.order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import khoabd.account.AccountDTO;

/**
 *
 * @author Admin
 */
public class OrderDTO implements Serializable {

    private int id;
    private AccountDTO account;
    private Date dateBuy;
    private BigDecimal totalPrice;
    private boolean isGuest = false;
    private String address;
    private String phoneNumber;

    public OrderDTO() {
    }

    public OrderDTO(int id, String username, Date dateBuy, String guestName, BigDecimal totalPrice, String phoneNumber) {
        this.id = id;
        this.dateBuy = dateBuy;
        this.totalPrice = totalPrice;
        this.account = new AccountDTO(username, null, guestName, false);
        this.phoneNumber = phoneNumber;
    }

    public OrderDTO(AccountDTO acc, Date dateBuy, BigDecimal totalPrice, String address, String phoneNumber) {
        this.account = acc;
        this.dateBuy = dateBuy;
        this.totalPrice = totalPrice;
        this.address = address;
        this.phoneNumber = phoneNumber;
        //if guest checkout 
        if (acc.getUsername().equalsIgnoreCase("guest")) {
            this.isGuest = true;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AccountDTO getAccount() {
        return account;
    }

    public void setAccount(AccountDTO account) {
        this.account = account;
    }

    public Date getDateBuy() {
        return dateBuy;
    }

    public void setDateBuy(Date dateBuy) {
        this.dateBuy = dateBuy;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isGuest() {
        return isGuest;
    }

    public void setIsGuest(boolean isGuest) {
        this.isGuest = isGuest;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
