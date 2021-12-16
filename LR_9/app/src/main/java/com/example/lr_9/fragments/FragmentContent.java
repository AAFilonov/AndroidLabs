package com.example.lr_9.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.lr_9.ItemFormActivity;
import com.example.lr_9.ListData;
import com.example.lr_9.db.StaticDatabase;
import com.example.lr_9.db.model.Item;
import com.example.lr_9.utils.ExpandableItemListAdapter;
import com.example.lr_9.R;

public class FragmentContent extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";
    public static final int ITEM_DEL = 101;
    public static final int ITEM_EDIT = 102;

    private int mPage;
    private int SelectedItemId;
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
        ExpandableItemListAdapter adapter = new ExpandableItemListAdapter(listData.getGroups(), getContext());
        registerForContextMenu(listView);

        listView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        ExpandableListView lv = (ExpandableListView) v;
        ExpandableListView.ExpandableListContextMenuInfo acmi = (ExpandableListView.ExpandableListContextMenuInfo) menuInfo;
        View obj = (View) acmi.targetView;

        if (obj instanceof LinearLayout) {
            String textId = (String) ((TextView) obj.findViewById(R.id.textId)).getText();
            SelectedItemId = Integer.parseInt(textId);
            menu.setHeaderTitle("Элемент "+SelectedItemId);

            menu.add(Menu.NONE, ITEM_DEL, Menu.NONE, "Удалить");
            menu.add(Menu.NONE, ITEM_EDIT, Menu.NONE, "Редактировать");
        }

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        CharSequence message;
        switch (item.getItemId()) {
            case ITEM_DEL:
                message = "Выбран пункт удалить элемент' "+SelectedItemId;
                deleteItem(SelectedItemId);
                break;
            case ITEM_EDIT:
                message = "Выбран пункт редактировать элемент "+SelectedItemId;
                updateItem(SelectedItemId);
                break;
            default:
                return super.onContextItemSelected(item);
        }
        Toast toast = Toast.makeText(getContext(), message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        return true;
    }

    private void deleteItem(int selectedItemId) {
        StaticDatabase.getInstance().deleteItem(selectedItemId);
        getActivity().recreate();
    }

    private void updateItem(int selectedItemId) {
        Intent groupFormActivity = new Intent(getActivity(), ItemFormActivity.class);

        groupFormActivity.putExtra(Item.class.getSimpleName(), StaticDatabase.getInstance().getItem(selectedItemId));
        startActivity(groupFormActivity);


    }
}