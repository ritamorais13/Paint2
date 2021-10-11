package com.example.paint;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    private static int BACK_COLOR;
    private final int REQUEST_CODE = 1;

    @Override
    public Resources getResources() {
        return super.getResources();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.layoutMain).setBackgroundColor(BACK_COLOR);
    }

    protected  void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE && data != null){
            Bundle extras = data.getExtras();
            String color = extras.getString("color");

                switch(color) {
                    case "1":
                        BACK_COLOR = getResources().getColor(R.color.c1);
                        break;
                    case "2":
                        BACK_COLOR = getResources().getColor(R.color.c2);
                        break;
                    case "3":
                        BACK_COLOR = getResources().getColor(R.color.c3);
                        break;
                    case "4":
                        BACK_COLOR = getResources().getColor(R.color.c4);
                        break;
                    case "5":
                        BACK_COLOR = getResources().getColor(R.color.c5);
                        break;
                    case "6":
                        BACK_COLOR = getResources().getColor(R.color.c6);
                        break;
                    case "7":
                        BACK_COLOR = getResources().getColor(R.color.c7);
                        break;
                    case "8":
                        BACK_COLOR = getResources().getColor(R.color.c8);
                        break;
                    default:
                        BACK_COLOR = getResources().getColor(R.color.c9);
                }
                findViewById(R.id.layoutMain).setBackgroundColor(BACK_COLOR);
        }
    }

    /* Called when the user taps the settings button */
    public void showSettings (View view){
        Intent intent = new Intent(this, ShowSettings.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    /* Called when the user taps the about button */
    public void about (View view){
        Intent intent = new Intent(this, About.class);
        startActivity(intent);

    }

}