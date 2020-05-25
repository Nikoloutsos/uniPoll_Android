package com.androiddreamer.unipoll.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.androiddreamer.unipoll.R;
import com.androiddreamer.unipoll.databinding.ActivitySuperUserMenuBinding;
import com.androiddreamer.unipoll.view.SuperUserPollsListFragment;
import com.androiddreamer.unipoll.view.fragment.ActivePollListFragment;

public class SuperUserMenuActivity extends AppCompatActivity {
    ActivitySuperUserMenuBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_super_user_menu);

        SuperUserPollsListFragment superUserPollsListFragment = new SuperUserPollsListFragment();
        getSupportFragmentManager().beginTransaction().add(binding.container.getId(), superUserPollsListFragment, "0").commit();



        binding.addPollFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SuperUserMenuActivity.this, CreatePollActivity.class));
            }
        });


    }
}
