package com.tdtu.project2.retrofit.services;

import com.tdtu.project2.models.BillDetail;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface BillDetailService {

    @POST("bill-detail/insert")
    Call<Void> createBillDetail(@Body BillDetail billDetail);

    @PUT("bill-detail/update/{foodId}/{quantity}/{price}/{billDetailId}")
    Call<Void> updateBillDetail(@Path("foodId") Long foodId, @Path("quantity") int quantity,
        @Path("price") double price, @Path("billDetailId") String billDetailId);

    @GET("bill-detail/get/id/{billId}")
    Call<String> getBillDetailIdByBill(@Path("billId") String billId);
}
