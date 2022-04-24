package com.tdtu.project2.services;

import com.tdtu.project2.models.BillDetail;

public interface BillDetailService {

    void createBillDetail(BillDetail billDetail);

    void updateBillDetail(Long foodId, int quantity, double price, String billDetailId);

    String getBillDetailIdByBill(String billId);
}
