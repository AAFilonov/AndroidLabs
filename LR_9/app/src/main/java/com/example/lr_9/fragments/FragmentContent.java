package com.example.lr_9.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.lr_9.ListData;
import com.example.lr_9.utils.ListAdapter;
import com.example.lr_9.R;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FragmentContent extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;
    private ListData listData = new ListData();

    public static FragmentContent newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        FragmentContent fragment = new FragmentContent();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mPage = getArguments().getInt(ARG_PAGE);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);

        ConstraintLayout layout = (ConstraintLayout) view;
        ExpandableListView listView = (ExpandableListView) layout.getChildAt(0);
        ListAdapter adapter =  new ListAdapter(listData.getGroups(), getContext());
        listView.setAdapter(adapter);

        return view;
    }



}