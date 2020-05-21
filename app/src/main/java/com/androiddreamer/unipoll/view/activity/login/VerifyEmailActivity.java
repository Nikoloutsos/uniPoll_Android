package com.androiddreamer.unipoll.view.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.androiddreamer.unipoll.R;
import com.androiddreamer.unipoll.databinding.ActivityEmailVerificationBinding;
import com.androiddreamer.unipoll.util.UDHelper;
import com.androiddreamer.unipoll.view.activity.MainActivity;
import com.androiddreamer.unipoll.viewModel.VerifyEmailViewModel;

import org.json.JSONException;
import org.json.JSONObject;


public class VerifyEmailActivity extends AppCompatActivity {
    ActivityEmailVerificationBinding binding;
    VerifyEmailViewModel viewModel;
    String pin;
    int attemptsAuth = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_email_verification);
        viewModel = ViewModelProviders.of(this).get(VerifyEmailViewModel.class);

        String email = getIntent().getExtras().getString("email");
        binding.hintBoxTv.setText("Check " + email + " for the PIN");


        viewModel.getPINForAuthenticateEmail(email).observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s == null) return;
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    pin = jsonObject.getString("pin");

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        binding.verifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pin == null) return;
                if (attemptsAuth++ >= 4) finish();
                if (pin.equalsIgnoreCase(binding.firstPinView.getText().toString())) {
                    UDHelper udHelper = new UDHelper(getApplicationContext());
                    String fcmToken = udHelper.getString(UDHelper.KEY_FIREBASE_TOKEN);
                    viewModel.sendPinInsertedSuccessfullyToServer(email, fcmToken).observe(VerifyEmailActivity.this, new Observer<String>() {
                        @Override
                        public void onChanged(String s) {
                            try {
                                JSONObject jsonObject = new JSONObject(s);
                                int status = jsonObject.getInt("status");
                                if (status == 1) {
                                    String userId = jsonObject.getString("user_id");
                                    UDHelper udHelper = new UDHelper(getApplicationContext());
                                    udHelper.setString(UDHelper.KEY_USER_ID, userId);
                                    startActivity(new Intent(VerifyEmailActivity.this, MainActivity.class));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } else {
                    Toast.makeText(VerifyEmailActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                    binding.firstPinView.setText("");
                }
            }
        });
    }
}
