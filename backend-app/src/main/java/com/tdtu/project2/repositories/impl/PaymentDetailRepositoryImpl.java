package com.tdtu.project2.repositories.impl;

import com.tdtu.project2.repositories.PaymentDetailRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentDetailRepositoryImpl implements PaymentDetailRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private static final String SQL_QUERY = "select f.food_name, bd.quantity, bd.total from bill_detail as bd, "
        + "food as f where bd.food_id = f.id and bd.bill_id = :bill_id";

    @Override
    public List<Object[]> getPaymentDetailByBillId(String billId) {
        Query nativeQuery = entityManager.createNativeQuery(SQL_QUERY);
        nativeQuery.setParameter("bill_id", billId);

        return nativeQuery.getResultList();
    }
}
