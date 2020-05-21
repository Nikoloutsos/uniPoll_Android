package com.androiddreamer.unipoll.view.activity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.androiddreamer.unipoll.R;
import com.androiddreamer.unipoll.databinding.ActivityPollDetailBinding;
import com.androiddreamer.unipoll.util.UDHelper;
import com.androiddreamer.unipoll.viewModel.PollDetailViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PollDetail extends AppCompatActivity {

    ActivityPollDetailBinding binding;
    PollDetailViewModel viewModel;
    int pollId;
    RadioGroup optionsRadioGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_poll_detail);
        viewModel = ViewModelProviders.of(this).get(PollDetailViewModel.class);

        pollId = getIntent().getExtras().getInt("id");
        binding.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UDHelper udHelper = new UDHelper(getApplicationContext());
                viewModel.vote(udHelper.getString(UDHelper.KEY_USER_ID), pollId, optionsRadioGroup.getCheckedRadioButtonId())
                        .observe(PollDetail.this, new Observer<Boolean>() {
                            @Override
                            public void onChanged(Boolean aBoolean) {
                                if (aBoolean == null) return;
                                if (aBoolean) {
                                    Toast.makeText(PollDetail.this, "You have successfully voted", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(PollDetail.this, "For some unexpected reason your vote did not count", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();

        UDHelper udHelper = new UDHelper(getApplicationContext());
        viewModel.getPollDetails(udHelper.getString(UDHelper.KEY_USER_ID), pollId).observe(this,
                new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        if (s == null) return;
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            String question = jsonObject.getString("question");
                            binding.textView5.setText(question);
                            renderVoteOptions(jsonObject);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

    }


    private void renderVoteOptions(JSONObject jsonObject) {
        try {
            JSONArray optionsArray = jsonObject.getJSONArray("poll_stats");
            List<String> optionStringList = new ArrayList<>();

            for (int i = 0; i < optionsArray.length(); i++) {
                optionStringList.add(optionsArray.getJSONObject(i).getString("option"));
            }

            optionsRadioGroup = new RadioGroup(this);
            for (int i = 0; i < optionStringList.size(); i++) {
                RadioButton radioButton = new RadioButton(this);
                Typeface typeface = ResourcesCompat.getFont(this, R.font.roboto_regular);
                radioButton.setTextColor(Color.WHITE);
                int textColor = Color.parseColor("#ffffff");
                radioButton.setButtonTintList(ColorStateList.valueOf(textColor));
                radioButton.setTypeface(typeface);
                radioButton.setText(optionStringList.get(i));
                optionsRadioGroup.addView(radioButton);
            }
            binding.optionsLl.addView(optionsRadioGroup);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
