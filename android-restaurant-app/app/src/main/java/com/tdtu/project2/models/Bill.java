package com.tdtu.project2.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bill {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("tableId")
    @Expose
    private Long tableId;

    @SerializedName("billDate")
    @Expose
    private String billDate;

    @SerializedName("billTime")
    @Expose
    private String billTime;

    @SerializedName("billDiscount")
    @Expose
    private double billDiscount;

    @SerializedName("billTotalAmount")
    @Expose
    private double billTotalAmount;

    @SerializedName("billStatus")
    @Expose
    private int billStatus;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public String getBillTime() {
        return billTime;
    }

    public void setBillTime(String billTime) {
        this.billTime = billTime;
    }

    public double getBillDiscount() {
        return billDiscount;
    }

    public void setBillDiscount(double billDiscount) {
        this.billDiscount = billDiscount;
    }

    public double getBillTotalAmount() {
        return billTotalAmount;
    }

    public void setBillTotalAmount(double billTotalAmount) {
        this.billTotalAmount = billTotalAmount;
    }

    public int getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(int billStatus) {
        this.billStatus = billStatus;
    }
}
