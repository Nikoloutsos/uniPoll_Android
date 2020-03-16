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

    // Livedata


    public void bindActivity(Context context, ActivityLoginBinding binding){
        this.context = context;
        this.binding = binding;
        binding.setHandler(new Handler(context));
    }


    private void initializeLiveData(){

    }











    //Responsible for setting functionality in Views
    public static class Handler{
        private Context context;
        Handler(Context context){
            this.context = context;
        }

        public void onEventHappened(View view){
            switch (view.getId()){
                case R.id.login_with_eclass:
                    Log.d("aabb", "onEventHappened: login with eclass");
                    context.startActivity(new Intent(context, EclassLogInActivity.class));
                    break;

                case R.id.login_with_email:
                    Log.d("aabb", "onEventHappened: login with email");
                    context.startActivity(new Intent(context, EmailLoginActivity.class));

                    break;
                default:
                    break;

            }
        }

    }
}
