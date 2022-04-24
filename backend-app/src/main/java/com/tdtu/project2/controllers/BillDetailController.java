package com.tdtu.project2.controllers;

import com.tdtu.project2.models.BillDetail;
import com.tdtu.project2.services.BillDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/bill-detail")
public class BillDetailController {

    private final BillDetailService service;

    @Autowired
    public BillDetailController(BillDetailService billDetailService) {
        this.service = billDetailService;
    }

    @PostMapping("/insert")
    public void createBillDetail(@RequestBody BillDetail billDetail) {
        this.service.createBillDetail(billDetail);
    }

    @PutMapping("/update/{foodId}/{quantity}/{price}/{billDetailId}")
    public void updateBillDetail(@PathVariable Long foodId, @PathVariable int quantity, @PathVariable double price,
        @PathVariable String billDetailId) {
        this.service.updateBillDetail(foodId, quantity, price, billDetailId);
    }

    @GetMapping("/get/id/{billId}")
    String getBillDetailIdByBill(@PathVariable String billId) {
        return this.service.getBillDetailIdByBill(billId);
    }
}
