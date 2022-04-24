package com.tdtu.project2.services;

import com.tdtu.project2.models.Bill;

public interface BillService {

    void createBill(Bill bill);
    
    Bill getBillByTableIdAndStatus(Long tableId, int status);

    void updateBill(String billId, double billDiscount, double billAmount, int billStatus);
}
