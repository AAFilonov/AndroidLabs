package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.db.StaticDatabase;
import com.example.myapplication.model.Order;

public class TotalActivity extends AppCompatActivity {
    private Order activeOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total);

        Bundle args = getIntent().getExtras();
        if (args != null)
            printOrderInfo((Order) args.getSerializable(Order.class.getSimpleName()));

        ((Button) findViewById(R.id.buttonCancel)).setOnClickListener(this::handleCancel);
        ((Button) findViewById(R.id.buttonList)).setOnClickListener(this::handleList);
        ((Button) findViewById(R.id.buttonSave)).setOnClickListener(this::handleSave);
    }


    private void printOrderInfo(Order order) {
        StringBuilder builder = new StringBuilder();
        builder.append("Тип заказа: ").append(order.getOrderTypeName()).append("\n");
        builder.append("Адрес: ").append(order.getHallName()).append("\n");
        ;
        builder.append("Имя: ").append(order.getFirstName()).append("\n");
        ;
        builder.append("Фамилия: ").append(order.getSecondName()).append("\n");
        ;
        builder.append("Контактный телефон: ").append(order.getPhone()).append("\n");
        ;


        TextView textViewTotal = findViewById(R.id.textViewTotal);
        textViewTotal.setText("Заявка на заказ торжества:\n" + builder);
        activeOrder = order;
    }


    private void handleSave(View view) {
        StaticDatabase.getInstance().insertOrder(this.activeOrder);
        Toast.makeText(this, "Заказ сохранен", Toast.LENGTH_SHORT).show();
    }

    private void handleList(View view) {
        Intent secondActivityIntent = new Intent(this, ListActivity.class);
        startActivity(secondActivityIntent);

    }

    private void handleCancel(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }
}