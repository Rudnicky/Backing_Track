package com.example.backingtrack.services.VoiceRecorder;

import android.media.MediaRecorder;
import android.util.Log;

import com.example.backingtrack.activities.MainActivity;
import com.example.backingtrack.services.FileHelper.FileHelper;
import com.example.backingtrack.services.FileHelper.IFileHelper;

import java.io.IOException;

public class VoiceRecorder implements IVoiceRecorder {

    private MediaRecorder recorder;
    private IFileHelper fileHelper;

    public VoiceRecorder() {
        // TODO: inject the interface!
        this.fileHelper = new FileHelper();
    }

    @Override
    public void startRecording() {
        String filePath = this.fileHelper.createFile("test.m4a");
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFile(filePath);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        recorder.setAudioEncodingBitRate(96000);
        recorder.setAudioSamplingRate(44100);

        try {
            recorder.prepare();
        } catch (IOException e) {
            Log.e(MainActivity.class.getSimpleName() + ":startRecording()", "prepare() failed");
        }

        recorder.start();
    }

    @Override
    public void stopRecording() {
        if (recorder != null) {
            recorder.release();
            recorder = null;
        }
    }

    @Override
    public int getMaxAmplitude() {
        if (recorder != null){
            return recorder.getMaxAmplitude();
        } else {
            return 0;
        }
    }
}
