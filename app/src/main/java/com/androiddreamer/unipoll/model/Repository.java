package com.androiddreamer.unipoll.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.androiddreamer.unipoll.network.API;
import com.androiddreamer.unipoll.network.RetrofitConfig;
import com.androiddreamer.unipoll.util.SwiftyJSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {
    API api;
    private static Repository repository;

    private Repository(){}

    public static Repository getInstance() {
        if(repository == null) repository = new Repository();
        repository.api = RetrofitConfig.callApi();
        return repository;
    }

    public LiveData<SwiftyJSONObject> getPINForEmailAuthentication(String email){
        MutableLiveData<SwiftyJSONObject> result = new MutableLiveData<>();
        api.getPINForEmailAuthentication(email).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                SwiftyJSONObject jsonResponse = getJSONResponse(response);
                result.setValue(jsonResponse);
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
        return result;
    }

    public LiveData<SwiftyJSONObject> getActivePolls(String userId){
        MutableLiveData<SwiftyJSONObject> result = new MutableLiveData<>();
        api.getActivePolls(userId).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                SwiftyJSONObject jsonResponse = getJSONResponse(response);
                result.setValue(jsonResponse);
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
        return result;
    }

    public LiveData<SwiftyJSONObject> getCompletedPolls(String userId){
        MutableLiveData<SwiftyJSONObject> result = new MutableLiveData<>();
        api.getCompletedPolls(userId).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                SwiftyJSONObject jsonResponse = getJSONResponse(response);
                result.setValue(jsonResponse);
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
        return result;
    }

    public LiveData<SwiftyJSONObject> getPollDetails(String userId, int pollId){
        MutableLiveData<SwiftyJSONObject> result = new MutableLiveData<>();
        api.getPollDetails(userId, pollId).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                SwiftyJSONObject jsonResponse = getJSONResponse(response);
                result.setValue(jsonResponse);
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
        return result;
    }

    public LiveData<SwiftyJSONObject> getUserDetails(String userId){
        MutableLiveData<SwiftyJSONObject> result = new MutableLiveData<>();
        api.getUserDetails(userId).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                SwiftyJSONObject jsonResponse = getJSONResponse(response);
                result.setValue(jsonResponse);
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
        return result;
    }

    public LiveData<SwiftyJSONObject> sendLogInEclass(String email, String password){
        MutableLiveData<SwiftyJSONObject> result = new MutableLiveData<>();
        api.loginWithEclass(email, password).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                SwiftyJSONObject jsonResponse = getJSONResponse(response);
                result.setValue(jsonResponse);
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
        return result;
    }

    public LiveData<SwiftyJSONObject> sendSuccessPinInserted(String email){
        MutableLiveData<SwiftyJSONObject> result = new MutableLiveData<>();
        api.sendSuccessfulPINInsertedForEmail(email).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                SwiftyJSONObject jsonResponse = getJSONResponse(response);
                result.setValue(jsonResponse);
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
        return result;
    }

    public LiveData<SwiftyJSONObject> sendVoteForPoll(String userId, String pollId){
        MutableLiveData<SwiftyJSONObject> result = new MutableLiveData<>();
        api.voteForPoll(userId, pollId).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                SwiftyJSONObject jsonResponse = getJSONResponse(response);
                result.setValue(jsonResponse);
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
        return result;
    }

    public LiveData<SwiftyJSONObject> sendPushNotificationToken(String userId, String pushNotificationToken){
        MutableLiveData<SwiftyJSONObject> result = new MutableLiveData<>();
        api.updatePushNotificationToken(userId, pushNotificationToken).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                SwiftyJSONObject jsonResponse = getJSONResponse(response);
                result.setValue(jsonResponse);
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
        return result;
    }


    private SwiftyJSONObject getJSONResponse(Response<ResponseBody> response){
        try {
            String string = response.body().string();
            return new SwiftyJSONObject(string);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
