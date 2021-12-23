package com.example.lr_9.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.lr_9.R;
import com.example.lr_9.db.StaticDatabase;
import com.example.lr_9.db.model.Group;
import com.example.lr_9.db.model.Item;
import com.example.lr_9.db.model.Subgroup;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ItemFormActivity extends AppCompatActivity {

    Item current;
    Boolean isUpdate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_form);
        Bundle args = getIntent().getExtras();
        if (args != null)
            setUpdatableItem(args);
        else
            current = new Item();


        findViewById(R.id.buttonItemCancel).setOnClickListener(this::handleCancel);
        findViewById(R.id.buttonItemReady).setOnClickListener(this::handleSave);

        Spinner spinnerGroup = findViewById(R.id.spinnerGroup);
        ArrayList<Subgroup> groups = StaticDatabase.getInstance().getSubgroups();
        List<String> groupsTitles = groups.stream().map(group -> group.getName()).collect(Collectors.toList());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, groupsTitles);
        spinnerGroup.setAdapter(adapter);
    }

    private void setUpdatableItem(Bundle args) {
        current = (Item) args.getSerializable(Item.class.getSimpleName());
        ((EditText) findViewById(R.id.editTextItemName)).setText(current.getName());
        ((EditText) findViewById(R.id.editTextItemDescription)).setText(current.getDescription());
        ((EditText) findViewById(R.id.editTextItemVersion)).setText(current.getVersion());
        ((EditText) findViewById(R.id.editTextCost)).setText(current.getCost().toString());
        ((Spinner) findViewById(R.id.spinnerGroup)).setSelection(current.getSubgroup_id() - 1);
        isUpdate = true;
    }


    private void handleSave(View view) {


        current.setName(((EditText) findViewById(R.id.editTextItemName)).getText().toString());
        current.setDescription(((EditText) findViewById(R.id.editTextItemDescription)).getText().toString());
        current.setVersion(((EditText) findViewById(R.id.editTextItemVersion)).getText().toString());
        current.setDevelopmentDate(((EditText) findViewById(R.id.editTextItemDevDate)).getText().toString());
        current.setCost(Integer.valueOf(
                ((EditText) findViewById(R.id.editTextCost)).getText().toString()));
        Spinner spinnerGroup = findViewById(R.id.spinnerGroup);
        int groupId = spinnerGroup.getSelectedItemPosition() + 1;
        current.setSubgroup_id(groupId);
        if (isUpdate)
            StaticDatabase.getInstance().updateItem(current);
        else
            StaticDatabase.getInstance().insertItem(current);


        handleCancel(null);
    }

    private void handleCancel(View view) {
        Intent groupFormActivity = new Intent(this, MainActivity.class);
        startActivity(groupFormActivity);
    }
}