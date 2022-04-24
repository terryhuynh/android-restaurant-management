package com.tdtu.project2.services.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tdtu.project2.models.Food;
import com.tdtu.project2.repositories.FoodRepository;
import com.tdtu.project2.services.FoodService;

@Service
public class FoodServiceImpl implements FoodService {

    private final FoodRepository repository;
    
    @Autowired
    public FoodServiceImpl(FoodRepository repo) {
        this.repository = repo;
    }
    
    @Override
    public List<Food> getListOfFood() {
        return this.repository.findAll();
    }

    @Override
    public List<Food> getListFoodById(Long id) {
        return this.repository.getFoodByTypeId(id);
    }
}
