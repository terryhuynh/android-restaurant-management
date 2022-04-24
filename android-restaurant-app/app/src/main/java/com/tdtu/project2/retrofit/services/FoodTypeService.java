package com.tdtu.project2.retrofit.services;

import com.tdtu.project2.models.FoodType;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface FoodTypeService {

    @GET("food-types/all")
    Call<List<FoodType>> getAllFoodTypes();

    @POST("food-types/insert")
    Call<Void> insertFoodType(@Body FoodType foodType);
}
