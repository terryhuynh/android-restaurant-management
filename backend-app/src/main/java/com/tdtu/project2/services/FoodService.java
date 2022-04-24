package com.tdtu.project2.services;

import java.util.List;
import com.tdtu.project2.models.Food;

public interface FoodService {

    List<Food> getListOfFood();
    
    List<Food> getListFoodById(Long id);
}
