package com.example.paint;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    private static int BACK_COLOR;
    private SharedPreferences preferences;

    @Override
    public Resources getResources() {
        return super.getResources();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        preferences = this.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        BACK_COLOR = preferences.getInt("back_color", Color.WHITE);
        setColor();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.about:
                about();
                return true;
            case R.id.abstractPaint:
                abstractPaint();
                return true;
            case R.id.exit:
                exit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
        setColor();
    }

    public void setColor(){
        findViewById(R.id.fragment_canvas).setBackgroundColor(BACK_COLOR);

        //Guarda a cor do background no ficheiro de preferencias
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("back_color", BACK_COLOR);
        editor.apply();
    }
    public void showPalette(View view){
        View v = findViewById(R.id.fragment_palette);
        if(v.getVisibility() == View.VISIBLE)
            v.setVisibility(View.INVISIBLE);
        else
            v.setVisibility(View.VISIBLE);
    }

    /* Called when the user taps the about button */
    public void about (){
        Intent intent = new Intent(this, About.class);
        startActivity(intent);
    }

    public void abstractPaint(){
        Intent intent = new Intent(this, Paint.class);
        startActivity(intent);
    }

    public void exit(){
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}