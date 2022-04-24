package com.tdtu.project2.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tdtu.project2.models.FoodType;
import com.tdtu.project2.services.FoodTypeService;

@RestController
@RequestMapping("api/v1/food-types")
public class FoodTypeController {

    @Autowired
    private final FoodTypeService ftypeService;

    @Autowired
    public FoodTypeController(FoodTypeService ftypeService) {
        this.ftypeService = ftypeService;
    }

    @GetMapping("/all")
    public List<FoodType> getAllFoodTypes() {
        return ftypeService.getAllFoodTypes();
    }

    @PostMapping(value = "/insert")
    public void insertFoodType(@RequestBody FoodType foodType) {
        this.ftypeService.insertFoodType(foodType);
    }
}
