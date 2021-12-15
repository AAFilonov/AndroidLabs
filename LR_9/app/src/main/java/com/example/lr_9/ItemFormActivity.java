package com.example.lr_9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lr_9.db.StaticDatabase;
import com.example.lr_9.db.model.Group;
import com.example.lr_9.db.model.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ItemFormActivity extends AppCompatActivity {

    Item current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_form);
        ((Button) findViewById(R.id.buttonItemCancel)).setOnClickListener(this::handleCancel);
        ((Button) findViewById(R.id.buttonItemReady)).setOnClickListener(this::handleSave);
        current = new Item();
        Spinner spinnerGroup = findViewById(R.id.spinnerGroup);
        ArrayList<Group> groups = StaticDatabase.getInstance().getGroupsWithoutLists();
        List<String> groupsTitles = groups.stream().map(group -> group.getName()).collect(Collectors.toList());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, groupsTitles);
        spinnerGroup.setAdapter(adapter);
    }


    private void handleSave(View view) {


        current.setName(((EditText) findViewById(R.id.editTextItemName)).getText().toString());
        current.setDescription(((EditText) findViewById(R.id.editTextItemDescription)).getText().toString());
        current.setVersion(((EditText) findViewById(R.id.editTextItemVersion)).getText().toString());
        current.setDevelopmentDate(((EditText) findViewById(R.id.editTextItemDevDate)).getText().toString());
        current.setCost(Integer.valueOf(
                ((EditText) findViewById(R.id.editTextCost)).getText().toString()));
        Spinner spinnerGroup = findViewById(R.id.spinnerGroup);
        int groupId = spinnerGroup.getSelectedItemPosition()+1;
        current.setGroup_id(groupId);
        StaticDatabase.getInstance().insertItem(current);
        Toast.makeText(this, "ПО успешно сохранено", Toast.LENGTH_LONG);
        ((Button) findViewById(R.id.buttonItemReady)).setEnabled(false);
        //handleCancel(null);
    }

    private void handleCancel(View view) {
        Intent groupFormActivity = new Intent(this, MainActivity.class);
        startActivity(groupFormActivity);
    }
}