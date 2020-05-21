package com.androiddreamer.unipoll.viewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.androiddreamer.unipoll.network.RetrofitConfig;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PollDetailViewModel extends ViewModel {

    public LiveData<String> getPollDetails(String userId, int pollId) {
        MutableLiveData<String> liveData = new MutableLiveData<>();
        RetrofitConfig.callApi().getPollDetails(userId, pollId).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    liveData.postValue(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("test", "onFailure: ");
            }
        });
        return liveData;
    }


    public LiveData<Boolean> vote(String userId, int pollId, int optionId) {
        MutableLiveData<Boolean> liveData = new MutableLiveData<>();
        RetrofitConfig.callApi().vote(userId, pollId, optionId).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    if (jsonObject.getInt("status") == 1) {
                        liveData.postValue(true);
                    } else {
                        liveData.postValue(false);
                    }
                } catch (Exception e) {
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
