package com.tdtu.project2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.tdtu.project2.models.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill, String>{

    @Query("select bi from Bill bi where bi.tableId = ?1 and bi.billStatus = ?2")
    Bill getBillByTableIdAndStatus(Long tableId, int status);
}
