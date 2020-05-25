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

    @FormUrlEncoded
    @POST(PREFIX + "app/get_poll_details.php")
    Call<ResponseBody> getPollDetails(
            @Field("user_id") String userId,
            @Field("poll_id") int pollId
    );

    @FormUrlEncoded
    @POST(PREFIX + "app/add_vote.php")
    Call<ResponseBody> vote(
            @Field("user_id") String userId,
            @Field("poll_id") int pollId,
            @Field("option_id") int optionId

    );

    @FormUrlEncoded
    @POST(PREFIX + "app/get_user_polls.php")
    Call<ResponseBody> getUserPolls(
            @Field("user_id") String userId
    );

    @FormUrlEncoded
    @POST(PREFIX + "app/get_super_user_groups.php")
    Call<ResponseBody> getAdminGroups(
            @Field("user_id") String userId
    );

    @FormUrlEncoded
    @POST(PREFIX + "app/add_poll.php")
    Call<ResponseBody> createAPoll(
            @Field("group_id") String groupId,
            @Field("question") String questionString,
            @Field("options") String optionsArray,
            @Field("end_time") String endTime
    );

    @FormUrlEncoded
    @POST(PREFIX + "app/get_super_user_active_polls.php")
    Call<ResponseBody> getSuperUserPolls(
            @Field("user_id") String userId
    );

    @FormUrlEncoded
    @POST(PREFIX + "app/login_with_eclass.php")
    Call<ResponseBody> logInWithEclass(
            @Field("username") String userId,
            @Field("password") String password,
            @Field("token") String fcmToken
    );





    @GET("get_active_polls.php")
    Call<ResponseBody> getActivePolls(
            @Query("user_id") String userId
    );

    @GET("get_completed_polls.php")
    Call<ResponseBody> getCompletedPolls(
            @Query("user_id") String userId
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
