package com.example.lr6;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

@RequiresApi(api = Build.VERSION_CODES.R)
public class SecondActivity extends AppCompatActivity {
    private Vibrator vibrator;
    private MorzeEncoder morzeEncoder = new MorzeEncoder();


    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);


        Button buttonToSecondActivity = findViewById(R.id.buttonToMainActivity);
        buttonToSecondActivity.setOnClickListener(view -> {
            Intent secondActivityIntent = new Intent(this, MainActivity.class);
            startActivity(secondActivityIntent);
        });
        Button buttonRunMorze = findViewById(R.id.buttonRunMorze2);
        buttonRunMorze.setOnClickListener(view -> handleRunMorze());

        Button buttonEncode = findViewById(R.id.buttonEncode);
        buttonEncode.setOnClickListener(view -> handleEncode());


    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    private void handleRunMorze() {
        EditText messageTextBox = findViewById(R.id.editTextEncodedMessage);
        String message = messageTextBox.getText().toString();
        if (!message.chars().allMatch(i -> (char) i == '*' || (char) i == '-')) {
            Toast.makeText(this, "Wrong input string", Toast.LENGTH_SHORT).show();
            return;
        }
        if (vibrator.hasVibrator()) {
            message.chars().mapToObj(i -> new Character((char) i)).forEachOrdered(s -> {
                        System.out.println(s);
                        this.RunMorzeCode(s);
                    }
            );
        } else {
            Toast.makeText(this, "No vibrator available on device ", Toast.LENGTH_SHORT).show();
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.R)
    private void handleEncode() {
        EditText editTextMessage = findViewById(R.id.editTextMessage);
        String message = editTextMessage.getText().toString();
        try {
            String encodedMessage = message.chars().mapToObj(i -> morzeEncoder.encode(new Character((char) i))).reduce((s, s2) -> s + s2).orElse("Error");
            EditText editTextEncodedMessage = findViewById(R.id.editTextEncodedMessage);
            editTextEncodedMessage.setText(encodedMessage);
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }


    public void RunMorzeCode(Character value) {
        if (value == '*') {
            vibrator.vibrate(20);
        } else if (value == '-') {
            vibrator.vibrate(40);
        }
    }

}