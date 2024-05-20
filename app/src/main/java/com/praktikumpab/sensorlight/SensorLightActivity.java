package com.praktikumpab.sensorlight;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SensorLightActivity extends AppCompatActivity  implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor sensor;
    private ImageView imageLamp;
    private TextView descriptionCondition;
    private boolean lampCondition = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_light);

        imageLamp = findViewById(R.id.iv_lamp);
        descriptionCondition = findViewById(R.id.tv_desc_condition);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.values[0] > 40) {
            if (lampCondition) {
                imageLamp.setImageResource(R.drawable.lamp_light);
                lampCondition = false;
                // Keterangan kondisi tempat terang
                descriptionCondition.setText("Tempat Terang");
            } else {
                return;
            }
        } else {
            lampCondition = true;
            imageLamp.setImageResource(R.drawable.lamp_dark);
            // Keterangan kondisi tempat gelap
            descriptionCondition.setText("Tempat Gelap");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do nothing or add implementation if needed
    }
}