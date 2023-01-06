/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoabd.order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import khoabd.account.AccountDTO;

/**
 *
 * @author Admin
 */
public class OrderDAO implements Serializable {

    //insert for guest
    public int insertOrderForGuest(Connection con, OrderDTO order)
            throws SQLException {

        int orderId = 0;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            String sql = "Insert Into Orders(username, totalPrice, dateBuy, guestName, address , phonenumber ) "
                    + "Values( ? , ? , ? , ? , ?, ?) ";
            stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, order.getAccount().getUsername());
            stm.setBigDecimal(2, order.getTotalPrice());
            stm.setDate(3, order.getDateBuy());
            stm.setString(4, order.getAccount().getFullname());
            stm.setString(5, order.getAddress());
            stm.setString(6, order.getPhoneNumber());
            
            //statement exec
            stm.executeUpdate();
            rs = stm.getGeneratedKeys();
            if (rs.next()) {
                orderId = rs.getInt(1);// get by index col
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
        }
        return orderId;
    }

    //insert order for user
    public int insertOrderForUser(Connection con, OrderDTO order)
            throws SQLException {

        int orderId = 0;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            String sql = "Insert Into Orders(username, totalPrice, dateBuy, address ) "
                    + "Values( ? , ? , ? , ? ) "; 
            
            stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            stm.setString(1, order.getAccount().getUsername());
            stm.setBigDecimal(2, order.getTotalPrice());
            stm.setDate(3, (Date) order.getDateBuy());
            stm.setString(4, order.getAddress());
            
            //statement exec
            stm.executeUpdate();
            rs = stm.getGeneratedKeys();
            if (rs.next()) {
                orderId = rs.getInt(1);// get by index col
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
        }
        return orderId;
    }
}
