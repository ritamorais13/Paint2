package com.example.paint;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ShowSettings extends AppCompatActivity {
    public static String BACK_COLOR = "#FFFFFF";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_settings);
    }

    /* Called when the user taps a color button */
    public void changeBackColor(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        switch(view.getId()){
            case R.id.button3:
                intent.putExtra("color", "1");
                break;
            case R.id.button4:
                intent.putExtra("color", "2");
                break;
            case R.id.button5:
                intent.putExtra("color", "3");
                break;
            case R.id.button6:
                intent.putExtra("color", "4");
                break;
            case R.id.button7:
                intent.putExtra("color", "5");
                break;
            case R.id.button8:
                intent.putExtra("color", "6");
                break;
            case R.id.button9:
                intent.putExtra("color", "7");
                break;
            case R.id.button10:
                intent.putExtra("color", "8");
                break;
            default:
                intent.putExtra("color", "9");
        }
        setResult(1, intent);
        finish();
    }

    /*
    public void changeBackColor(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        @SuppressLint("ResourceType") int color = view.getId()-2;
        intent.putExtra(BACK_COLOR, color);
        startActivity(intent);
    }*/

}
