package com.example.paint;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class FragmentCanvas extends Fragment {

    private PaintCanvas paintCanvas;

    public FragmentCanvas() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public void updateStrokeColor(int color){
        paintCanvas.changeStrokeColor(color);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Context context = getActivity().getApplicationContext();

        ShakeListener shaker = new ShakeListener(context);

        GestureListener mGestureListener = new GestureListener();
        GestureDetector mGestureDetector = new GestureDetector(getContext(), mGestureListener);
        mGestureDetector.setIsLongpressEnabled(true);
        mGestureDetector.setOnDoubleTapListener(mGestureListener);

        paintCanvas = new PaintCanvas(getContext(), null, mGestureDetector);
        mGestureListener.setCanvas(paintCanvas);

        shaker.setOnShakeListener(new ShakeListener.OnShakeListener() {
            @Override
            public void onShake() {
                paintCanvas.erase();
                inflater.inflate(R.layout.fragment_canvas, container, false);
            }
        });

        return paintCanvas;//inflater.inflate(R.layout.fragment_canvas, container, false);
    }

    public PaintCanvas getPaintCanvas(){
        return paintCanvas;
    }

}