package com.tdtu.project2.repositories;

import com.tdtu.project2.models.BillDetail;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BillDetailRepository extends JpaRepository<BillDetail, String> {

    @Query("select bd.id from BillDetail bd where bd.billId = ?1")
    String getBillDetailIdByBill(String billId);

//    @Query("select * from BillDetail bd, Food f where bd.foodId = f.id and where bd.billId = ?1")
//    List<PaymentDetail> getPaymentDetailByBillId(String billId);
}
