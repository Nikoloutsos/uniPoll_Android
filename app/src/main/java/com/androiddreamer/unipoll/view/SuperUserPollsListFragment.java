package com.androiddreamer.unipoll.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androiddreamer.unipoll.R;
import com.androiddreamer.unipoll.databinding.FragmentSuperUserPollsListBinding;
import com.androiddreamer.unipoll.network.RetrofitConfig;
import com.androiddreamer.unipoll.util.UDHelper;
import com.androiddreamer.unipoll.view.adapter.PollListSuperUserAdapter;
import com.androiddreamer.unipoll.viewModel.PollsViewModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SuperUserPollsListFragment extends Fragment {
    FragmentSuperUserPollsListBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_super_user_polls_list, container, false);
        return binding.getRoot();
    }


    @Override
    public void onResume() {
        super.onResume();

        UDHelper udHelper = new UDHelper(getActivity());
        RetrofitConfig.callApi().getSuperUserPolls(udHelper.getString(UDHelper.KEY_USER_ID)).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String string = response.body().string();
                    JSONObject jsonObject = new JSONObject(string);
                    JSONArray data = jsonObject.getJSONArray("data");

                    List<JSONObject> jsonObjects = new ArrayList<>();
                    for (int i = 0; i < data.length(); i++) {
                        jsonObjects.add(data.getJSONObject(i));
                    }

                    binding.rv.setLayoutManager(new LinearLayoutManager(getActivity()));
                    binding.rv.setAdapter(new PollListSuperUserAdapter(getActivity(), jsonObjects));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
