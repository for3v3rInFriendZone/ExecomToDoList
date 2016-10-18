package com.example.gencode.execomtodolist.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.gencode.execomtodolist.R;

public class TaskAddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_add);

        submitTask();
    }

    private void submitTask() {
        Button submitTask = (Button)findViewById(R.id.submitTask);

        submitTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               addTaskToUser();
            }
        });
    }

    private void addTaskToUser() {
        EditText titleTask = (EditText) findViewById(R.id.taskTitle);
        EditText desTask = (EditText) findViewById(R.id.taskDescription);

        Log.d("WWWWW----->", titleTask.getText() + " - - - " + desTask.getText());
    }
}
