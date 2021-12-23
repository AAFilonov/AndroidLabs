package com.example.lr_9.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lr_9.R;
import com.example.lr_9.db.StaticDatabase;
import com.example.lr_9.db.model.Group;
import com.example.lr_9.db.model.Subgroup;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SubgroupFormActivity extends AppCompatActivity {
    Subgroup current;
    Boolean isUpdate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subgroup_form);

        Spinner spinnerGroup = findViewById(R.id.spinnerSubgroup);
        ArrayList<Group> groups = StaticDatabase.getInstance().getGroupsWithoutLists();
        List<String> groupsTitles = groups.stream().map(group -> group.getName()).collect(Collectors.toList());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, groupsTitles);
        spinnerGroup.setAdapter(adapter);




        Bundle args = getIntent().getExtras();
        if (args != null)
            setUpdatableGroup(args);
        else
            current = new Subgroup();


        findViewById(R.id.buttonSubgroupCancel).setOnClickListener(this::handleCancel);
        findViewById(R.id.buttonSubgroupReady).setOnClickListener(this::handleSave);


    }

    private void setUpdatableGroup(Bundle args) {
        current = (Subgroup) args.getSerializable(Subgroup.class.getSimpleName());
        EditText editName = this.findViewById(R.id.editTextSubgroupName);
        editName.setText(current.getName());

        Spinner spinnerGroup = findViewById(R.id.spinnerSubgroup);
        spinnerGroup.setSelection(current.getGroup_id()-1);


        isUpdate = true;
    }

    private void handleSave(View view) {

        EditText name = findViewById(R.id.editTextSubgroupName);
        current.setName(name.getText().toString());
        Spinner spinnerGroup = findViewById(R.id.spinnerSubgroup);
        int groupId = spinnerGroup.getSelectedItemPosition() + 1;
        current.setGroup_id(groupId);
        if (isUpdate)
            StaticDatabase.getInstance().updateSubgroup(current);
        else
            StaticDatabase.getInstance().insertSubgroup(current);


        Toast.makeText(this, "Подкатегория успешно сохранена", Toast.LENGTH_LONG).show();

        handleCancel(null);
    }

    private void handleCancel(View view) {
        Intent groupFormActivity = new Intent(this, MainActivity.class);
        startActivity(groupFormActivity);
    }
}