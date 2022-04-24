package com.tdtu.project2.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tdtu.project2.models.Bill;
import com.tdtu.project2.repositories.BillRepository;
import com.tdtu.project2.services.BillService;

@Service
public class BillServiceImpl implements BillService {

    private final BillRepository repository;
    
    @Autowired
    public BillServiceImpl(BillRepository billRepo) {
        this.repository = billRepo;
    }

    @Override
    public void createBill(Bill bill) {
       this.repository.save(bill);
    }

    @Override
    public Bill getBillByTableIdAndStatus(Long tableId, int status) {
        return this.repository.getBillByTableIdAndStatus(tableId, status);
    }

    @Override
    public void updateBill(String billId, double billDiscount, double billAmount, int billStatus) {
        Bill bill = this.repository.getOne(billId);
        bill.setBillDiscount(billDiscount);
        bill.setBillTotalAmount(billAmount);
        bill.setBillStatus(billStatus);

        this.repository.save(bill);
    }
}
