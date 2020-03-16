package com.androiddreamer.unipoll.network;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

// Retrofit interface for interacting with uniPoll Server
public interface API {

    public static final String PREFIX = "UniPoll/Webservices/";

    @FormUrlEncoded
    @POST(PREFIX + "loginWithEclass.php")
    Call<ResponseBody> loginWithEclass(
            @Field("username") String email,
            @Field("password") String password,
            @Field("deviceToken") String fcmToken
    );



}
