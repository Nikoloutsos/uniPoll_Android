package com.androiddreamer.unipoll.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.androiddreamer.unipoll.R;
import com.androiddreamer.unipoll.databinding.ActivityPollDetailBinding;

public class PollDetail extends AppCompatActivity {

    ActivityPollDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_poll_detail);


    }
}
