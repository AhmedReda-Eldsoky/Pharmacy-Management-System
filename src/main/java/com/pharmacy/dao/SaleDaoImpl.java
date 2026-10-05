package com.pharmacy.dao;

import com.pharmacy.model.Sale;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SaleDaoImpl implements SaleDao {

    @Override
    public boolean addNewSale(Sale sale) {
        String sql = "INSERT INTO sales(customer_name, total_amount) VALUES(?,?);";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1,sale.getCustomerName());
            pstmt.setDouble(2,sale.getTotalAmount());
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Sale> findAllSales() {
        List<Sale> salesList = new ArrayList<>();
        String sql = "SELECT * FROM sales;";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet resultSet = pstmt.executeQuery();) {
            while (resultSet.next()) {
                salesList.add(new Sale(
                        resultSet.getInt("sale_id"),
                        resultSet.getString("customer_name"),
                        resultSet.getDouble("total_amount"),
                        resultSet.getTimestamp("sale_date")
                ));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }return salesList;
    }
}
