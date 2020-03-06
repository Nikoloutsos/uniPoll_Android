package com.androiddreamer.unipoll.viewModel;

import android.util.Log;
import android.view.View;
import androidx.lifecycle.ViewModel;

import com.androiddreamer.unipoll.R;
import com.androiddreamer.unipoll.databinding.ActivityLoginBinding;

public class LoginViewModel extends ViewModel {

    private ActivityLoginBinding binding;


    //Sets the binding for the
    public void setBinding(ActivityLoginBinding binding){
        this.binding = binding;
    }

    public void initializeLiveData(){

    }

    public void setHandler(){
        binding.setHandler(new Handler());
    }


    //Responsible for setting functionality in Views
    public static class Handler{
        public void onEventHappened(View view){
            switch (view.getId()){
                case R.id.dd:
                    Log.d("aabb", "onEventHappened: ");
                    break;
                default:
                    break;

            }
        }

    }
}
