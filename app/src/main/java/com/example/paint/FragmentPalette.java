package com.example.paint;

import android.arch.lifecycle.ViewModelProvider;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentPalette extends Fragment {

    private int nextStrokeColor;
    private ColorStrokeListener listener;

    public interface ColorStrokeListener{
        public void onColorStrokeChange(int color);
    }


    public FragmentPalette() {
        // Required empty public constructor
    }

    public void setStrokeColorListener(ColorStrokeListener listener){
        this.listener = listener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        nextStrokeColor = Color.BLACK;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_palette, container, false);

        Button btn = (Button) v.findViewById(R.id.brush_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != listener) {
                    listener.onColorStrokeChange(nextStrokeColor);
                    if(nextStrokeColor == Color.WHITE)
                        nextStrokeColor = Color.BLACK;
                    else
                        nextStrokeColor = Color.WHITE;
                    btn.setBackgroundColor(nextStrokeColor);
                }
            }
        });
        return v;
    }
}