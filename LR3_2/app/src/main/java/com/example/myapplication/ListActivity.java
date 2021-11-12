package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.myapplication.db.StaticDatabase;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        fillListView();
        ((Button) findViewById(R.id.buttonBack)).setOnClickListener(this::handleBack);

    }

    private void handleBack(View view) {

        startActivity(new Intent(this, MainActivity.class));
    }

    private void fillListView() {

        ArrayList<String> orders = StaticDatabase.getInstance().getOrders();

        ListView orderList = findViewById(R.id.ListViewOrders);
        ArrayAdapter<String> adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, orders);
        orderList.setAdapter(adapter);


    }
}