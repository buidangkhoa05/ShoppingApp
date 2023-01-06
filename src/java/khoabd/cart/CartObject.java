/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoabd.cart;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.naming.NamingException;
import khoabd.product.ProductDAO;
import khoabd.product.ProductDTO;

/**
 *
 * @author Admin
 */
public class CartObject implements Serializable {

    private Map<String, ProductDTO> items;

    public Map<String, ProductDTO> getItems() {
        return items;
    }

    public void addItemsToCart(String sku, int quantity)
            throws SQLException, NamingException {

        if (sku == null) {
            return;
        }

        if (sku.trim().isEmpty()) {
            return;
        }

        if (quantity <= 0) {
            return;
        }

        //1.check existed items
        if (this.items == null) {
            this.items = new HashMap<>();
        }

        //2.check existed item name
        ProductDTO dto = null;
        if (this.items.containsKey(sku)) {
            dto = this.items.get(sku);
            quantity += dto.getQuantity();
            dto.setQuantity(quantity);
        } else {
            ProductDAO dao = new ProductDAO();
            dao.loadProduct(sku);
            dto = dao.getProduct();
            dto.setQuantity(quantity);
        }

        //3.update item
        if (dto != null) {
        this.items.put(sku, dto);
        }
    }

    public void removeItemFromCart(String sku) {
        if (sku == null) {
            return;
        }

        //1 check exists items
        if (this.items == null) {
            return;
        }

        //2 check existed item name
        if (this.items.containsKey(sku)) {
            this.items.remove(sku);

            if (this.items.isEmpty()) {
                this.items = null;
            }
        }
    }

}
