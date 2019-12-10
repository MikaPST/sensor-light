package com.example.sensor;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;


public class MainActivity extends Activity implements SensorEventListener
{
    SensorManager sm;
    Sensor sensor;
    TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sm.getDefaultSensor(Sensor.TYPE_LIGHT);
        tvData = findViewById(R.id.tvData);
    }
    @Override
    protected void onResume() {
        super.onResume();
        sm.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    protected void onPause() {
        super.onPause();
        sm.unregisterListener(this);
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        float lightValue;
        if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            lightValue = event.values[0];
            tvData.setText(String.valueOf(lightValue));
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }
}