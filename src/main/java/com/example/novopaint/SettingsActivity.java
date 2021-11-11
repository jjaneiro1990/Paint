package com.example.novopaint;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import yuku.ambilwarna.AmbilWarnaDialog;

public class SettingsActivity extends AppCompatActivity {
    ConstraintLayout mlayout;
    int def;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int orientation = getResources().getConfiguration().orientation;
        if(orientation == Configuration.ORIENTATION_PORTRAIT){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_settings);
        }else{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_settings_landscape);
        }

        mlayout = (ConstraintLayout) findViewById(R.id.main_layout);
        def = ContextCompat.getColor(SettingsActivity.this,R.color.design_default_color_primary);
        button = (Button) findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openColor();
            }

        });

    }

    public void openColor() {
        AmbilWarnaDialog cp = new AmbilWarnaDialog(this, def, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                def = color;
                acaba(findViewById(R.id.settings_layout).getRootView(),def);
            }
        });
        cp.show();
    }

    private void acaba(View rootView, int def) {
        Intent i = new Intent();
        i.putExtra("color",def);
        setResult(0,i);
        super.finish();
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
            case R.id.main_layout:
                goToMain(view);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_settings, menu);
        return true;
    }

    public void goToAbout(View view){
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

    public void goToMain(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void goToSettings(View view){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivityForResult(intent,0);
    }

}