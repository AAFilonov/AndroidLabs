package com.example.lr_9.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.lr_9.R;
import com.example.lr_9.utils.ListAdapter;

import java.util.ArrayList;

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
        TextView textView = (TextView) layout.getViewById(R.id.textName);
        textView.setText("Fragment #" + mPage);
        return view;
    }


}