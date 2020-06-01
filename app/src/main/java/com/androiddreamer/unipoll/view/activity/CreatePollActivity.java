package com.androiddreamer.unipoll.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.androiddreamer.unipoll.R;
import com.androiddreamer.unipoll.databinding.ActivityCreatePollBinding;
import com.androiddreamer.unipoll.util.UDHelper;
import com.androiddreamer.unipoll.viewModel.CreatePollViewModel;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class CreatePollActivity extends AppCompatActivity {
    ActivityCreatePollBinding binding;
    CreatePollViewModel viewModel;
    HashMap<String, String> allOptions;

    private int numOfOptions = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_poll);
        viewModel = ViewModelProviders.of(this).get(CreatePollViewModel.class);

        UDHelper udHelper = new UDHelper(getApplicationContext());
        viewModel.getAdminGroups(udHelper.getString(UDHelper.KEY_USER_ID)).observe(this, new Observer<HashMap<String, String>>() {
            @Override
            public void onChanged(HashMap<String, String> stringStringHashMap) {
                if (stringStringHashMap == null) return;
                allOptions = stringStringHashMap;
                List<String> options = new ArrayList<>();
                for (String s : stringStringHashMap.keySet()) {
                    options.add(stringStringHashMap.get(s));
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(CreatePollActivity.this,
                        android.R.layout.simple_spinner_item, options);

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                binding.groupSpinner.setAdapter(adapter);
            }
        });

        for (int i = 0; i < 2; i++) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = inflater.inflate(R.layout.leyout_add_option, null);
            binding.optionsLl.addView(v);
            v.findViewById(R.id.remove_image).setAlpha(0.4f);
            v.findViewById(R.id.remove_image).setFocusable(false);
            v.findViewById(R.id.remove_image).setClickable(false);
        }

        binding.addNewAnswerLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = inflater.inflate(R.layout.leyout_add_option, null);
                binding.optionsLl.addView(v);

                ImageView lo = v.findViewById(R.id.remove_image);
                lo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("awl", "onClick: ");
                        binding.optionsLl.removeView((View) v.getParent().getParent());
                    }
                });

            }
        });


        binding.expTimeEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                                monthOfYear += 1;
                                String strMonth = monthOfYear < 10 ? "0" + monthOfYear : "" + monthOfYear;
                                String strDay = dayOfMonth < 10 ? "0" + dayOfMonth : "" + dayOfMonth;
                                binding.expTimeEt.setText(year + "-" + strMonth + "-" + strDay + " 23:55:00");
                            }
                        },
                        now.get(Calendar.YEAR), // Initial year selection
                        now.get(Calendar.MONTH), // Initial month selection
                        now.get(Calendar.DAY_OF_MONTH) // Inital day selection
                );
                dpd.setMinDate(now);
                dpd.setThemeDark(true);
                dpd.show(getSupportFragmentManager(), "Datepickerdialog");
            }
        });

        binding.createPollBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPollInServer();
            }
        });
    }


    public void createPollInServer() {
        String question = binding.questionEt.getText().toString();

        JSONArray jsonArray = new JSONArray();
        final int childCount = binding.optionsLl.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View v = binding.optionsLl.getChildAt(i);
            EditText et = v.findViewById(R.id.option_et);
            String s = et.getText().toString();
            jsonArray.put(s);
        }

        int position = binding.groupSpinner.getSelectedItemPosition();

        int i = 0;
        String groupId = "";
        for (String s : allOptions.keySet()) {
            if (i == position) {
                groupId = s;
                break;
            }
            i++;
        }

        boolean isInputValid = isInputValid(question, jsonArray, binding.expTimeEt.getText().toString());

        if(isInputValid){
            viewModel.createPoll(groupId, question, jsonArray.toString(),
                    binding.expTimeEt.getText().toString())
                    .observe(this, new Observer<Boolean>() {
                        @Override
                        public void onChanged(Boolean aBoolean) {
                            if (aBoolean == null) return;

                            if (aBoolean) {
                                finish();
                            } else {
                                Toast.makeText(CreatePollActivity.this, "Error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }else{
            Toast.makeText(this, "Please fill in the form!", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean isInputValid(String question, JSONArray jsonArray, String expirationTime) {
        boolean isInputValid = true;

        if (question.isEmpty()) {
            isInputValid = false;
        }

        if (expirationTime.isEmpty()){
            isInputValid = false;
        }

        try {
            if (jsonArray.getString(0).isEmpty() || jsonArray.getString(1).isEmpty()) {
                isInputValid = false;
            }
        } catch (Exception e) {

        }
        return isInputValid;
    }
}
