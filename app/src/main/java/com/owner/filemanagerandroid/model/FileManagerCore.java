package com.owner.filemanagerandroid.model;

import android.os.Environment;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class FileManagerCore {
    private File currentDirectory;
    private File previousDirectory;
    //Utilizzo il tipo Stack, per ritrovare agevolmente il percorso completo
    private Stack<File> historyPath;
    public static final String TAG = "Current dir";

    public FileManagerCore() {
        init();
    }

    private void init() {
        historyPath = new Stack<>();
        currentDirectory = Environment.getExternalStorageDirectory();
    }

    public File getCurrentDirectory() {
        return currentDirectory;
    }

    public void setCurrentDirectory(File currentDirectory) {
        this.currentDirectory = currentDirectory;
    }

    public boolean hasPreviousDir() {
        return !historyPath.isEmpty();
    }

    public File getPreviousDirectory() {
        return historyPath.pop();
    }

    public void setPreviousDirectory(File previousDirectory) {
        this.previousDirectory = previousDirectory;
        historyPath.add(previousDirectory);
    }

    public List<File> getAllFiles(File path) {
        File[] allFiles = path.listFiles();
        List<File> directories = new ArrayList<>();
        List<File> files = new ArrayList<>();

        for (File file : allFiles) {
            if (file.isDirectory()) {
                directories.add(file);
            } else {
                files.add(file);
            }
        }
        Collections.sort(directories);
        Collections.sort(files);
        directories.addAll(files);
        return directories;
    }
}