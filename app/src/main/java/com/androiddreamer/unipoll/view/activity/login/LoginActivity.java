package com.androiddreamer.unipoll.view.activity.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.androiddreamer.unipoll.R;
import com.androiddreamer.unipoll.util.UDHelper;
import com.androiddreamer.unipoll.view.activity.MainActivity;
import com.androiddreamer.unipoll.viewModel.LoginViewModel;
import com.androiddreamer.unipoll.databinding.ActivityLoginBinding;
import com.google.firebase.iid.FirebaseInstanceId;

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


    @Override
    protected void onResume() {
        super.onResume();

        UDHelper udHelper = new UDHelper(getApplicationContext());
        String userId = udHelper.getString(UDHelper.KEY_USER_ID);
        if(!userId.isEmpty()){
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

    }
}
