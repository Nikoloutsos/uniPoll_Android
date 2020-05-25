package com.androiddreamer.unipoll.view.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.androiddreamer.unipoll.R;
import com.androiddreamer.unipoll.databinding.ActivityEmailLoginBinding;
import com.androiddreamer.unipoll.util.RegexUtil;

public class EmailLoginActivity extends AppCompatActivity {
    ActivityEmailLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_email_login);


        binding.nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailString = binding.emailEt.getText().toString();
                if (emailString.isEmpty()) {
                    Toast.makeText(EmailLoginActivity.this, "Please enter your educational email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!RegexUtil.isEmail(emailString)) {
                    Toast.makeText(EmailLoginActivity.this, "Given email doesn't look like a real email. Try another one!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(EmailLoginActivity.this, VerifyEmailActivity.class);
                intent.putExtra("email", emailString);
                startActivity(intent);
                finish();
            }
        });
    }


}
