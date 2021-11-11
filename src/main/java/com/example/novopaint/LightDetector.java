package com.example.novopaint;

import android.content.ContentResolver;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.provider.Settings;
import android.view.Window;
import android.view.WindowManager;

public class LightDetector implements SensorEventListener {

    private OnLightListener mListener;

    private ContentResolver cResolver;
    private Window window;
    public void setOnLightListener(OnLightListener listener){
        this.mListener = listener;
    }

    public interface OnLightListener {
        public void onLight(int luz);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(mListener != null){
            int brightness = (int) sensorEvent.values[0];
            mListener.onLight(brightness);
            /*if(lx > 600){

            }*/
        }
    }



    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        //TODO
    }
}
