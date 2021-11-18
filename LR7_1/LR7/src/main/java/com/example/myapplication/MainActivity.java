package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    public boolean isMusicOn=false;
    public boolean isSoundOn=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DrawView((this)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {

            case R.id.music:
                isMusicOn=!isMusicOn;
                if(isMusicOn)
                    SoundPlayer.playOst();
                else
                    SoundPlayer.stopOst();
                item.setChecked(!item.isChecked());
                return true;
            case R.id.sound:
                isSoundOn = !isSoundOn;
                item.setChecked(!item.isChecked());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}