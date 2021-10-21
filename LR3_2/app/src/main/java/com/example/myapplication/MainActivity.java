package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.myapplication.model.Order;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((Button) findViewById(R.id.buttonReady)).setOnClickListener(this::handleReady);
    }

    private void handleReady(View view) {

        try {
            Order order = createOrder(view);
            Intent secondActivityIntent = new Intent(this, TotalActivity.class);
            secondActivityIntent.putExtra(Order.class.getSimpleName(), order);
            startActivity(secondActivityIntent);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private Order createOrder(View view) {
        Order order = new Order();

        fillOrderType(order);
        fillHallAddr(order);
        fillFirstName(order);
        fillSecondName(order);
        fillPhone(order);
        return order;
    }

    private void fillFirstName(Order order) {
        EditText editText = findViewById(R.id.editTextFirstName);
        order.setFirstName(editText.getText().toString());
    }

    private void fillSecondName(Order order) {
        EditText editText = findViewById(R.id.editTextSecondName);
        order.setSecondName(editText.getText().toString());
    }

    private void fillPhone(Order order) {
        EditText editText = findViewById(R.id.editTextPhone);
        order.setPhone(editText.getText().toString());
    }

    private void fillOrderType(Order order) {
        RadioButton radioButtonBuffer = findViewById(R.id.radioButton_Type1);
        if (radioButtonBuffer.isChecked())
            order.setOrderTypeName(radioButtonBuffer.getText().toString());
        radioButtonBuffer = findViewById(R.id.radioButton_Type2);
        if (radioButtonBuffer.isChecked())
            order.setOrderTypeName(radioButtonBuffer.getText().toString());
    }

    private void fillHallAddr(Order order) {

        RadioButton radioButtonBuffer = findViewById(R.id.radioButton_Adr1);
        if (radioButtonBuffer.isChecked())
            order.setHallName(radioButtonBuffer.getText().toString());
        radioButtonBuffer = findViewById(R.id.radioButton_Adr2);
        if (radioButtonBuffer.isChecked())
            order.setHallName(radioButtonBuffer.getText().toString());
        radioButtonBuffer = findViewById(R.id.radioButton_Adr3);
        if (radioButtonBuffer.isChecked())
            order.setHallName(radioButtonBuffer.getText().toString());


    }

}