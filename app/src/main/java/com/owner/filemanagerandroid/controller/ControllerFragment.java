package com.owner.filemanagerandroid.controller;

import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Loader;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.owner.filemanagerandroid.R;
import com.owner.filemanagerandroid.model.FileArrayAdapter;
import com.owner.filemanagerandroid.model.FileManagerCore;
import com.owner.filemanagerandroid.view.MainViewFragment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ControllerFragment extends FragmentActivity implements LoaderManager.LoaderCallbacks<List<File>> {
    private MainViewFragment mainViewFragment;
    private FileManagerCore fileManagerCore;
    private FileArrayAdapter fileArrayAdapter;
    private List<File> listFiles;
    private AsyncTaskLoader<List<File>> fileLoader;

    public ControllerFragment(MainViewFragment mainViewFragment) {
        this.mainViewFragment = mainViewFragment;
        this.fileManagerCore = new FileManagerCore();
        this.listFiles = new ArrayList<>();
        init();
    }

    private void init() {
        fileArrayAdapter = new FileArrayAdapter(mainViewFragment.getActivity(),
                R.layout.list_row, listFiles);
        mainViewFragment.setListAdapter(fileArrayAdapter);
        mainViewFragment.getActivity().getLoaderManager().initLoader(0, null, this);
        fileLoader.forceLoad();
    }

    private void updateAdapter(List<File> elements) {
        fileArrayAdapter.clear();
        fileArrayAdapter.addAll(elements);
        fileArrayAdapter.notifyDataSetChanged();
    }

    public void listItemClicked(int position) {
        File fileClicked = fileArrayAdapter.getItem(position);
        if (fileClicked.isDirectory()) {
            fileManagerCore.setPreviousDirectory(fileManagerCore.getCurrentDirectory());
            fileManagerCore.setCurrentDirectory(fileClicked);
            fileLoader.onContentChanged();
        }
    }

    public void backPressed() {
        if (fileManagerCore.hasPreviousDir()) {
            fileManagerCore.setCurrentDirectory(fileManagerCore.getPreviousDirectory());
            fileLoader.onContentChanged();
        }
    }

    @Override
    public Loader<List<File>> onCreateLoader(int id, Bundle args) {
        fileLoader = new AsyncTaskLoader<List<File>>(mainViewFragment.getActivity()) {

            @Override
            public List<File> loadInBackground() {
               return fileManagerCore.getAllFiles(fileManagerCore.getCurrentDirectory());
            }
        };
        return fileLoader;
    }

    @Override
    public void onLoadFinished(Loader<List<File>> loader, List<File> data) {
        this.listFiles = data;
        updateAdapter(data);
    }

    @Override
    public void onLoaderReset(Loader<List<File>> loader) {
    }

}