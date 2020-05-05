package com.androiddreamer.unipoll.model;

import androidx.lifecycle.LiveData;

import com.androiddreamer.unipoll.network.API;
import com.androiddreamer.unipoll.network.RetrofitConfig;

import org.json.JSONObject;

public class Repository {
    API api;
    private static Repository repository;

    private Repository(){}

    public static Repository getInstance() {
        if(repository == null) repository = new Repository();
        repository.api = RetrofitConfig.callApi();
        return repository;
    }

    public LiveData<JSONObject> getPINForEmailAuthentication(String email){
        return null;
    }

    public LiveData<JSONObject> getAllActivePolls(String userId){
        return null;
    }

    public LiveData<JSONObject> getCompletedPolls(String userId){
        return null;
    }

    public LiveData<JSONObject> getPollDetails(String pollId){
        return null;
    }

    public LiveData<JSONObject> getUserDetails(String userId){
        return null;
    }

    public LiveData<JSONObject> sendLogInEclass(String email){
        return null;
    }

    public LiveData<JSONObject> sendSuccessPinInserted(String email){
        return null;
    }

    public LiveData<Boolean> sendVoteForPoll(String pollId){
        return null;
    }

    public LiveData<Boolean> sendPushNotificationToken(String pollId){
        return null;
    }
}
