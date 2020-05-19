package com.androiddreamer.unipoll.view.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androiddreamer.unipoll.R;
import com.androiddreamer.unipoll.databinding.FragmentPollListBinding;
import com.androiddreamer.unipoll.util.UDHelper;
import com.androiddreamer.unipoll.view.adapter.PollListAdapter;
import com.androiddreamer.unipoll.viewModel.PollsViewModel;


public class ActivePollListFragment extends Fragment {

    FragmentPollListBinding binding;
    PollsViewModel viewModel;

    public ActivePollListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_poll_list, container, false);
        viewModel = ViewModelProviders.of(this).get(PollsViewModel.class);

        return binding.getRoot();
    }


    @Override
    public void onResume() {
        super.onResume();

        UDHelper udHelper = new UDHelper(getActivity().getApplicationContext());

        viewModel.callGetActivePolls(udHelper.getString(UDHelper.KEY_USER_ID)).observe(this,
                jsonObjects -> {
                    if (jsonObjects == null) return;
                    binding.pollsRv.setLayoutManager(new LinearLayoutManager(getActivity()));
                    binding.pollsRv.setAdapter(new PollListAdapter(jsonObjects, getActivity()));
                });
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
