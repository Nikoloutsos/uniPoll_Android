package com.androiddreamer.unipoll.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.graphics.Color;
import android.os.Bundle;

import com.androiddreamer.unipoll.R;
import com.androiddreamer.unipoll.databinding.ActivityCompletePollDetailBinding;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CompletePollDetailActivity extends AppCompatActivity {
    ActivityCompletePollDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_complete_poll_detail);

        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, 30f));
        entries.add(new BarEntry(1f, 80f));
        entries.add(new BarEntry(2f, 60f));
        entries.add(new BarEntry(3f, 50f));
        Collections.reverse(entries);

        BarDataSet set = new BarDataSet(entries, "");
        BarData data = new BarData(set);
        data.setBarWidth(0.9f);

        Description description = new Description();
        description.setText("");

        binding.barChart.getAxisRight().setDrawLabels(false);
        binding.barChart.getXAxis().setDrawLabels(false);
        binding.barChart.setContentDescription("statistics");
        binding.barChart.setDescription(description);
        binding.barChart.setBackgroundColor(Color.WHITE);
        binding.barChart.setData(data);
        binding.barChart.setFitBars(true);
        binding.barChart.invalidate();
    }
}
