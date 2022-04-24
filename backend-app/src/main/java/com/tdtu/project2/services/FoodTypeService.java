package com.tdtu.project2.services;

import java.util.List;
import com.tdtu.project2.models.FoodType;

public interface FoodTypeService {

    List<FoodType> getAllFoodTypes();
    
    void insertFoodType(FoodType foodType);
}
