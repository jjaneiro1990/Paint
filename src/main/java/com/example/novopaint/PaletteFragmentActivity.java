package com.example.novopaint;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

public class PaletteFragmentActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_palette, new PaletteFragment()).commit();}
    }
}
