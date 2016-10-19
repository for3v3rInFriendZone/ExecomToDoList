package com.example.gencode.execomtodolist.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.gencode.execomtodolist.R;
import com.example.gencode.execomtodolist.model.Task;

import java.util.List;

/**
 * Created by Marko on 19-Oct-16.
 */

public class AdapterTask extends ArrayAdapter<Task> {

    private Activity activity;
    private List<Task> taskList;
    private static LayoutInflater inflater = null;

    public AdapterTask(Activity activity, int textViewResourceId, List<Task> taskList) {
        super(activity, textViewResourceId, taskList);

        try {
            this.activity = activity;
            this.taskList = taskList;

            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }
    }

    public int getCount() {
        return taskList.size();
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        // Get the current item from ListView
        View view = super.getView(position,convertView,parent);

        if(taskList.get(position).isDone()) {
            view.setBackgroundColor(Color.parseColor("#999999"));
        } else {
            view.setBackgroundColor(Color.parseColor("#fafafa"));
        }

        return view;
    }
};

