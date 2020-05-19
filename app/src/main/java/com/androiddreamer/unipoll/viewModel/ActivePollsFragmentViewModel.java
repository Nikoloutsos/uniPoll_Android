package com.androiddreamer.unipoll.viewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.androiddreamer.unipoll.Application;
import com.androiddreamer.unipoll.model.Repository;
import com.androiddreamer.unipoll.network.RetrofitConfig;
import com.androiddreamer.unipoll.util.SwiftyJSONObject;
import com.androiddreamer.unipoll.util.UDHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.KotlinExtensions;
import retrofit2.Response;

public class ActivePollsFragmentViewModel extends ViewModel {
    Repository repository;
    UDHelper udHelper;

    public ActivePollsFragmentViewModel() {
        repository = Repository.getInstance();
        udHelper = new UDHelper(Application.applicationContext);
    }

    /**
     * Network calls
     */
    public LiveData<List<JSONObject>> callGetPolls(String userId){
        MutableLiveData<List<JSONObject>> liveData = new MutableLiveData<>();

        RetrofitConfig.callApi().getUserPolls(userId).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String string = response.body().string();
                    JSONObject jsonObject = new JSONObject(string);
                    liveData.postValue(transformDataForUI(jsonObject));
                } catch (Exception e) {
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

    private List<JSONObject> transformDataForUI(JSONObject jsonObject) throws JSONException {
        List<JSONObject> list = new ArrayList<>();
        JSONArray activePolls = jsonObject.getJSONArray("active_polls");

        for (int i = 0; i < activePolls.length(); i++) {
            list.add(activePolls.getJSONObject(i));
        }

        return list;
    }


}
