package com.androiddreamer.unipoll.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.androiddreamer.unipoll.R;
import com.androiddreamer.unipoll.databinding.ActivityMainBinding;
import com.androiddreamer.unipoll.view.fragment.PollListFragment;
import com.androiddreamer.unipoll.view.fragment.ProfileFragment;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import nl.joery.animatedbottombar.AnimatedBottomBar;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        PollListFragment fragment = new PollListFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, fragment, "tag1").commit();

        AnimatedBottomBar bottomBar = findViewById(R.id.bottom_nav_bar1);
        bottomBar.selectTab(bottomBar.getTabs().get(0), false);

        bottomBar.setOnTabSelectListener(new AnimatedBottomBar.OnTabSelectListener() {
            @Override
            public void onTabSelected(int i, @Nullable AnimatedBottomBar.Tab tab, int i1, @NotNull AnimatedBottomBar.Tab tab1) {
                switch (i1){
                    case 0:
                        PollListFragment fragment = new PollListFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, fragment, "1").commit();
                        break;
                    case 1:
                        ProfileFragment fragment1 = new ProfileFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, fragment1, "2").commit();
                        break;
                }
            }

            @Override
            public void onTabReselected(int i, @NotNull AnimatedBottomBar.Tab tab) {

            }
        });

    }
}
