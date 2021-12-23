package com.example.lr_9.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lr_9.R;
import com.example.lr_9.db.StaticDatabase;
import com.example.lr_9.db.model.Group;

public class GroupFormActivity extends AppCompatActivity {
    Group current;
    Boolean isUpdate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_form);

        Bundle args = getIntent().getExtras();
        if (args != null)
            setUpdatableGroup(args);
        else
            current = new Group();


        findViewById(R.id.buttonGroupCancel).setOnClickListener(this::handleCancel);
        findViewById(R.id.buttonGroupReady).setOnClickListener(this::handleSave);


    }

    private void setUpdatableGroup(Bundle args) {
        current = (Group) args.getSerializable(Group.class.getSimpleName());
        EditText editName = this.findViewById(R.id.editTextGroupName);
        editName.setText(current.getName());
        isUpdate = true;
    }

    private void handleSave(View view) {

        EditText name = findViewById(R.id.editTextGroupName);
        current.setName(name.getText().toString());
        if (isUpdate)
            StaticDatabase.getInstance().updateGroup(current);
        else
            StaticDatabase.getInstance().insertGroup(current);


        Toast.makeText(this, "Категория успешно сохранена", Toast.LENGTH_LONG).show();

        handleCancel(null);
    }

    private void handleCancel(View view) {
        Intent groupFormActivity = new Intent(this, MainActivity.class);
        startActivity(groupFormActivity);
    }
}