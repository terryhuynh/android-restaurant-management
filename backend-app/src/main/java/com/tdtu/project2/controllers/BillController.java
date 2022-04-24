package com.tdtu.project2.controllers;

import com.tdtu.project2.models.Bill;
import com.tdtu.project2.services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/bill")
public class BillController {

    private final BillService service;

    @Autowired
    public BillController(BillService billService) {
        this.service = billService;
    }

    @PostMapping("/insert")
    public void createBill(@RequestBody Bill bill) {
        this.service.createBill(bill);
    }

    @GetMapping("/get/{tableId}/{status}")
    public Bill getBillByTableIdAndStatus(@PathVariable Long tableId, @PathVariable int status) {
        return this.service.getBillByTableIdAndStatus(tableId, status);
    }

    @PutMapping("/update/{billId}/{billDiscount}/{billAmount}/{billStatus}")
    public void updateBill(@PathVariable String billId, @PathVariable double billDiscount,
        @PathVariable double billAmount, @PathVariable int billStatus) {
        this.service.updateBill(billId, billDiscount, billAmount, billStatus);
    }
}
