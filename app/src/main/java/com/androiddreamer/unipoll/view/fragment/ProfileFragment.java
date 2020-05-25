package com.androiddreamer.unipoll.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.androiddreamer.unipoll.R;
import com.androiddreamer.unipoll.databinding.FragmentProfileBinding;
import com.androiddreamer.unipoll.util.UDHelper;


public class ProfileFragment extends Fragment {

    FragmentProfileBinding binding;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);
        UDHelper udHelper = new UDHelper(getActivity());
        String userId = udHelper.getString(UDHelper.KEY_USER_ID);
        binding.nicknameValueTv.setText(userId);

        return binding.getRoot();
    }
}
