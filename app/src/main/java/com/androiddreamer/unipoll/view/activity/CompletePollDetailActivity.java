package com.androiddreamer.unipoll.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.androiddreamer.unipoll.Application;
import com.androiddreamer.unipoll.R;
import com.androiddreamer.unipoll.databinding.ActivityCompletePollDetailBinding;
import com.androiddreamer.unipoll.util.UDHelper;
import com.androiddreamer.unipoll.viewModel.PollDetailViewModel;
import com.androiddreamer.unipoll.viewModel.PollsViewModel;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CompletePollDetailActivity extends AppCompatActivity {
    ActivityCompletePollDetailBinding binding;
    PollDetailViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_complete_poll_detail);
        viewModel = ViewModelProviders.of(this).get(PollDetailViewModel.class);

        UDHelper udHelper = new UDHelper(getApplicationContext());
        int pollId = getIntent().getExtras().getInt("id");
        viewModel.getPollDetails(udHelper.getString(UDHelper.KEY_USER_ID), pollId)
                .observe(this, new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        if(s == null) return;
                        try{
                            JSONObject webServerResponseJson = new JSONObject(s);
                            JSONArray pollStats = webServerResponseJson.getJSONArray("poll_stats");
                            renderBarPlot(pollStats);
                        }catch (Exception e){

                        }
                    }
                });
    }

    private void renderBarPlot(JSONArray json) throws JSONException {
        PieChart pieChart = binding.pieChart;
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,10,5, 5);
        pieChart.setDragDecelerationFrictionCoef(0.5f);
        pieChart.setTouchEnabled(false);

        pieChart.setUsePercentValues(true);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(0x333333);
        pieChart.setTransparentCircleRadius(60f);
        pieChart.setTransparentCircleColor(0x333333);
        pieChart.setDrawSliceText(false);

        ArrayList<PieEntry> data = new ArrayList<>();
        for (int i = 0; i < json.length(); i++) {
            int totalVotesForThisOption = json.getJSONObject(i).getInt("votes");
            int optionId = json.getJSONObject(i).getInt("option_id");
            data.add(new PieEntry(totalVotesForThisOption, ""));
        }


        PieDataSet set = new PieDataSet(data, "");
        set.setSliceSpace(8f);
        set.setSelectionShift(12f);
        set.setColors(ColorTemplate.JOYFUL_COLORS);
        PieData pieData = new PieData(set);
        pieChart.setData(pieData);
        pieChart.getLegend().setEnabled(false);

        for (int i = 0; i < json.length(); i++) {
            String optionString = json.getJSONObject(i).getString("option");
            View v = getLayoutInflater().inflate(R.layout.layout_option_statistics, null);

            TextView optionTV = v.findViewById(R.id.option_tv);
            View optionColor = v.findViewById(R.id.option_color);

            optionTV.setText(optionString);
            optionColor.setBackgroundColor(ColorTemplate.JOYFUL_COLORS[i % 5]);
            binding.optionsLl.addView(v);
        }
    }
}
