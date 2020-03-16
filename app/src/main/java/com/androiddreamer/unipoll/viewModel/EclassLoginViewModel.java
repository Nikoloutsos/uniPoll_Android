package com.androiddreamer.unipoll.viewModel;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.androiddreamer.unipoll.R;
import com.androiddreamer.unipoll.activity.EclassLogInActivity;
import com.androiddreamer.unipoll.activity.EmailLoginActivity;
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
    private MutableLiveData<Boolean> liveDataUserConnectedInfo;
    private MutableLiveData<Boolean> liveDataError;
    private MutableLiveData<String>  liveDataWaitSpinner;

    public void bindActivity(Context context, ActivityEclassLogInBinding binding){
        this.context = context;
        this.binding = binding;
        binding.setHandler(new Handler(context, this));
        initializeLiveData();
    }

    private void initializeLiveData(){
        liveDataUserConnectedInfo = new MutableLiveData<>();
        liveDataError = new MutableLiveData<>();
        liveDataWaitSpinner = new MutableLiveData<>();
    }

    /**
     * Business logic
     */
    public void signWithEclass(String username, String password, String deviceToken){
        //TODO send api request
        RetrofitConfig.callApi().loginWithEclass(username, password, deviceToken).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String responseString = response.body().string();
                    liveDataWaitSpinner.setValue(responseString);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                liveDataError.setValue(true);
            }
        });
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

    /**
     * Responsible for click interactions
     */
    public static class Handler{
        private EclassLoginViewModel viewModel;
        Handler(Context context, EclassLoginViewModel viewModel){
            this.viewModel = viewModel;
        }

        public void onEventHappened(View view){
            switch (view.getId()){
                case R.id.sign_in_btn:
                    String username = "p3170122";
                    String password = "22920276324story";
                    String deciceToken = "dasfhjefejwfrn32";

                    viewModel.signWithEclass(username, password, deciceToken);
                    break;
                default:
                    break;

            }
        }
    }


}