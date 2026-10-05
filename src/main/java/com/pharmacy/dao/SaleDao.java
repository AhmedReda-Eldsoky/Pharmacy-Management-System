package com.pharmacy.dao;

import com.pharmacy.model.Sale;

import java.util.List;

public interface SaleDao {
    boolean addNewSale(Sale sale);
    List<Sale> findAllSales();
}
