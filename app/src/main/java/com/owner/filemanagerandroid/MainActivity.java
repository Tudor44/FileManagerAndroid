package com.owner.filemanagerandroid;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.owner.filemanagerandroid.view.MainViewFragment;

public class MainActivity extends AppCompatActivity {

    private MainViewFragment mainViewFragment;
    protected IBackPressedListener backPressedListener;

    public void setOnBackPressedListener(IBackPressedListener backPressedListener) {
        this.backPressedListener = backPressedListener;
    }

    @Override
    public void onBackPressed(){
        if (backPressedListener != null)
            backPressedListener.doActionBackPressed();
        else
            super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainViewFragment = (MainViewFragment) getFragmentManager().findFragmentById(R.id.file_list);
    }

    @Override
    protected void onDestroy(){
        backPressedListener = null;
        super.onDestroy();
    }

    public interface IBackPressedListener {
        void doActionBackPressed();
    }

}