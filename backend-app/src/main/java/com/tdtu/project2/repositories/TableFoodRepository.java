package com.tdtu.project2.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tdtu.project2.models.TableFood;

@Repository
public interface TableFoodRepository extends JpaRepository<TableFood, Long> {

    List<TableFood> findAll();
}
