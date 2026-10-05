package com.pharmacy.dao;

import com.pharmacy.model.Product;

import java.util.List;

public interface ProductDao {

    boolean addNewProduct (Product product);
    boolean updateProduct (Product updatedProduct);
    boolean deleteProduct (int id);

    List<Product> findAll();

    Product findById(int id);

}
