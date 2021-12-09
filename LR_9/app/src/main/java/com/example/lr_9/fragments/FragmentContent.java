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
        ListAdapter adapter = getListAdapter();
        listView.setAdapter(adapter);

        return view;
    }

    @NonNull
    private ListAdapter getListAdapter() {
        ArrayList<ArrayList<String>> groups = new ArrayList<ArrayList<String>>();
        ArrayList<String> children1 = new ArrayList<String>();
        ArrayList<String> children2 = new ArrayList<String>();
        ArrayList<String> children3 = new ArrayList<String>();
        children1.add("Child_1");
        children1.add("Child_2");
        groups.add(children1);
        children2.add("Child_1");
        children2.add("Child_2");
        children2.add("Child_3");
        groups.add(children2);
        children3.add("Child_1");
        children3.add("Child_2");
        children3.add("Child_3");
        groups.add(children3);
        //Создаем адаптер и передаем context и список с данными

        List<String> groupTitles = listData.getGroups().stream().map(group -> group.getName())
                .collect(Collectors.toList());

        ListAdapter adapter = new ListAdapter(listData.getGroups(), getContext());
        return adapter;
    }


}