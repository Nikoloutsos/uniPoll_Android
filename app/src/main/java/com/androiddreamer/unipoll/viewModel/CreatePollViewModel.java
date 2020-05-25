package com.androiddreamer.unipoll.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.androiddreamer.unipoll.network.RetrofitConfig;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreatePollViewModel extends ViewModel {

    public LiveData<HashMap<String, String>> getAdminGroups(String adminId) {
        MutableLiveData mutableLiveData = new MutableLiveData();
        RetrofitConfig.callApi().getAdminGroups(adminId).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String string = response.body().string();
                    JSONObject jsonObject = new JSONObject(string);

                    HashMap<String, String> groups = new HashMap<>();

                    JSONArray groupsArray = jsonObject.getJSONArray("data");

                    for (int i = 0; i < groupsArray.length(); i++) {
                        JSONObject group = groupsArray.getJSONObject(i);
                        groups.put(group.getString("group_id"), group.getString("group_name"));
                    }
                    mutableLiveData.postValue(groups);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }

    public LiveData<Boolean> createPoll(String groupId, String questionString,
                                        String optionsArrayString, String endTime) {
        MutableLiveData<Boolean> liveData = new MutableLiveData<>();

        RetrofitConfig.callApi().createAPoll(groupId, questionString, optionsArrayString, endTime)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call,
                                           Response<ResponseBody> response) {
                        try {
                            String string = response.body().string();
                            liveData.postValue(true);
                        } catch (IOException e) {
                            e.printStackTrace();
                            liveData.postValue(false);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        liveData.postValue(false);
                    }
                });

        return liveData;
    }


}
