package com.example.rahul.deltatask2;


import android.app.Activity;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import android.widget.Toast;

import com.kosalgeek.android.photoutil.ImageLoader;


import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.zip.Inflater;

public class Adapterset extends ArrayAdapter{

    private final Activity activity;

    ArrayList<String> images;


    public Adapterset(Activity activity,ArrayList<String> images) {
        super(activity,R.layout.activity_imageview);
        this.activity = activity;
        this.images = images;
    }

    @NonNull
    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {

        LayoutInflater inflater = activity.getLayoutInflater();
        View rowview = inflater.inflate(R.layout.activity_imageview,null,true);
        ImageView imageView = (ImageView) rowview.findViewById(R.id.imageview);
        Bitmap bitmap = null;
        try {
            bitmap = ImageLoader.init().from(images.get(position)).getBitmap();
            imageView.setImageBitmap(bitmap);
        } catch (Exception e) {
            Toast.makeText(getContext(),"done",Toast.LENGTH_LONG).show();

        }

        return rowview;
    }
}
