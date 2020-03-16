package com.androiddreamer.unipoll.activity;

        import androidx.appcompat.app.AppCompatActivity;

        import android.os.Bundle;

        import com.androiddreamer.unipoll.R;

public class EmailLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setTheme(R.style.AppThemeLogInEmail);
        setContentView(R.layout.activity_email_login);
    }
}
