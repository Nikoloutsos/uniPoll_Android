package com.androiddreamer.unipoll.view.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androiddreamer.unipoll.R;
import com.androiddreamer.unipoll.databinding.FragmentPollListBinding;
import com.androiddreamer.unipoll.util.JavaUtil;
import com.androiddreamer.unipoll.util.SwiftyJSONObject;
import com.androiddreamer.unipoll.view.adapter.PollListAdapter;
import com.androiddreamer.unipoll.viewModel.ActivePollsFragmentViewModel;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONObject;

import java.util.ArrayList;


public class ActivePollListFragment extends Fragment {

    FragmentPollListBinding binding;
    ActivePollsFragmentViewModel viewModel;

    public ActivePollListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_poll_list, container, false);
        viewModel = ViewModelProviders.of(this).get(ActivePollsFragmentViewModel.class);

        viewModel.callGetActivePolls().observe(this, new Observer<SwiftyJSONObject>() {
            @Override
            public void onChanged(SwiftyJSONObject swiftyJSONObject) {
                if (swiftyJSONObject == null) return;
                updateUI(swiftyJSONObject);
            }
        });

//        binding.pollsRv.setLayoutManager(new LinearLayoutManager(getActivity()));
//        PollListAdapter adapter = new PollListAdapter(JavaUtil.getMockPollList(), getActivity());
//        binding.pollsRv.setAdapter(adapter);
        return binding.getRoot();
    }

    private void updateUI(SwiftyJSONObject swiftyJSONObject) {
        
    }


    private void loadPieData() {

//        PieChart pieChart = binding.pieChart;
//        pieChart.getDescription().setEnabled(false);
//        pieChart.setExtraOffsets(5,10,5, 5);
//        pieChart.setDragDecelerationFrictionCoef(0.5f);
//
//        pieChart.setEntryLabelColor(Color.WHITE);
//
//        pieChart.setTouchEnabled(false);
//
//
//        pieChart.setUsePercentValues(true);
//        pieChart.setDrawHoleEnabled(true);
//        pieChart.setHoleColor(0x333333);
//        pieChart.setTransparentCircleRadius(60f);
//        pieChart.setTransparentCircleColor(0x333333);
//
//        ArrayList<PieEntry> data = new ArrayList<>();
//        data.add(new PieEntry(22f, "A"));
//        data.add(new PieEntry(44f, "B"));
//        data.add(new PieEntry(11F, "C"));
//
//        pieChart.getLegend().setTextColor(Color.WHITE);
//        pieChart.getLegend().setDirection(Legend.LegendDirection.RIGHT_TO_LEFT);
//        pieChart.getLegend().setTextSize(40f);
//
//        PieDataSet set = new PieDataSet(data, "Poll_data_set_label");
//        set.setSliceSpace(8f);
//        set.setSelectionShift(12f);
//        set.setColors(ColorTemplate.JOYFUL_COLORS);
//        PieData pieData = new PieData(set);
//        pieChart.setData(pieData);
//        pieChart.spin(800, 0, -360f, Easing.EaseInOutQuad);
    }
}
