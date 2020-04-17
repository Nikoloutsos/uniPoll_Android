package com.androiddreamer.unipoll.activity;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.constraintlayout.widget.ConstraintLayout;

        import android.content.Intent;
        import android.content.res.ColorStateList;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.Toast;

        import com.androiddreamer.unipoll.R;
        import com.google.android.material.snackbar.Snackbar;

public class EmailLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_login);

        Button btn = findViewById(R.id.next_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EmailLoginActivity.this, verifyEmailActivity.class));





            }
        });


    }
}
