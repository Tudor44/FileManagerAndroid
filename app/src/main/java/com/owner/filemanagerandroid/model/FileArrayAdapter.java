package com.owner.filemanagerandroid.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.owner.filemanagerandroid.R;

import java.io.File;
import java.util.List;

public class FileArrayAdapter extends ArrayAdapter<File> {
    private Context activityContext;
    private int idViewResource;
    private List<File> elements;

    public FileArrayAdapter(Context activityContext, int idViewResource, List<File> elements) {
        super(activityContext, idViewResource, elements);
        this.activityContext = activityContext;
        this.idViewResource = idViewResource;
        this.elements = elements;
    }

    public FileArrayAdapter(Context activityContext, int idViewResource) {
        super(activityContext, idViewResource);
        this.activityContext = activityContext;
        this.idViewResource = idViewResource;
    }

    @Override
    public File getItem(int item) {
        return elements.get(item);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View thisView = convertView;

        if (thisView == null) {
            LayoutInflater inflater = (LayoutInflater.from(activityContext));
            thisView = inflater.inflate(idViewResource, null);
        }
        ImageView imageView = (ImageView) thisView.findViewById(R.id.imageView);
        TextView nameView = (TextView) thisView.findViewById(R.id.name_text_view);
        File file = getItem(position);

        /* Controllo se l'elemento è una directory oppure un file, cambiando l'icona */
        if (file.isDirectory()) {
            imageView.setImageResource(R.drawable.ic_folder_black);
        } else {
            imageView.setImageResource(R.drawable.ic_insert_drive_file_black);
        }

        nameView.setText(file.getName());
        return thisView;
    }
}