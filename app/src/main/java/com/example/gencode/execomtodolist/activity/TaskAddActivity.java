package com.example.gencode.execomtodolist.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.gencode.execomtodolist.DAO.TaskDao;
import com.example.gencode.execomtodolist.DAO.UserDao;
import com.example.gencode.execomtodolist.R;
import com.example.gencode.execomtodolist.model.Task;
import com.example.gencode.execomtodolist.model.User;
import java.sql.SQLException;

public class TaskAddActivity extends AppCompatActivity {

    private User currentUser = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_add);

        submitTask();
    }

    /**
     * Onclick event for a submit task button.
     */
    private void submitTask() {
        Button submitTask = (Button)findViewById(R.id.submitTask);

        submitTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    addTaskToUser();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Method for adding a new task to current user.
     * @throws SQLException
     */
    private void addTaskToUser() throws SQLException {
        EditText titleTask = (EditText) findViewById(R.id.taskTitle);
        EditText desTask = (EditText) findViewById(R.id.taskDescription);
        if (taskValidation(titleTask)) return;

        persistTask(titleTask, desTask);

        Intent intent = new Intent(this, TaskViewActivity.class);
        intent.putExtra("user", currentUser.getId());
        startActivity(intent);
        return;
    }


    /**
     * Validation of a task form.
     * @param titleTask
     * @return
     */
    private boolean taskValidation(EditText titleTask) {
        TextView errorTaskText = (TextView) findViewById(R.id.errorTextTask);

        if(titleTask.getText().toString().equals("")) {
            errorTaskText.setText("You must enter a task title");
            return true;
        }
        errorTaskText.setText("");
        return false;
    }

    /**
     * Method for creating new task for a current user (doesnt work!!!)
     * @param title
     * @param description
     * @throws SQLException
     */
    private void persistTask(EditText title, EditText description) throws SQLException {

        UserDao userDao = new UserDao(this);
        TaskDao taskDao = new TaskDao(this);

        currentUser = (User) userDao.findById(getIntent().getExtras().getLong("user"));
        Task task = new Task(currentUser, title.getText().toString(), description.getText().toString(), false);
        taskDao.create(task);

        Log.e("dasdas", "dasdaa");
    }
}
