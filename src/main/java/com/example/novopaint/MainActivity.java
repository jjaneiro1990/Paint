package com.example.novopaint;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity  {

    private SensorManager sensorManager;
    private SensorEventListener lightEventListener;
    private Sensor lightSensor;
    private Sensor mAccelerometer;
    private View main;
    private int maxValue;
    private ShakerDetector shakerDetector;
    static int color = 0;
    static int colorP  = R.color.black;
    private int brightness;
    private ContentResolver cResolver;
    private Window window;
    PaintCanvas paintCanvas;
    private Sensor acel;
    private LightDetector lightDetector;
    Fragment fragment;
    FragmentManager fragmentManager;
    FragmentTransaction transaction;
    FirebaseDatabase db;
    DatabaseReference myRef;
    static Pinta p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int orientation = getResources().getConfiguration().orientation;
        if(orientation == Configuration.ORIENTATION_PORTRAIT){

            //setContentView(paintCanvas);
            setContentView(R.layout.activity_main);
            Button mBotaoPalette = (Button) findViewById(R.id.buttonX);
            mBotaoPalette.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v) {
                    fragment = new PaletteFragment();
                    fragmentManager = getSupportFragmentManager();
                    transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.canvas, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            });

        }else{
            ///super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main_landscape);
        }
        main = findViewById(R.id.main_layout);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if(lightSensor == null){
            Toast.makeText(this, "This device has no light sensor",Toast.LENGTH_SHORT).show();
            finish();
        }
        maxValue = (int) lightSensor.getMaximumRange();
        lightEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                float val = sensorEvent.values[0];
                WindowManager.LayoutParams layoutpars = getWindow().getAttributes();
                int brightness;
                Toast.makeText(getApplicationContext(),""+val,Toast.LENGTH_SHORT).show();
                layoutpars.screenBrightness = (3* val+50)/ (float) 255;
                getWindow().setAttributes(layoutpars);

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
        GestureListener mGestureListener = new GestureListener();
        GestureDetector mGestureDetector = new GestureDetector(getApplicationContext(), mGestureListener);
        mGestureDetector.setIsLongpressEnabled(true);
        mGestureDetector.setOnDoubleTapListener(mGestureListener);


        paintCanvas = new PaintCanvas(getApplicationContext(), null, mGestureDetector);
        paintCanvas.setColor(colorP);
        mGestureListener.setCanvas(paintCanvas);

        mAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        cResolver = getContentResolver();
        window = getWindow();
        lightDetector = new LightDetector();
        shakerDetector = new ShakerDetector();
        shakerDetector.setOnShakeListener(new ShakerDetector.OnShakeListener() {

            @Override
            public void onShake(int count) {
                /*
                 * The fo &&wing method, "handleShakeEvent(count):" is a stub //
                 * method you would use to setup whatever you want done once the
                 * device has been shook.
                 */
                //handleShakeEvent(count);
                paintCanvas.erase();
            }
        });


        defineCor(color);
        Button b2 = (Button) findViewById(R.id.buttonX2);
        /*b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p = paintCanvas.getPs();
                addData(p);
            }
        });*/
        //db = FirebaseDatabase.getInstance("https://novopaint-default-rtdb.europe-west1.firebasedatabase.app/");
        //myRef = db.getReference("Pinta");
        //myRef.setValue(paintCanvas);




    }

    private void addData(Pinta p) {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                myRef.child("Pinta").setValue(p);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    protected void onResume() {
        // Register a listener for the sensor.
        super.onResume();
        //s.registerListener(this, luz, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(shakerDetector, mAccelerometer,	SensorManager.SENSOR_DELAY_UI);
        sensorManager.registerListener(lightEventListener, lightSensor,	SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onPause() {
        // Be sure to unregister the sensor when the activity pauses.
        sensorManager.unregisterListener(shakerDetector);
        sensorManager.unregisterListener(lightEventListener);
        super.onPause();
        //s.unregisterListener(this);
    }

    public void goToSettings(View view){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivityForResult(intent,0);
    }

    @Override
    public void onActivityResult(int req, int res, Intent d){
        if(req == 0 && req == 0){
            if(d.hasExtra("color")){
                color = d.getIntExtra("color",0);
                defineCor(color);
            }
            super.onActivityResult(req,res,d);
        }
    }

    private void defineCor(int color) {
        if(this.color!= 0){
            this.getWindow().getDecorView().setBackgroundColor(color);
            this.paintCanvas.setBackgroundColor(color);
            //this.getWindow().findViewById(R.id.fragment_canvas).setBackgroundColor(color);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        View view = this.getWindow().getDecorView();
        switch (item.getItemId()) {
            case R.id.settings:
                goToSettings(view);
                return true;
            case R.id.about:
                goToAbout(view);
                return true;
            case R.id.mapi:
                goToMap(view);
                return true;
            case R.id.main_layout:
                goToMain(view);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void goToAbout(View view){
        Intent intent = new Intent(this, AboutActivity.class);
        startActivityForResult(intent,0);
    }

    public void goToMain(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void goToMap(View view){
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }

}