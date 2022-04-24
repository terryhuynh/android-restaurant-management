package com.tdtu.project2.retrofit.services;

import com.tdtu.project2.models.Food;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FoodService {

    @GET("food/all")
    Call<List<Food>> getListOfFood();

    @GET("food/all/{idType}")
    Call<List<Food>> getListFoodById(@Path("idType") Long id);
}
