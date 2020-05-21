package com.androiddreamer.unipoll.view.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.androiddreamer.unipoll.R;
import com.androiddreamer.unipoll.databinding.ActivityCreatePollBinding;

import java.util.ArrayList;
import java.util.List;

public class CreatePollActivity extends AppCompatActivity {
    ActivityCreatePollBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_poll);

        List<String> a = new ArrayList<>();
        a.add("Group1");
        a.add("Group2");
        a.add("Group3");
        a.add("Group4");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CreatePollActivity.this,
                android.R.layout.simple_spinner_item, a);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.groupSpinner.setAdapter(adapter);
    }
}
