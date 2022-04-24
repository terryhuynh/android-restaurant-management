package com.tdtu.project2.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BillDetail {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("billId")
    @Expose
    private String billId;

    @SerializedName("foodId")
    @Expose
    private Long foodId;

    @SerializedName("quantity")
    @Expose
    private int quantity;

    @SerializedName("price")
    @Expose
    private double price;

    @SerializedName("total")
    @Expose
    private double total;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public Long getFoodId() {
        return foodId;
    }

    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
