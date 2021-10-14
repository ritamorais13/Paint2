package com.example.paint;

import android.app.Fragment;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    private static int BACK_COLOR;

    @Override
    public Resources getResources() {
        return super.getResources();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /* Called when the user taps a color button */
    public void changeBackColor(View view) {
        switch(view.getId()) {
            case R.id.button1:
                BACK_COLOR = getResources().getColor(R.color.c1);
                break;
            case R.id.button2:
                BACK_COLOR = getResources().getColor(R.color.c2);
                break;
            case R.id.button3:
                BACK_COLOR = getResources().getColor(R.color.c3);
                break;
            case R.id.button4:
                BACK_COLOR = getResources().getColor(R.color.c4);
                break;
            case R.id.button5:
                BACK_COLOR = getResources().getColor(R.color.c5);
                break;
            case R.id.button6:
                BACK_COLOR = getResources().getColor(R.color.c6);
                break;
            case R.id.button7:
                BACK_COLOR = getResources().getColor(R.color.c7);
                break;
            case R.id.button8:
                BACK_COLOR = getResources().getColor(R.color.c8);
                break;
            case R.id.button9:
                BACK_COLOR = getResources().getColor(R.color.c9);
                break;
            case R.id.button10:
                BACK_COLOR = getResources().getColor(R.color.c10);
                break;
            case R.id.button11:
                BACK_COLOR = getResources().getColor(R.color.c11);
                break;
            default:
                BACK_COLOR = getResources().getColor(R.color.c12);
        }
        findViewById(R.id.fragment_canvas).setBackgroundColor(BACK_COLOR);
    }

    public void showPalette(View view){
        View v = findViewById(R.id.fragment_palette);
        if(v.getVisibility() == View.VISIBLE)
            v.setVisibility(View.INVISIBLE);
        else
            v.setVisibility(View.VISIBLE);
    }

    /* Called when the user taps the about button */
    public void about (View view){
        Intent intent = new Intent(this, About.class);
        startActivity(intent);
    }




}