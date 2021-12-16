package com.example.lr_9.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.lr_9.GroupFormActivity;
import com.example.lr_9.ItemFormActivity;
import com.example.lr_9.R;

public class FragmentEdit extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;

    public static FragmentEdit newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        FragmentEdit fragment = new FragmentEdit();
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
        View view = inflater.inflate(R.layout.fragment_edit, container, false);
        ConstraintLayout layout = (ConstraintLayout) view;
        Button buttonAddGroup = (Button) layout.getViewById(R.id.buttonAddGroup);
        Button buttonAddItem = (Button) layout.getViewById(R.id.buttonAddItem);
        buttonAddGroup.setOnClickListener(view1 -> {
            Intent groupFormActivity = new Intent(getActivity(), GroupFormActivity.class);
            startActivity(groupFormActivity);
        });

        buttonAddItem.setOnClickListener(view1 -> {
            Intent groupFormActivity = new Intent(getActivity(), ItemFormActivity.class);
            startActivity(groupFormActivity);
        });
        return view;
    }


}