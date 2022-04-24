package com.tdtu.project2.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tdtu.project2.models.Food;
import com.tdtu.project2.services.FoodService;

@RestController
@RequestMapping("api/v1/food")
public class FoodController {

    @Autowired
    private final FoodService foodService;

    @Autowired
    public FoodController(FoodService service) {
        this.foodService = service;
    }

    @GetMapping(value = "/all")
    public List<Food> getAllFood() {
        return this.foodService.getListOfFood();
    }
    
    @GetMapping(value = "/all/{id}")
    public List<Food> getListFoodById(@PathVariable Long id) {
        return this.foodService.getListFoodById(id);
    }
}
