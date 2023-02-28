package com.example.retrofit_api_test.Api;

import com.example.retrofit_api_test.Model.Login;
import com.example.retrofit_api_test.Model.SignUp;
import com.example.retrofit_api_test.Respone.LoginRespone;
import com.example.retrofit_api_test.Respone.SignUpRespone;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    //    http://10.0.2.2:5001/api/v1/accounts/login
    Gson gson = new GsonBuilder()
        .setDateFormat("yyyy-MM-dd HH:mm:ss")
        .create();

    ApiService apiservice = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5001/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @POST("api/v1/accounts/login")
    Call<LoginRespone> logIn(@Body Login login);

    @POST("api/v1/accounts")
    Call<SignUpRespone> signUp(@Body SignUp signUp);
}

