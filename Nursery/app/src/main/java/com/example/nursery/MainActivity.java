package com.example.nursery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.android.volley.toolbox.NetworkImageView;
import com.example.nursery.network.ImageRequester;

public class MainActivity extends AppCompatActivity {

    private ImageRequester imageRequester;
    private NetworkImageView myImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String url = "http://10.0.2.2:5000/images/1.jpg";

        imageRequester = ImageRequester.getInstance();
        myImage = findViewById(R.id.myImage);
        imageRequester.setImageFromUrl(myImage, url);
    }
}