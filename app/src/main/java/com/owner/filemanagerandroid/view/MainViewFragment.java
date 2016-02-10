package com.owner.filemanagerandroid.view;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.owner.filemanagerandroid.MainActivity;
import com.owner.filemanagerandroid.R;
import com.owner.filemanagerandroid.controller.ControllerFragment;

public class MainViewFragment extends ListFragment implements MainActivity.IBackPressedListener {
    private ControllerFragment controllerFragment;
    private Activity activityContext;


    public void setControllerFragment(ControllerFragment controllerFragment) {
        this.controllerFragment = controllerFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.listfragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MainActivity) getActivity()).setOnBackPressedListener(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setControllerFragment(new ControllerFragment(this));
        registerForContextMenu(getListView());
        activityContext = (MainActivity) getActivity();
    }

    @Override
    public void onListItemClick(ListView listView, android.view.View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);
        controllerFragment.listItemClicked(position);
    }
    @Override
    public void doActionBackPressed() {
        controllerFragment.backPressed();
    }
}