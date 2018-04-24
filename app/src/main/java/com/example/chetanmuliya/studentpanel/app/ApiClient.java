package com.example.chetanmuliya.studentpanel.app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by domaine on 24/04/18.
 */

public class ApiClient {

    public static Retrofit retrofit = null;

    static Gson gson = new GsonBuilder().setLenient().create();

    public static Retrofit getClient(){
        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(AppConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson)).build();
        }
        return retrofit;
    }
}
