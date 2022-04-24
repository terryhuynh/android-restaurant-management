package com.tdtu.project2.retrofit.services;

import com.tdtu.project2.models.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EmployeeService {

    @GET("employees/all")
    Call<List<Employee>> getAllEmployees();

    @GET("employees/{username}/{password}")
    Call<Employee> loginEmployee(@Path("username") String username,
        @Path("password") String password);

    @POST("employees/insert")
    Call<Void> insertEmployee(@Body Employee employee);
}
