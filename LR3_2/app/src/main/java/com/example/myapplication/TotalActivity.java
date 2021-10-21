package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.myapplication.model.Order;

public class TotalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total);

        Bundle args = getIntent().getExtras();
        if (args != null)
            printOrderInfo((Order) args.getSerializable(Order.class.getSimpleName()));
    }

    private void printOrderInfo(Order order) {
        StringBuilder builder = new StringBuilder();
        builder.append("Тип заказа: ").append(order.getOrderTypeName()).append("\n");
        builder.append("Адрес: ").append(order.getHallName()).append("\n");;
        builder.append("Имя: ").append(order.getFirstName()).append("\n");;
        builder.append("Фамилия: ").append(order.getSecondName()).append("\n");;
        builder.append("Контактный телефон: ").append(order.getPhone()).append("\n");;


        TextView textViewTotal = findViewById(R.id.textViewTotal);
        textViewTotal.setText("Заявка на заказ торжества:\n" + builder);
    }
}