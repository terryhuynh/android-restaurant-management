package com.tdtu.project2.retrofit.services;

import com.tdtu.project2.models.TableFood;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TableFoodService {

    @GET("tables/all")
    Call<List<TableFood>> getAllTables();

    @PUT("tables/update/status/{tableId}/{status}")
    Call<Void> updateTableStatus(@Path("tableId") Long tableId, @Path("status") Long status);
}
