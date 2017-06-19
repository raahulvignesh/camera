package com.example.rahul.deltatask2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.kosalgeek.android.photoutil.CameraPhoto;
import com.kosalgeek.android.photoutil.GalleryPhoto;

import java.io.IOException;
import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {


    ArrayList<String> imagelist;
    ImageView image;
    Adapterset adapterset;
    CameraPhoto cameraPhoto;
    GalleryPhoto galleryPhoto;
    String camerapath;
    ImageView imagegallery;
    final int CAMERA_REQUEST = 1130;
    final int GALLERY_REQUEST = 1220;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapterset = new Adapterset(this, imagelist);
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapterset);
        camera();
    }


    public void camera() {
        image = (ImageView) findViewById(R.id.imageView);
        imagegallery = (ImageView) findViewById(R.id.imageView2);
        cameraPhoto = new CameraPhoto(getApplicationContext());
        galleryPhoto = new GalleryPhoto(getApplicationContext());
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    startActivityForResult(cameraPhoto.takePhotoIntent(), CAMERA_REQUEST);
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(), "Camera rendering problem", Toast.LENGTH_LONG).show();
                }

            }

        });
        imagegallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    startActivityForResult(galleryPhoto.openGalleryIntent(), GALLERY_REQUEST);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "PROBLEM IN LOADING GALLERY", Toast.LENGTH_LONG).show();
                }

            }

        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            if (requestCode == CAMERA_REQUEST) {
                camerapath = cameraPhoto.getPhotoPath();
                imagelist.add(camerapath);
                adapterset.notifyDataSetChanged();

            }
        } else if (resultCode == RESULT_OK) {
            if (requestCode== GALLERY_REQUEST) {
                galleryPhoto.setPhotoUri(data.getData());
                String photoPath = galleryPhoto.getPath();
                imagelist.add(photoPath);
                adapterset.notifyDataSetChanged();

            }


        }
    }

}