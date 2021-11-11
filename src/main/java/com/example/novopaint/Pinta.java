package com.example.novopaint;

import android.graphics.Paint;
import android.graphics.Path;
import android.util.Pair;

import java.util.ArrayList;

public class Pinta {
    ArrayList<Pair<Paint, Path>> arrayList;

    public Pinta(){
        ArrayList<Pair<Paint, Path>> arrayList2 = new ArrayList<>();
        this.arrayList = arrayList2;
    }

    public void setArrayPP(ArrayList<Pair<Paint, Path>> p){
        this.arrayList = p;
    }



}
