/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoabd.product;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.naming.NamingException;
import khoabd.utils.DBHelper;

/**
 *
 * @author Admin
 */
public class ProductDAO implements Serializable {

    private List<ProductDTO> list;

    private ProductDTO dto;

    public List<ProductDTO> getListProduct() {
        return this.list;
    }

    public ProductDTO getProduct() {
        return dto;
    }

    public void loadAllProductToView() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Select sku, name, description, price "
                        + "From Product "
                        + "Where status = 'true' ";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String sku = rs.getString("sku");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    BigDecimal price = rs.getBigDecimal("price");
                    ProductDTO dto = new ProductDTO(sku, name, description, 0, price, true);
                    if (this.list == null) {
                        this.list = new ArrayList<>();
                    }
                    this.list.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public void loadProduct(String sku) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            String sql = "Select name, description, price "
                    + "From Product "
                    + "Where sku = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, sku);
            rs = stm.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String description = rs.getString("description");
                BigDecimal price = rs.getBigDecimal("price");
                this.dto = new ProductDTO(sku, name, description, 0, price, true);
            }
        } finally {
            if (con != null) {
                con.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }

    public void searchByNameProduct(String searchName) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            String sql = "SELECT sku, name, [description], price, quantity "
                    + "FROM Product "
                    + "WHERE name like ? and [status] = 1";
            stm = con.prepareStatement(sql);
            stm.setString(1, "%" + searchName + "%");
            rs = stm.executeQuery();
            while (rs.next()) {
                String sku = rs.getString("sku");
                String name = rs.getString("name");
                String description = rs.getString("description");
                BigDecimal price = rs.getBigDecimal("price");
                int quantity = rs.getInt("quantity");
                ProductDTO dto = new ProductDTO(sku, name, description, quantity, price, true);
                if (this.list == null || this.list.isEmpty()) {
                    this.list = new ArrayList<>();
                }
                this.list.add(dto);
            }
        } finally {
            if (con != null) {
                con.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }

    public boolean deleteProduct(String pk) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                // 2. Create SQL string
                String sql = "UPDATE Product "
                        + "SET [status] = 0 "
                        + "WHERE sku = ? ";
                stm = cn.prepareStatement(sql);
                stm.setString(1, pk);
                //4. execute Query
                int effectRows = stm.executeUpdate();
                //5. process result
                if (effectRows > 0) {
                    result = true;
                }
            }
        } finally {
            if (cn != null) {
                cn.close();
            }
            if (stm != null) {
                stm.close();
            }
        }
        return result;
    }

    public boolean update(String sku, String name, String description, BigDecimal price, int quantity) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                // 2. Create SQL string
                String sql = "UPDATE Product " +
                                "SET name = ? , [description] = ? , price = ? , quantity = ? " +
                                "WHERE sku = ? ";
                stm = cn.prepareStatement(sql);
                stm.setString(1, name);
                stm.setString(2, description);
                stm.setBigDecimal(3, price);
                stm.setInt(4, quantity);
                stm.setString(5, sku);
                //4. execute Query
                int effectRows = stm.executeUpdate();
                //5. process result
                if (effectRows > 0) {
                    result = true;
                }
            }
        } finally {
            if (cn != null) {
                cn.close();
            }
            if (stm != null) {
                stm.close();
            }
        }
        return result;
    }

}
