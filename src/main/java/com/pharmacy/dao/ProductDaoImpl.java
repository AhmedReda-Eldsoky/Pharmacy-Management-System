package com.pharmacy.dao;

import com.pharmacy.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProductDaoImpl implements ProductDao {
    @Override
    public boolean addNewProduct(Product product) {
        String sql = "INSERT INTO products(name, price, quantity) VALUES (?,?,?);";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);){
            pstmt.setString(1, product.getName());
            pstmt.setDouble(2, product.getPrice());
            pstmt.setInt(3, product.getQuantity());
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateProduct(Product updatedProduct) {
        String sql = "UPDATE products SET name=?, price=?, quantity=? WHERE product_id=?;";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);){
            pstmt.setString(1, updatedProduct.getName());
            pstmt.setDouble(2, updatedProduct.getPrice());
            pstmt.setInt(3, updatedProduct.getQuantity());
            pstmt.setInt(4, updatedProduct.getProductId());
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteProduct(int id) {
        String sql = "DELETE FROM products WHERE product_id=?;";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);){
            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Product> findAll() {
        List<Product> productsList = new ArrayList<>();
        String sql = "SELECT * FROM products;";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet resultSet = pstmt.executeQuery();){
            while (resultSet.next()) {
                int product_id = resultSet.getInt("product_id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");
                Product product = new Product(product_id, name, price, quantity);
                productsList.add(product);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }return productsList;
    }

    @Override
    public Product findById(int id) {
        Product product = null;
        String sql = "SELECT * FROM products WHERE product_id = ?;";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setInt(1,id);
            try(ResultSet resultSet = pstmt.executeQuery();){
                if(resultSet.next()) {
                    product = new Product(
                            resultSet.getInt("product_id"),
                            resultSet.getString("name"),
                            resultSet.getDouble("price"),
                            resultSet.getInt("quantity")
                    );
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }return product;
    }
}
