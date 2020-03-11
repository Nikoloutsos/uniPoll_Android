package com.androiddreamer.unipoll.network;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

// Retrofit interface for interacting with uniPoll Server
public interface API {

    public static final String PREFIX = "Prefix_for_webservice";

    @FormUrlEncoded
    @POST(PREFIX + "app/login.php")
    Call<ResponseBody> loginWithEclass(
            @Field("username") String email,
            @Field("password") String password,
            @Field("deviceToken") String fcmToken
    );

    @FormUrlEncoded
    @POST(PREFIX + "app/login.php")
    Call<ResponseBody> sendAuthEmail(
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST(PREFIX + "app/login.php")
    Call<ResponseBody> verifyAuthPin(
            @Field("email") String email,
            @Field("deviceToken") String fcmToken
    );

}
