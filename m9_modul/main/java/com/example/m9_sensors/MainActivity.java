package com.example.m9_sensors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;

import com.example.m9_sensors.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private static final String TAG = "debug MainAct";
    private static final float VALUE_DRIFT = 0.05f;
    private ActivityMainBinding bindingMain;
    private SensorManager mSensorManager;
    private Sensor mSensorAccelerometer;
    private Sensor mSensorMagnetometer;

    private float[] accelerometerReading = new float[3];
    private float[] magnetometerReading = new float[3];

    private float[] rotationMatrix = new float[9];
    private float[] orientationAngles = new float[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindingMain = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(bindingMain.getRoot());
        
        this.mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        this.mSensorAccelerometer = this.mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        this.mSensorMagnetometer = this.mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        this.showAllSensor();
    }

    private void showAllSensor() {                                                                  Log.d(TAG, "showAllSensor: masuk");
        List<Sensor> sensorList = this.mSensorManager.getSensorList(Sensor.TYPE_ALL);

        for (Sensor curSensor : sensorList) {
            // liat isiny
//            Log.d("sensor: ",curSensor.getName());
            Log.d(TAG, "showAllSensor: curSensor: " + curSensor.getName());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mSensorAccelerometer != null) {
            mSensorManager.registerListener(this,
                                            mSensorAccelerometer,
                                            SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (mSensorMagnetometer != null) {
            mSensorManager.registerListener(this,
                                            mSensorMagnetometer,
                                            SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        int sensorType = sensorEvent.sensor.getType();

        switch (sensorType) {
            case Sensor.TYPE_ACCELEROMETER:
                this.accelerometerReading = sensorEvent.values.clone();
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                this.magnetometerReading = sensorEvent.values.clone();
                break;
        }

        float[] rotationMatrixAdjusted = new float[9];

        this.mSensorManager.getRotationMatrix(rotationMatrix, null, accelerometerReading, magnetometerReading);
        this.mSensorManager.getOrientation(rotationMatrix, orientationAngles);
        /*
        sebelum diset jd:
            private float[] accelerometerReading = new float[3];
            private float[] magnetometerReading = new float[3];

            private float[] rotationMatrix = new float[9];
            private float[] orientationAngles = new float[3];
        021-11-24 19:51:14.502 27383-27383/com.example.m9_sensors E/SensorManager: Exception dispatching input event.
2021-11-24 19:51:14.502 27383-27383/com.example.m9_sensors A/mple.m9_sensor: java_vm_ext.cc:578] JNI DETECTED ERROR IN APPLICATION: JNI CallObjectMethodV called with pending exception java.lang.NullPointerException: Attempt to read from null array
    java_vm_ext.cc:578]   at boolean android.hardware.SensorManager.getRotationMatrix(float[], float[], float[], float[]) (SensorManager.java:1239)
    java_vm_ext.cc:578]   at void com.example.m9_sensors.MainActivity.onSensorChanged(android.hardware.SensorEvent) (MainActivity.java:94)*/

        float azimuth = orientationAngles[0];
        float pitch = orientationAngles[1];
        float roll = orientationAngles[2];

        if(Math.abs(azimuth) < VALUE_DRIFT){
            azimuth = 0;
        }
        if(Math.abs(pitch) < VALUE_DRIFT){
            pitch = 0;
        }
        if(Math.abs(roll) < VALUE_DRIFT){
            roll = 0;
        }

        bindingMain.azimuthTv.setText("Azimuth: " + azimuth);
        bindingMain.pitchTv.setText("Pitch: " + pitch);
        bindingMain.rollTv.setText("Roll: " + roll);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        //
    }
}