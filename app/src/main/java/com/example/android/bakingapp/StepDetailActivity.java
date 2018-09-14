package com.example.android.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class StepDetailActivity extends AppCompatActivity {

    public static final String TAG = "**STEP DETAIL LOG**";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);

        Intent intent = getIntent();
        String videoUrl = intent.getStringExtra("current_video_url");
        Log.i(TAG, "Video URL is: " + videoUrl);

    }
}
