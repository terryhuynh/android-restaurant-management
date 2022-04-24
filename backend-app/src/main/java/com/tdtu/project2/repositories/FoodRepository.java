package com.tdtu.project2.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.tdtu.project2.models.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {

    List<Food> findAll();
    
    @Query("select f from Food f where f.idType = ?1")
    List<Food> getFoodByTypeId(Long id);
}
