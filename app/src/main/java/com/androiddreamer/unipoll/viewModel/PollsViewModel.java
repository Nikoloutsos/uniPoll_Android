package com.androiddreamer.unipoll.viewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.androiddreamer.unipoll.Application;
import com.androiddreamer.unipoll.network.RetrofitConfig;
import com.androiddreamer.unipoll.util.UDHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PollsViewModel extends ViewModel {
    UDHelper udHelper;

    public PollsViewModel() {
        udHelper = new UDHelper(Application.applicationContext);
    }

    /**
     * Network calls
     */
    public LiveData<List<JSONObject>> callGetActivePolls(String userId) {
        MutableLiveData<List<JSONObject>> liveData = new MutableLiveData<>();

        RetrofitConfig.callApi().getUserPolls(userId).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String string = response.body().string();
                    JSONObject jsonObject = new JSONObject(string);
                    liveData.postValue(transformDataForUIForActivePolls(jsonObject));
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

    private List<JSONObject> transformDataForUIForActivePolls(JSONObject jsonObject) throws JSONException {
        List<JSONObject> list = new ArrayList<>();
        JSONArray activePolls = jsonObject.getJSONArray("active_polls");

        for (int i = 0; i < activePolls.length(); i++) {
            list.add(activePolls.getJSONObject(i));
        }

        return list;
    }

    public LiveData<List<JSONObject>> callGetCompletedPolls(String userId) {
        MutableLiveData<List<JSONObject>> liveData = new MutableLiveData<>();

        RetrofitConfig.callApi().getUserPolls(userId).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String string = response.body().string();
                    JSONObject jsonObject = new JSONObject(string);
                    liveData.postValue(transformDataForUIForCompletedPolls(jsonObject));
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

    private List<JSONObject> transformDataForUIForCompletedPolls(JSONObject jsonObject) throws JSONException {
        List<JSONObject> list = new ArrayList<>();
        JSONArray activePolls = jsonObject.getJSONArray("completed_polls");

        for (int i = 0; i < activePolls.length(); i++) {
            list.add(activePolls.getJSONObject(i));
        }

        return list;
    }


}
