package com.example.paint;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;


public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private static int BACK_COLOR;
    private SharedPreferences preferences;
    private FragmentPalette fragmentPalette;
    private FragmentCanvas fragmentCanvas;
    private SensorManager sensorManager;
    private Sensor light;
    WindowManager.LayoutParams layout;

    @Override
    public Resources getResources() {
        return super.getResources();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = this.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        BACK_COLOR = preferences.getInt("back_color", Color.WHITE);
        setColor();

        FragmentManager manager = getSupportFragmentManager();
        fragmentCanvas = (FragmentCanvas) manager.findFragmentById(R.id.fragment_canvas);
        fragmentPalette = (FragmentPalette) manager.findFragmentById(R.id.fragment_palette);

        fragmentPalette.setStrokeColorListener(new FragmentPalette.ColorStrokeListener() {
            @Override
            public void onColorStrokeChange(int color) {
                fragmentCanvas.updateStrokeColor(color);
            }
        });

        //Light Sensor
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        //Screnn Brigtness
        layout = getWindow().getAttributes();
        layout.screenBrightness = 1F;
        getWindow().setAttributes(layout);
    }

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do something here if sensor accuracy changes.
    }

    @Override
    public final void onSensorChanged(SensorEvent event) {
        float ambientLight = event.values[0];

        if(ambientLight < 1){
            layout.screenBrightness = 0.0F;
        }
        else{
            if(ambientLight >1 && ambientLight <4){
                layout.screenBrightness = 0.5F;
            }
            else{
                layout.screenBrightness = 1F;
            }
        }
        getWindow().setAttributes(layout);
    }

    @Override
    protected void onResume() {
        // Register a listener for the sensor.
        super.onResume();
        sensorManager.registerListener(this, light, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        // Be sure to unregister the sensor when the activity pauses.
        super.onPause();
        sensorManager.unregisterListener(this);
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
            case R.id.map:
                map();
                return true;
            case R.id.help:
                help();
                return true;
            case R.id.about:
                about();
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

        //Guarda a cor do background no ficheiro preferences
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
    /* Called when the user taps the map button */
    public void map (){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    /* Called when the user taps the about button */
    public void about (){
        Intent intent = new Intent(this, About.class);
        startActivity(intent);
    }

    /* Called when the user taps the help button */
    public void help (){
        Intent intent = new Intent(this, Help.class);
        startActivity(intent);
    }

    public void exit(){
        android.os.Process.killProcess(android.os.Process.myPid());
    }

}