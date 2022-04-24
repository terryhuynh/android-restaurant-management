package com.tdtu.project2.retrofit.services;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PaymentDetailService {

    @GET("payment-detail/get/{billId}")
    Call<List<Object[]>> getPaymentDetailByBillId(@Path("billId") String billId);
}
