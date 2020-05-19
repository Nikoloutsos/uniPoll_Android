package com.androiddreamer.unipoll.view.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androiddreamer.unipoll.R;
import com.androiddreamer.unipoll.databinding.FragmentProfileBinding;
import com.androiddreamer.unipoll.util.SwiftyJSONObject;
import com.androiddreamer.unipoll.util.UDHelper;
import com.androiddreamer.unipoll.viewModel.ProfileFragmentViewModel;

import org.json.JSONObject;


public class ProfileFragment extends Fragment {

    FragmentProfileBinding binding;
    ProfileFragmentViewModel viewModel;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);
        viewModel = ViewModelProviders.of(this).get(ProfileFragmentViewModel.class);

        UDHelper udHelper = new UDHelper(getActivity());
        String userId = udHelper.getString(UDHelper.KEY_USER_ID);
        binding.nicknameValueTv.setText(userId);

        return binding.getRoot();
    }
}
