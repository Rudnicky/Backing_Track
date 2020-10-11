package com.example.backingtrack.activities;

import android.os.Bundle;

import com.example.backingtrack.R;
import com.example.backingtrack.activities.base.BaseActivity;

public class BackingTracksActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_backing_tracks);
    }
}