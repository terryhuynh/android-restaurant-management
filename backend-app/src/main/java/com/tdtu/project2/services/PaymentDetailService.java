package com.tdtu.project2.services;

import java.util.List;

public interface PaymentDetailService {

    List<Object[]> getPaymentDetailByBillId(String billId);
}
