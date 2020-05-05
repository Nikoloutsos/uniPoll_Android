package com.androiddreamer.unipoll.network;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

// Retrofit interface for interacting with uniPoll Server
public interface API {

    public static final String PREFIX = "unipoll/webservices/";

    @GET("get_active_polls.php")
    Call<ResponseBody> getActivePolls(
            @Query("user_id") int userId
    );

    @GET("get_completed_polls.php")
    Call<ResponseBody> getCompletedPolls(
            @Query("user_id") int userId
    );

    @GET("get_poll_details.php")
    Call<ResponseBody> getPollDetails(
            @Query("user_id") String userId,
            @Query("poll_id") int PollId
    );

    @GET("get_pin_for_email_authentication.php")
    Call<ResponseBody> getPollDetails(
            @Query("email") String userId
    );

    @GET("get_user_details.php")
    Call<ResponseBody> getUserDetails(
            @Query("user_id") String userId
    );

    @FormUrlEncoded
    @POST(PREFIX + "login_with_eclass.php")
    Call<ResponseBody> loginWithEclass(
            @Field("username") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST(PREFIX + "on_successful_pin_inserted_for_email_authentication.php")
    Call<ResponseBody> sendSuccessfulPINInsertedForEmail(
            @Field("user_id") String userId
    );

    @FormUrlEncoded
    @POST(PREFIX + "vote_for_poll.php")
    Call<ResponseBody> updatePushNotificationToken(
            @Field("user_id") String userId,
            @Field("option_id") String optionId
    );
}
