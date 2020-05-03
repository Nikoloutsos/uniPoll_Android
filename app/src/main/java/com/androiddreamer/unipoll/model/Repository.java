package com.androiddreamer.unipoll.model;

import androidx.lifecycle.LiveData;

import com.androiddreamer.unipoll.model.network.API;

import org.json.JSONObject;

import retrofit2.Retrofit;

public class Repository {
    API api;
    private static Repository repository;

    private Repository(){}

    public static Repository getInstance() {
        if(repository == null) repository = new Repository();
        return repository;
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

    public LiveData<Boolean> setVoteForPoll(String pollId){
        return null;
    }
}
