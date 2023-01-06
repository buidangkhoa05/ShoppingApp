/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoabd.orderDetail;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.NamingException;
import khoabd.utils.DBHelper;

/**
 *
 * @author Admin
 */
public class OrderDetailDAO implements Serializable {

    public int insertOrderDetail(Connection con, int orderId, OrderDetailDTO dto)
            throws SQLException, NamingException {
        PreparedStatement stm = null;
        ResultSet rs = null;

        int orderDetailId = 0;

        try {
            String sql = "Insert Into OrderDetail(orderId, sku, quantity, totalPrice ) "
                    + "Values( ? , ? , ? , ? )";
            stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stm.setInt(1, orderId);
            stm.setString(2, dto.getSku());
            stm.setInt(3, dto.getQuantity());
            stm.setBigDecimal(4, dto.getTotalPrice());
            //exec
            stm.executeUpdate();
            rs = stm.getGeneratedKeys();
            if (rs.next()) {
                orderDetailId = rs.getInt(1);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
        }
        return orderDetailId;
    }

    public int sumQuantityOrderDetail(String sku) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int result = 0;
        try {
            con = DBHelper.makeConnection();
            String sql = "SELECT SUM(quantity) as quantity "
                    + "FROM OrderDetail "
                    + "Where sku = ? ";
            stm = con.prepareStatement(sql);
            stm.setString(1, sku);
            rs = stm.executeQuery();
            if (rs.next()) {
                result = rs.getInt("quantity");
                System.out.println("so luong san pham da ban: " + result);
            }
        } finally {
            if (con != null) {
                con.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
        }
        return result;
    }
}
