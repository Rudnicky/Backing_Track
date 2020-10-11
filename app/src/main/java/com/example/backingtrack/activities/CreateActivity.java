package com.example.backingtrack.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.backingtrack.R;
import com.example.backingtrack.activities.base.BaseActivity;
import com.example.backingtrack.services.VoiceRecorder.IVoiceRecorder;
import com.example.backingtrack.services.VoiceRecorder.VoiceRecorder;
import com.visualizer.amplitude.AudioRecordView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CreateActivity extends BaseActivity {

    private Button recordButton;
    private AudioRecordView audioRecordView;

    // TODO: this must be injected somehow.
    // TODO: best way would be to use some mvvm pattern.
    private IVoiceRecorder voiceRecorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_create);

        this.voiceRecorder = new VoiceRecorder();
        this.audioRecordView = findViewById(R.id.audioRecordView);

        this.recordButton = findViewById(R.id.recordButton);
        recordButton.setText("start");
        this.recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (recordButton.getText() == "start") {
                    recordButton.setText("stop");
                    voiceRecorder.startRecording();
                    startDrawing();
                } else {
                    recordButton.setText("start");
                    voiceRecorder.stopRecording();
                    stopDrawing();
                }
            }
        });
    }

    private Disposable timerSubscribe;

    private void startDrawing() {
        timerSubscribe = Observable.interval(100, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long t) {
                        int currentMaxAmplitude = voiceRecorder.getMaxAmplitude();
                        audioRecordView.update(currentMaxAmplitude); //redraw view
                    }
                });
    }

    private void stopDrawing() {
        if (timerSubscribe != null){
            timerSubscribe.dispose();
        }
        audioRecordView.recreate();
    }
}