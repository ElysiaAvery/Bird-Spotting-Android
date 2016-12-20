package com.example.guest.aviary.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.guest.aviary.R;

import com.example.guest.aviary.models.Bird;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class BirdListFragment extends Fragment {
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    public ArrayList<Bird> mBirds = new ArrayList<>();


    public BirdListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bird_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

}
