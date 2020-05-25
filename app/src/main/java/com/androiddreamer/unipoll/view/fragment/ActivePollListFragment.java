package com.androiddreamer.unipoll.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.androiddreamer.unipoll.R;
import com.androiddreamer.unipoll.databinding.FragmentPollListBinding;
import com.androiddreamer.unipoll.util.UDHelper;
import com.androiddreamer.unipoll.view.activity.PollDetail;
import com.androiddreamer.unipoll.view.adapter.PollListAdapter;
import com.androiddreamer.unipoll.viewModel.PollsViewModel;

import org.json.JSONException;
import org.json.JSONObject;


public class ActivePollListFragment extends Fragment implements PollListAdapter.OnItemClickedListener {

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

        UDHelper udHelper = new UDHelper(getActivity().getApplicationContext());

        viewModel.callGetActivePolls(udHelper.getString(UDHelper.KEY_USER_ID)).observe(this,
                jsonObjects -> {
                    if (jsonObjects == null) return;
                    binding.pollsRv.setLayoutManager(new LinearLayoutManager(getActivity()));
                    binding.pollsRv.setAdapter(new PollListAdapter(jsonObjects, getActivity(), ActivePollListFragment.this));
                });

        return binding.getRoot();
    }

    private void loadPieData() {

    }

    @Override
    public void onItemClicked(JSONObject item) {
        try {
            Intent intent = new Intent(getActivity(), PollDetail.class);
            intent.putExtra("id", item.getInt("id"));
            getActivity().startActivity(intent);
        } catch (JSONException e) {
            Toast.makeText(getActivity(), "Cannot open poll for some unexpected reason", Toast.LENGTH_SHORT).show();
        }
    }
}
