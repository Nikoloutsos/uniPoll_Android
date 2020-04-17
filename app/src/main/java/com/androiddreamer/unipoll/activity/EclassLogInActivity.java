package com.androiddreamer.unipoll.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.androiddreamer.unipoll.R;
import com.androiddreamer.unipoll.databinding.ActivityEclassLogInBinding;
import com.androiddreamer.unipoll.viewModel.EclassLoginViewModel;
import com.androiddreamer.unipoll.viewModel.LoginViewModel;
import com.google.android.material.snackbar.Snackbar;

public class EclassLogInActivity extends AppCompatActivity {


    ActivityEclassLogInBinding binding;
    EclassLoginViewModel viewModel;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("Connecting with e-class");

        //Enable data binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_eclass_log_in);

        //Initialize viewModel
        viewModel = ViewModelProviders.of(this).get(EclassLoginViewModel.class);


        //Observe livedata
        viewModel.getLiveDataError().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {

            }
        });
        viewModel.getLiveDataWaitSpinner().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                toggleLoadingSpinner(true);
            }
        });


        viewModel.getLiveDataUserConnectedInfo().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                Toast.makeText(EclassLogInActivity.this, "connectedInfo triggered", Toast.LENGTH_SHORT).show();
            }
        });


        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EclassLogInActivity.this, MainActivity.class));
            }
        });

    }


    private void showSnackbarWrongCredentials(){
        View root = binding.getRoot();
        Snackbar snackbar = Snackbar
                .make(root, "Wrong username or password", Snackbar.LENGTH_SHORT);
        snackbar.getView().setBackgroundColor(getResources().getColor(R.color.colorAccent));
        snackbar.show();
    }

    private void toggleLoadingSpinner(boolean b){
        if(b){
            progressDialog.show();
        }else{
            progressDialog.hide();
        }

    }



}
