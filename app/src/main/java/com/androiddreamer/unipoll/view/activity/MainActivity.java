package com.androiddreamer.unipoll.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.androiddreamer.unipoll.R;
import com.androiddreamer.unipoll.databinding.ActivityMainBinding;
import com.androiddreamer.unipoll.view.fragment.ActivePollListFragment;
import com.androiddreamer.unipoll.view.fragment.CompletedPollListFragment;
import com.androiddreamer.unipoll.view.fragment.ProfileFragment;
import com.google.firebase.iid.FirebaseInstanceId;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import nl.joery.animatedbottombar.AnimatedBottomBar;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("pokemon", "onCreate: " + FirebaseInstanceId.getInstance().getToken());

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        initBottomNavigationBar();

        startActivity(new Intent(this, SuperUserMenuActivity.class));

        ActivePollListFragment activePollListFragment = new ActivePollListFragment();
        getSupportFragmentManager().beginTransaction().add(binding.fragmentHolder.getId(), activePollListFragment, "0").commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void initBottomNavigationBar() {
        binding.bottomNavBar.selectTab(binding.bottomNavBar.getTabs().get(0), false);
        binding.bottomNavBar.setOnTabSelectListener(new AnimatedBottomBar.OnTabSelectListener() {
            @Override
            public void onTabSelected(int i, @Nullable AnimatedBottomBar.Tab tab, int i1, @NotNull AnimatedBottomBar.Tab tab1) {
                switch (i1){
                    case 0:
                        ActivePollListFragment activePollListFragment = new ActivePollListFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, activePollListFragment, "0").commit();
                        break;
                    case 1:
                        CompletedPollListFragment completedPollListFragment = new CompletedPollListFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, completedPollListFragment, "1").commit();
                        break;
                    case 2:
                        ProfileFragment profileFragment = new ProfileFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, profileFragment, "2").commit();
                        break;
                }
            }

            @Override
            public void onTabReselected(int i, @NotNull AnimatedBottomBar.Tab tab) {

            }
        });
    }
}
