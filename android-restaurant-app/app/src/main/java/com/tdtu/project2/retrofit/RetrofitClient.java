package com.tdtu.project2.retrofit;

import com.tdtu.project2.constants.ApiConstant;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit = null;

    public static Retrofit getInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(ApiConstant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        }

        return retrofit;
    }
}
