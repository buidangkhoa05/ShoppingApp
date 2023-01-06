/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoabd.account;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import khoabd.utils.DBHelper;

public class AccountDAO implements Serializable {

    public AccountDTO checkLogin(String username, String password) throws SQLException, NamingException {
//
        Connection cn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        AccountDTO result = null;

        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                // 2. Create SQL string
                String sql = "SELECT username, lastname, isAdmin "
                        + "FROM Registration "
                        + "WHERE username = ? and password = ?";
                // 3. create Statement 
                stm = cn.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //4. execute Query
                rs = stm.executeQuery();
                //5. process result
                if (rs.next()) {
                    String fullname = rs.getString("lastname");
                    boolean isAdmin = rs.getBoolean("isAdmin");
                    result = new AccountDTO(username, password, fullname, isAdmin);
                }
            }
        } finally {
            if (cn != null) {
                cn.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (rs != null) {
                rs.close();
            }
        }

        return result;
    }

    private List<AccountDTO> accountList;

    //get crt + spacebar
    public List<AccountDTO> getAccountList() {
        return accountList;
    }

    public void searchLastName(String searchValue) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                // 2. Create SQL string
                String sql = "SELECT username, password, lastname, isAdmin "
                        + "FROM Registration "
                        + "WHERE lastname like ? ";
                // 3. create Statement 
                stm = cn.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                //4. execute Query
                rs = stm.executeQuery();
                //5. process result
                while (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String lastname = rs.getString("lastname");
                    boolean isAdmin = rs.getBoolean("isAdmin");
                    AccountDTO dto = new AccountDTO(username, password, lastname, isAdmin);
                    if (this.accountList == null) {
                        this.accountList = new ArrayList<>();
                    }
                    this.accountList.add(dto);
                }
            }
        } finally {
            if (cn != null) {
                cn.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (rs != null) {
                rs.close();
            }
        }

    }

    public boolean deleteAccount(String pk) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                // 2. Create SQL string
                String sql = "DELETE From Registration "
                        + "Where username = ?";
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

    public boolean update(String username, String password, String fullname, boolean isAdmin)
            throws SQLException, NamingException {

        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            con = DBHelper.makeConnection();
            String sql = "Update Registration "
                    + "Set password = ? , lastname = ?, isAdmin = ? "
                    + "Where username = ? ";
            stm = con.prepareStatement(sql);
            stm.setString(1, password);
            stm.setString(2, fullname);
            stm.setBoolean(3, isAdmin);
            stm.setString(4, username);

            int effectRows = stm.executeUpdate();
            if (effectRows > 0) {
                result = true;
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    public boolean createAccounnt(AccountDTO dto) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                // 2. Create SQL string
                String sql = "insert into Registration"
                        + "(username, password, lastname, isAdmin)\n" 
                        + "Values(?, ?, ?, ?)";
                stm = cn.prepareStatement(sql);
                stm.setString(1, dto.getUsername());
                stm.setString(2, dto.getPassword());
                stm.setString(3, dto.getFullname());
                stm.setBoolean(4, dto.isRole());
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
