package com.example.lr6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Switch switchFlashlight = findViewById(R.id.switchFlashlight);
        switchFlashlight.setOnCheckedChangeListener((compoundButton, b) -> {
            handleSwithcFlashlight(switchFlashlight);
        });

        Button buttonToSecondActivity = findViewById(R.id.buttonToSecondActivity);
        buttonToSecondActivity.setOnClickListener(view -> {
            Intent secondActivityIntent = new Intent(this, SecondActivity.class);
            startActivity(secondActivityIntent);
        });



    }

    private void handleSwithcFlashlight(Switch switchFlashlight) {
        switchFlashlight.setChecked(!switchFlashlight.isChecked());

        boolean isFlashlightAvailable = this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
        if(isFlashlightAvailable){
            setFlashlightState(switchFlashlight.isChecked());
        }
        else {
            Toast.makeText(this, "No flashlight available on device", Toast.LENGTH_SHORT).show();
        }

        changeImage(switchFlashlight);
    }


    private void changeImage(Switch switchFlashlight) {
        ImageView imageFlahlight = findViewById(R.id.imageView);
        if (switchFlashlight.isChecked()) {
            imageFlahlight.setImageResource(R.drawable.lampon);
        } else {
            imageFlahlight.setImageResource(R.drawable.lamp_off);
        }
    }

    private  void  setFlashlightState(boolean state ){
        Camera cam = Camera.open();
        if(state){

            Camera.Parameters p = cam.getParameters();
            p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            cam.setParameters(p);
            cam.startPreview();
        }
        else {
            cam.stopPreview();
            cam.release();
        }
    }

}