package com.example.countriesapp.view.Utils;

import android.content.Context;
import android.widget.ImageView;

import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.countriesapp.R;

public class ImageUtils {
    public static void loadImage(ImageView view, String url, CircularProgressDrawable circularProgressDrawable){
        RequestOptions requestOptions=new RequestOptions().
                placeholder(circularProgressDrawable).
                error(R.mipmap.ic_launcher_round);
    Glide.with(view.getContext()).setDefaultRequestOptions(requestOptions).load(url).into(view);
    }

    public static CircularProgressDrawable getProgressDrawable(Context context) {
        CircularProgressDrawable circularProgressDrawable=new CircularProgressDrawable(context);
        circularProgressDrawable.setStrokeWidth(10f);
        circularProgressDrawable.setCenterRadius(50f);
        circularProgressDrawable.start();
        return circularProgressDrawable;
    }
}
