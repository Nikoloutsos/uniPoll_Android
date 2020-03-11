package com.androiddreamer.unipoll.viewModel;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import androidx.lifecycle.ViewModel;

import com.androiddreamer.unipoll.R;
import com.androiddreamer.unipoll.activity.EclassLogInActivity;
import com.androiddreamer.unipoll.databinding.ActivityLoginBinding;

public class LoginViewModel extends ViewModel {

    private ActivityLoginBinding binding;
    private Context context;


    //Sets the binding for the
    public void setBinding(ActivityLoginBinding binding){
        this.binding = binding;
    }

    public void initializeLiveData(){

    }

    public void setHandler(){
        binding.setHandler(new Handler(context));
    }

    public void setContext(Context context){
        this.context = context;
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

                    break;
                default:
                    break;

            }
        }

    }
}
