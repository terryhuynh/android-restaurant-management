package com.tdtu.project2.services.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tdtu.project2.models.FoodType;
import com.tdtu.project2.repositories.FoodTypeRepository;
import com.tdtu.project2.services.FoodTypeService;

@Service
public class FoodTypeServiceImpl implements FoodTypeService {

    private final FoodTypeRepository repository;
    
    @Autowired
    public FoodTypeServiceImpl(FoodTypeRepository repo) {
        this.repository = repo;
    }

    @Override
    public List<FoodType> getAllFoodTypes() {
        return this.repository.findAll();
    }

    @Override
    public void insertFoodType(FoodType foodType) {
        this.repository.save(foodType);
    }
}
