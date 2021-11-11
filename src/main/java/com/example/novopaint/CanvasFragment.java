package com.example.novopaint;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.graphics.Canvas;
import androidx.fragment.app.Fragment;

import android.text.Layout;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CanvasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CanvasFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private GestureDetector mGestureDetector;
    private Paint paint = new Paint();
    private int backGroundColor = Color.WHITE;
    private Path path = new Path();
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final Context c = null;
    private static int a;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private PaintCanvas canvasView;

    public CanvasFragment() {
        // Required empty public constructor
    }
    /*
    public CanvasFragment(){
        super(R.layout.fragment_canvas);
        setOnTouchListener(this);
    }*/
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CanvasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CanvasFragment newInstance(String param1, String param2) {
        CanvasFragment fragment = new CanvasFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        //View v =  inflater.inflate(R.layout.fragment_canvas, container, false);
        PaintCanvas pc = ((MainActivity)getActivity()).paintCanvas;
        int col = ((MainActivity)getActivity()).color;
        canvasView = pc;
        if(col!=0){
           a = col;
           canvasView.setBackgroundColor(a);
        }
        return canvasView;
    }

}