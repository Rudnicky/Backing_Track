package com.example.backingtrack.services.VoiceRecorder;

public interface IVoiceRecorder {
    void startRecording();
    void stopRecording();
    int getMaxAmplitude();
}
