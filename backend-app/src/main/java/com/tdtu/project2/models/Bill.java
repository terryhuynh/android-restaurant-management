package com.tdtu.project2.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bill")
public class Bill {

    @Id
    @Column(name = "id", length = 11)
    private String id;

    @Column(name = "table_id", length = 11)
    private Long tableId;

    @Column(name = "bill_date", length = 20)
    private String billDate;

    @Column(name = "bill_time", length = 20)
    private String billTime;

    @Column(name = "bill_discount")
    private double billDiscount;

    @Column(name = "bill_total_amount")
    private double billTotalAmount;

    @Column(name = "bill_status")
    private int billStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
