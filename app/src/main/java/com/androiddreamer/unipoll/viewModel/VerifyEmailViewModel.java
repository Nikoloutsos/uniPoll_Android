package com.androiddreamer.unipoll.viewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.androiddreamer.unipoll.network.RetrofitConfig;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyEmailViewModel extends ViewModel {

    public LiveData<String> getPINForAuthenticateEmail(String email) {
        MutableLiveData<String> mutableLiveData = new MutableLiveData<>();
        RetrofitConfig.callApi().getAndSendAuthPinEmail(email).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String resp = response.body().string();
                    mutableLiveData.postValue(resp);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println("aa");
            }
        });
        return mutableLiveData;
    }

    public LiveData<String> sendPinInsertedSuccessfullyToServer(String email, String fcmToken) {
        MutableLiveData<String> mutableLiveData = new MutableLiveData<>();
        RetrofitConfig.callApi().sendSuccesfullPINToServer(email, fcmToken).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    mutableLiveData.postValue(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("test", "onFailure: ");
            }
        });
        return mutableLiveData;
    }


}
