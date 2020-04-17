package com.androiddreamer.unipoll.viewModel;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import androidx.lifecycle.ViewModel;

import com.androiddreamer.unipoll.R;
import com.androiddreamer.unipoll.activity.EclassLogInActivity;
import com.androiddreamer.unipoll.activity.EmailLoginActivity;
import com.androiddreamer.unipoll.databinding.ActivityLoginBinding;

public class LoginViewModel extends ViewModel {

    private ActivityLoginBinding binding;
    private Context context;

    public void bindActivity(Context context, ActivityLoginBinding binding){
        this.context = context;
        this.binding = binding;
    }

}
