package com.androiddreamer.unipoll.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.androiddreamer.unipoll.R;
import com.androiddreamer.unipoll.viewModel.LoginViewModel;
import com.androiddreamer.unipoll.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Enable data binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        //Initialize viewModel
        viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);


        binding.loginWithEclass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, EclassLogInActivity.class));
            }
        });

        binding.loginWithEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, EmailLoginActivity.class));
            }
        });
    }
}
