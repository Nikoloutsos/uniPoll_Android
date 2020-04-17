package com.androiddreamer.unipoll.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.androiddreamer.unipoll.R;
import com.androiddreamer.unipoll.databinding.SplashScreenBinding;

public class SplashScreenActivity extends AppCompatActivity {

    SplashScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Hides status bar
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Enable data binding
        binding = DataBindingUtil.setContentView(this, R.layout.splash_screen);

        //Redirect to next activity after delay
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreenActivity.this, LoginActivity.class);
                startActivity(i);
            }
        }, 1000);
    }
}
