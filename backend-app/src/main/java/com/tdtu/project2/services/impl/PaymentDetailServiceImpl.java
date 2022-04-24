package com.tdtu.project2.services.impl;

import com.tdtu.project2.repositories.impl.PaymentDetailRepositoryImpl;
import com.tdtu.project2.services.PaymentDetailService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentDetailServiceImpl implements PaymentDetailService {

    private final PaymentDetailRepositoryImpl repository;

    @Autowired
    public PaymentDetailServiceImpl(PaymentDetailRepositoryImpl repo) {
        this.repository = repo;
    }

    @Override
    public List<Object[]> getPaymentDetailByBillId(String billId) {
        return repository.getPaymentDetailByBillId(billId);
    }
}
