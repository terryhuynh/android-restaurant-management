package com.tdtu.project2.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tdtu.project2.models.FoodType;

public interface FoodTypeRepository extends JpaRepository<FoodType, Long> {

    List<FoodType> findAll();
}
