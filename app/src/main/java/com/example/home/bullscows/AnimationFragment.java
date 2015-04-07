package com.example.home.bullscows;

import android.app.DialogFragment;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class AnimationFragment extends DialogFragment {
    ImageView animationFrame;
    int cows;
    int bulls;

    static AnimationFragment newInstance(int cows, int bulls) {
        AnimationFragment f = new AnimationFragment();

        Bundle args = new Bundle();
        args.putInt("cows", cows);
        args.putInt("bulls", bulls);
        f.setArguments(args);

        return f;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.animation_frame, container,
                false);
        animationFrame = (ImageView) rootView.findViewById(R.id.animView);

        cows = getArguments().getInt("cows");
        bulls = getArguments().getInt("bulls");

        if(cows + bulls == 0){
            animationFrame.setBackgroundResource(R.drawable.looser_animation);
        }else if(cows + bulls == 1){
            animationFrame.setBackgroundResource(R.drawable.one_cow_animation);
        }else if(cows + bulls == 2){
            animationFrame.setMinimumHeight(200);
            animationFrame.setMinimumWidth(200);
            animationFrame.setScaleType(ImageView.ScaleType.FIT_XY);
            animationFrame.setBackgroundResource(R.drawable.two_cows_animation);
        }else if(cows + bulls == 3){
            animationFrame.setBackgroundResource(R.drawable.three_cows_animation);
        }else if(bulls == 4){
            animationFrame.setBackgroundResource(R.drawable.four_bulls_animation);
        }else if(cows + bulls == 4  && bulls != 4) {
            animationFrame.setBackgroundResource(R.drawable.four_cows_animation);
        }

        AnimationDrawable frameAnimation = (AnimationDrawable) animationFrame.getBackground();
        frameAnimation.start();
        // Do something else
        return rootView;
    }


}
