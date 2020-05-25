package com.androiddreamer.unipoll.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.androiddreamer.unipoll.R;
import com.androiddreamer.unipoll.databinding.FragmentCompletedPollListBinding;
import com.androiddreamer.unipoll.util.UDHelper;
import com.androiddreamer.unipoll.view.activity.CompletePollDetailActivity;
import com.androiddreamer.unipoll.view.activity.PollDetail;
import com.androiddreamer.unipoll.view.adapter.PollListAdapter;
import com.androiddreamer.unipoll.viewModel.PollsViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompletedPollListFragment extends Fragment implements PollListAdapter.OnItemClickedListener {
    FragmentCompletedPollListBinding binding;
    PollsViewModel viewModel;

    public CompletedPollListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_completed_poll_list, container, false);
        viewModel = ViewModelProviders.of(this).get(PollsViewModel.class);
        return binding.getRoot();
    }


    @Override
    public void onResume() {
        super.onResume();
        UDHelper udHelper = new UDHelper(getActivity());
        viewModel.callGetCompletedPolls(udHelper.getString(UDHelper.KEY_USER_ID))
                .observe(this, new Observer<List<JSONObject>>() {
                    @Override
                    public void onChanged(List<JSONObject> jsonObjects) {
                        if (jsonObjects == null) return;
                        binding.pollsRv.setLayoutManager(new LinearLayoutManager(getActivity()));
                        binding.pollsRv.setAdapter(new PollListAdapter(jsonObjects, getActivity(), CompletedPollListFragment.this));
                    }
                });
    }

    @Override
    public void onItemClicked(JSONObject item) {
        try {
            Intent intent = new Intent(getActivity(), CompletePollDetailActivity.class);
            intent.putExtra("id", item.getInt("id"));
            getActivity().startActivity(intent);
        } catch (JSONException e) {
            Toast.makeText(getActivity(), "Cannot open poll for some unexpected reason", Toast.LENGTH_SHORT).show();
        }
    }
}
