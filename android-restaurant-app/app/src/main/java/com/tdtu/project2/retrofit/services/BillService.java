package com.tdtu.project2.retrofit.services;

import com.tdtu.project2.models.Bill;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface BillService {

    @POST("bill/insert")
    Call<Void> createNewBill(@Body Bill bill);

    @GET("bill/get/{tableId}/{status}")
    Call<Bill> getBillByTableIdAndStatus(@Path("tableId") Long tableId, @Path("status") int status);

    @PUT("bill/update/{billId}/{billDiscount}/{billAmount}/{billStatus}")
    Call<Void> updateBill(@Path("billId") String billId, @Path("billDiscount") double billDiscount,
        @Path("billAmount") double billAmount, @Path("billStatus") int billStatus);
}
