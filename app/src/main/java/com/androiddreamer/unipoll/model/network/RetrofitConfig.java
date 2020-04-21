package com.androiddreamer.unipoll.model.network;

import retrofit2.Retrofit;

//Class which configures retrofit
public class RetrofitConfig {

    public static final String BASE_URL = "http://828071906.linuxzone147.grserver.gr/";

    public static API callApi(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build();

        API service = retrofit.create(API.class);
        return service;
    }




}
