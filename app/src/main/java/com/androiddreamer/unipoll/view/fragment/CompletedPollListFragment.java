package com.androiddreamer.unipoll.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androiddreamer.unipoll.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompletedPollListFragment extends Fragment {

    public CompletedPollListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_completed_poll_list, container, false);
    }
}
