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

    public static final String PREFIX = "UniPoll/webservices/";

    @FormUrlEncoded
    @POST(PREFIX + "app/send_auth_pin_email.php")
    Call<ResponseBody> getAndSendAuthPinEmail(
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST(PREFIX + "app/complete_email_authorization.php")
    Call<ResponseBody> sendSuccesfullPINToServer(
            @Field("email") String email,
            @Field("token") String pushNotificationToken
    );



    @GET("get_active_polls.php")
    Call<ResponseBody> getActivePolls(
            @Query("user_id") String userId
    );

    @GET("get_completed_polls.php")
    Call<ResponseBody> getCompletedPolls(
            @Query("user_id") String userId
    );

    @GET("get_poll_details.php")
    Call<ResponseBody> getPollDetails(
            @Query("user_id") String userId,
            @Query("poll_id") int PollId
    );

    @GET("get_pin_for_email_authentication.php")
    Call<ResponseBody> getPINForEmailAuthentication(
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
    Call<ResponseBody> voteForPoll(
            @Field("user_id") String userId,
            @Field("option_id") String optionId
    );

    @FormUrlEncoded
    @POST(PREFIX + "update_push_notification_token.php")
    Call<ResponseBody> updatePushNotificationToken(
            @Field("user_id") String userId,
            @Field("push_notification_token") String optionId
    );
}
