package com.tdtu.project2.repositories;

import java.util.List;

public interface PaymentDetailRepository {

    List<Object[]> getPaymentDetailByBillId(String billId);
}
