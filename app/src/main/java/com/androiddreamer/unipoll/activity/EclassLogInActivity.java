package com.androiddreamer.unipoll.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.Toast;

import com.androiddreamer.unipoll.R;
import com.androiddreamer.unipoll.databinding.ActivityEclassLogInBinding;
import com.androiddreamer.unipoll.viewModel.EclassLoginViewModel;
import com.androiddreamer.unipoll.viewModel.LoginViewModel;

public class EclassLogInActivity extends AppCompatActivity {


    ActivityEclassLogInBinding binding;
    EclassLoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Enable data binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_eclass_log_in);

        //Initialize viewModel
        viewModel = ViewModelProviders.of(this).get(EclassLoginViewModel.class);
        viewModel.bindActivity(this, binding);

        //Observe livedata
        viewModel.getLiveDataError().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                Toast.makeText(EclassLogInActivity.this, "liveDataError triggered", Toast.LENGTH_SHORT).show();
            }
        });
        viewModel.getLiveDataWaitSpinner().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(EclassLogInActivity.this, s, Toast.LENGTH_SHORT).show();

            }
        });
        viewModel.getLiveDataUserConnectedInfo().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                Toast.makeText(EclassLogInActivity.this, "connectedInfo triggered", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
