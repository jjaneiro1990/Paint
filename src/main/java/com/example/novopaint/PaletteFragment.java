package com.example.novopaint;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import yuku.ambilwarna.AmbilWarnaDialog;


public class PaletteFragment extends Fragment {
    int def;
    Button but;
    View view;
    private String mParam1;
    private String mParam2;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public static PaletteFragment newInstance(String param1, String param2) {
        PaletteFragment fragment = new PaletteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public PaletteFragment(){

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_palette, container, false);
        view.setBackgroundColor(PaintCanvas.backGroundColor);
        Button b = view.findViewById(R.id.button7);
        def = Color.BLACK;
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openColor();

            }
        });
        Button b2 = view.findViewById(R.id.button9);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).paintCanvas.setColor(def);
                Fragment f = new CanvasFragment();
                FragmentManager f2 = ((MainActivity) getActivity()).fragmentManager;
                FragmentTransaction lo = f2.beginTransaction();

                lo.replace(R.id.canvas,f);
                lo.addToBackStack(null);
                lo.commit();
            }
        });

        return view;
    }

    public void openColor() {
        AmbilWarnaDialog cp = new AmbilWarnaDialog(this.getContext(), def, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                def = color;


            }
        });
        cp.show();
    }

}