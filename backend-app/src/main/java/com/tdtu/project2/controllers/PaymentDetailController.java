package com.tdtu.project2.controllers;

import com.tdtu.project2.services.PaymentDetailService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/payment-detail")
public class PaymentDetailController {

    private final PaymentDetailService service;

    @Autowired
    public PaymentDetailController(PaymentDetailService service) {
        this.service = service;
    }

    @GetMapping("/get/{billId}")
    public List<Object[]> getPaymentDetailByBillId(@PathVariable String billId) {
        return this.service.getPaymentDetailByBillId(billId);
    }
}
