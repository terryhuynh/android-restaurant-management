package com.tdtu.project2.services.impl;

import com.tdtu.project2.models.BillDetail;
import com.tdtu.project2.repositories.BillDetailRepository;
import com.tdtu.project2.services.BillDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillDetailServiceImpl implements BillDetailService {

    private final BillDetailRepository repository;

    @Autowired
    public BillDetailServiceImpl(BillDetailRepository billDetailRepo) {
        this.repository = billDetailRepo;
    }

    @Override
    public void createBillDetail(BillDetail billDetail) {
        this.repository.save(billDetail);
    }

    @Override
    public void updateBillDetail(Long foodId, int quantity, double price, String billDetailId) {
        BillDetail billDetail = this.repository.getOne(billDetailId);
        double total = price * quantity;

        billDetail.setFoodId(foodId);
        billDetail.setQuantity(quantity);
        billDetail.setPrice(price);
        billDetail.setTotal(total);

        this.repository.save(billDetail);
    }

    @Override
    public String getBillDetailIdByBill(String billId) {
        return this.repository.getBillDetailIdByBill(billId);
    }
}
