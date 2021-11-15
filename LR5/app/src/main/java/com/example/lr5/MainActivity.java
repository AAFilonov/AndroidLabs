package com.example.lr5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String[] spinnerItems = {"CMA Small Systems", "Idea promotion", "IT-Pro", "R-Style"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initSpinner();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.style1:
                this.setTheme(R.style.theme1);
                this.recreate();
                return true;
            case R.id.style2:
                this.setTheme(R.style.theme2);
                this.recreate();
                return true;
            case R.id.visiblity:
                TextView textView = findViewById(R.id.textViewSelected);
                textView.setVisibility(textView.getVisibility() == View.VISIBLE ? View.INVISIBLE : View.VISIBLE);

                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setTitle("Видимость названия изменена!");
                dialog.setContentView(R.layout.dialog);
                dialog.show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initSpinner() {
        Spinner spinner = findViewById(R.id.spinner);


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerItems);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                TextView textViewSelected = findViewById(R.id.textViewSelected);
                String item = (String) adapterView.getItemAtPosition(position);
                textViewSelected.setText(item);


                ImageView imageView = findViewById(R.id.imageView);
                switch (position) {
                    case 0:
                        imageView.setImageResource(R.drawable.cma);
                        break;
                    case 1:
                        imageView.setImageResource(R.drawable.idea);
                        break;
                    case 2:
                        imageView.setImageResource(R.drawable.itpro);
                        break;
                    case 3:
                        imageView.setImageResource(R.drawable.r_style);
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}