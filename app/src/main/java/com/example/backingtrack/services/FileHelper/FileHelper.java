package com.example.backingtrack.services.FileHelper;

import android.os.Environment;

import java.io.File;

public class FileHelper implements IFileHelper {

    private String rootPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/BackingTrack";
    private File storageDir;

    public FileHelper() {
        storageDir = new File(rootPath);
    }

    @Override
    public String createFile(String fileName) {
        File file = new File(storageDir, fileName);
        if (!storageDir.exists() && !storageDir.mkdirs()) {
            // This should never happen - log handled exception!
        }
        return file.getAbsolutePath();
    }
}
