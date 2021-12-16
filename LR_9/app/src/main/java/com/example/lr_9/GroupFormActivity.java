package com.example.lr_9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lr_9.db.StaticDatabase;
import com.example.lr_9.db.model.Group;

public class GroupFormActivity extends AppCompatActivity {
    Group current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        current = new Group();
        setContentView(R.layout.activity_group_form);

        ((Button) findViewById(R.id.buttonGroupCancel)).setOnClickListener(this::handleCancel);
        ((Button) findViewById(R.id.buttonGroupReady)).setOnClickListener(this::handleSave);
    }

    private void handleSave(View view) {

        EditText name = findViewById(R.id.editЕextGroupName);
        current.setName(name.getText().toString());
        StaticDatabase.getInstance().insertGroup(current);
        Toast.makeText(this, "Подкатегория успешно сохранена", Toast.LENGTH_LONG).show();

        handleCancel(null);
    }

    private void handleCancel(View view) {
        Intent groupFormActivity = new Intent(this, MainActivity.class);
        startActivity(groupFormActivity);
    }
}