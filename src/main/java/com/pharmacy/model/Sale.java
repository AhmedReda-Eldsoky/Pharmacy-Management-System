package com.pharmacy.model;

import java.util.Date;

public class Sale {
    private int saleId;
    private String customerName;
    private double totalAmount;
    private Date saleDate;

    public Sale() {}

    public Sale(String customerName, double totalAmount, Date saleDate) {
        this.customerName = customerName;
        this.totalAmount = totalAmount;
        this.saleDate = saleDate;
    }

    public Sale(int saleId, String customerName, double totalAmount, Date saleDate) {
        this.saleId = saleId;
        this.customerName = customerName;
        this.totalAmount = totalAmount;
        this.saleDate = saleDate;
    }

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
       if (totalAmount > 0){
           this.totalAmount = totalAmount;
       }
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "saleId=" + saleId +
                ", customarName='" + customerName + '\'' +
                ", totalAmount=" + totalAmount +
                ", saleDate=" + saleDate +
                '}';
    }
}

