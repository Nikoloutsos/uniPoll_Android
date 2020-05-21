package com.androiddreamer.unipoll.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.androiddreamer.unipoll.R;
import com.androiddreamer.unipoll.databinding.ActivitySuperUserMenuBinding;

public class SuperUserMenuActivity extends AppCompatActivity {
    ActivitySuperUserMenuBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_super_user_menu);

        binding.addPollFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SuperUserMenuActivity.this, CreatePollActivity.class));
            }
        });


    }
}
