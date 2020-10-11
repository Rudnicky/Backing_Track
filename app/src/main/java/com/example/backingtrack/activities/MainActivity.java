package com.example.backingtrack.activities;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.backingtrack.R;
import com.example.backingtrack.activities.base.BaseActivity;

public class MainActivity extends BaseActivity {


    // perms
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    private static final int REQUEST_WRITE_EXTERNAL_STORAGE_PERMISSION = 201;
    private static final String[] permissions = {Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    private Button createNewButton;
    private Button backingTracksButton;
    private Button closeApplicationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setDisplayHomeAsUpEnabled(false);
        setContentView(R.layout.activity_main);
        this.InitializeOnClickListeners();

        ActivityCompat.requestPermissions(this, permissions, REQUEST_RECORD_AUDIO_PERMISSION);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_RECORD_AUDIO_PERMISSION:
             //   audioRecordingPermissionGranted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                break;
            case REQUEST_WRITE_EXTERNAL_STORAGE_PERMISSION:
              //  writeExternalStoragePermissionGranted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                break;


        }
    }

    private void InitializeOnClickListeners() {

        this.createNewButton = findViewById(R.id.createNewButton);
        this.backingTracksButton = findViewById(R.id.backingTracksButton);
        this.closeApplicationButton = findViewById(R.id.closeApplicationButton);

        this.createNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateActivity.class);
                startActivity(intent);
            }
        });

        this.backingTracksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BackingTracksActivity.class);
                startActivity(intent);
            }
        });

        this.closeApplicationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.finish();
            }
        });
    }
}