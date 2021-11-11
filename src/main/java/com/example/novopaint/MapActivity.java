package com.example.novopaint;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends AppCompatActivity {
    private GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        final Button b = findViewById(R.id.button8);
        b.setTag(1);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int status = (int) view.getTag();
                if(status == 1){
                    b.setText("Stop Drawing");
                    view.setTag(0);
                }else{
                    b.setText("Start Drawing");
                    view.setTag(1);
                }


            }
        });
        /*SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapi);
        mapFragment.getMapAsync(this::onMapReady);*/
    }

}