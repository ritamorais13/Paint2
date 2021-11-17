package com.example.paint;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.annotation.RequiresApi;

public class PaintCanvas extends View implements View.OnTouchListener{

    private List<Paint> paintList = new ArrayList<>();
    private List<Path> pathList = new ArrayList<>();
    private SharedPreferences preferences;
    private int backGroundColor = Color.WHITE;
    private GestureDetector mGestureDetector;

    public PaintCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);

        preferences = context.getSharedPreferences(getResources().getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        this.backGroundColor = preferences.getInt("back_color", Color.WHITE);

        setOnTouchListener(this);
        setBackgroundColor(backGroundColor);

        pathList.add(new Path());
        initPaint();
    }

    public PaintCanvas(Context context, AttributeSet attrs, GestureDetector mGestureDetector) {
        super(context, attrs);

        preferences = context.getSharedPreferences(getResources().getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        this.backGroundColor = preferences.getInt("back_color", Color.WHITE);

        this.mGestureDetector = mGestureDetector;
        setOnTouchListener(this);
        setBackgroundColor(backGroundColor);
        pathList.add(new Path());
        initPaint();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onDraw(Canvas canvas) {
        int i = 0;
        for(Path p : pathList) {
            canvas.drawPath(p, paintList.get(i));// draws the path with the paint
            i++;
        }
    }

    @Override
    public boolean performClick(){
        return super.performClick();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        return false; // let the event go to the rest of the listeners
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float eventX = event.getX();
        float eventY = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                pathList.get(pathList.size()-1).moveTo(eventX, eventY);// updates the path initial point
                return true;
            case MotionEvent.ACTION_MOVE:
                pathList.get(pathList.size()-1).lineTo(eventX, eventY);// makes a line to the point each time this event is fired
                break;
            case MotionEvent.ACTION_UP:// when you lift your finger
                performClick();
                break;
            default:
                return false;
        }

        // Schedules a repaint.
        invalidate();
        return true;
    }

    public void changeStrokeColor(int color){
        pathList.add(new Path());
        initPaint();
        paintList.get(paintList.size()-1).setColor(color);
    }

    public void changeBackground(){
        Random r = new Random();
        backGroundColor = Color.rgb(r.nextInt(256), r.nextInt(256), r.nextInt(256));
        setBackgroundColor(backGroundColor);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("back_color", backGroundColor);
        editor.commit();
    }

    public void erase(){
        pathList = new ArrayList();
        pathList.add(new Path());
        paintList = new ArrayList<>();
        initPaint();
        invalidate();
    }

    private void initPaint(){
        paintList.add(new Paint());
        paintList.get(paintList.size()-1).setAntiAlias(true);
        paintList.get(paintList.size()-1).setStrokeWidth(20f);
        paintList.get(paintList.size()-1).setColor(Color.BLACK);
        paintList.get(paintList.size()-1).setStyle(Paint.Style.STROKE);
        paintList.get(paintList.size()-1).setStrokeJoin(Paint.Join.ROUND);
    }

    public List<Paint> getPaintList(){
        return paintList;
    }
    public List<Path> getPathList(){
        return pathList;
    }
    public int getBackGroundColor(){
        return backGroundColor;
    }
    public void setPaintList(List<Paint> paintList){
        this.paintList = paintList;
    }
    public void setPathList(List<Path> pathList){
        this.pathList = pathList;
    }
    public void setBackGroundColor(int backGroundColor){
        this.backGroundColor = backGroundColor;
    }
}