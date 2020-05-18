package com.androiddreamer.unipoll.viewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.androiddreamer.unipoll.databinding.ActivityEclassLogInBinding;
import com.androiddreamer.unipoll.network.RetrofitConfig;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EclassLoginViewModel extends ViewModel {

    Context context;
    ActivityEclassLogInBinding binding;

    /**
     * Livedata
     */
    private MutableLiveData<Boolean> liveDataUserConnectedInfo = new MutableLiveData<>();
    private MutableLiveData<Boolean> liveDataError = new MutableLiveData<>();
    private MutableLiveData<String>  liveDataWaitSpinner = new MutableLiveData<>();

    /**
     * Business logic
     */
    public void signWithEclass(String username, String password, String deviceToken){
        //TODO send api request
//        RetrofitConfig.callApi().loginWithEclass(username, password, deviceToken).enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                try {
//                    String responseString = response.body().string();
//                    liveDataWaitSpinner.setValue(responseString);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                liveDataError.setValue(true);
//            }
//        });
    }

    /**
     * Getter for livedata
     */
    public LiveData<Boolean> getLiveDataUserConnectedInfo(){
        return liveDataUserConnectedInfo;
    }
    public LiveData<Boolean> getLiveDataError(){
        return liveDataError;
    }
    public LiveData<String> getLiveDataWaitSpinner(){
        return liveDataWaitSpinner;
    }




}
