package com.example.alarmclock;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class AlarmStopper extends Activity implements SensorEventListener {
    MediaPlayer mediaPlayer;
    TextView textView3;
    SensorManager sensorManager;
    boolean running = false;
    Vibrator v;
    int count = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sensorManager = (SensorManager)  getSystemService(Context.SENSOR_SERVICE);
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.moozik);
        mediaPlayer.start();
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        //v.vibrate(1000);
        running = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if(countSensor!=null)
        {
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
            //sensorManager.getR
        }else{
            Toast.makeText(this, "Sensor not found", Toast.LENGTH_LONG).show();
        }

    }
      @Override
   protected void onPause() {
       super.onPause();
       running = true;
       textView3.setText("Not showing properly");
       sensorManager.unregisterListener(this);
       v.cancel();
   }
  /*  @Override
    protected void onResume() {
        super.onResume();
        running = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if(countSensor!=null)
        {
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
            //sensorManager.getR
        }else{
            Toast.makeText(this, "Sensor not found", Toast.LENGTH_LONG).show();
        }
    }*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "ASHWINNNNNNNN", Toast.LENGTH_SHORT).show();
        running = false;
        mediaPlayer.stop();
        mediaPlayer.release();
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        long[] pattern = {0, 100, 1000, 300, 200, 100, 500, 200, 100};
//        v.vibrate(pattern, 0);
        if(running)
        {
            Toast.makeText(getApplicationContext(), "ASHUUUioiUUUU", Toast.LENGTH_SHORT).show();

            //textView3.setText(String.valueOf(event.values[0]+"\n and "+event.values[1]+"\n and "+event.values[2]));

            if(((Math.abs(event.values[0])>7)||(event.values[1]+event.values[2])>10))
                count++;
            if(count>=60) {
                    mediaPlayer.stop();
                   mediaPlayer.release();
                 v.cancel();
                Toast.makeText(getApplicationContext(), "Closing", Toast.LENGTH_LONG).show();
            }

        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
