package com.androiddreamer.unipoll.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.androiddreamer.unipoll.R;
import com.androiddreamer.unipoll.databinding.ActivityPollDetailBinding;
import com.androiddreamer.unipoll.util.UDHelper;
import com.androiddreamer.unipoll.viewModel.PollDetailViewModel;

import org.json.JSONException;
import org.json.JSONObject;

public class PollDetail extends AppCompatActivity {

    ActivityPollDetailBinding binding;
    PollDetailViewModel viewModel;
    int pollId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_poll_detail);
        viewModel = ViewModelProviders.of(this).get(PollDetailViewModel.class);
        pollId = getIntent().getExtras().getInt("id");
    }


    @Override
    protected void onResume() {
        super.onResume();

        UDHelper udHelper = new UDHelper(getApplicationContext());
        viewModel.getPollDetails(udHelper.getString(UDHelper.KEY_USER_ID), pollId).observe(this,
                new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        if (s == null) return;
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            String question = jsonObject.getString("question");
                            binding.textView5.setText(question);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

    }
}
