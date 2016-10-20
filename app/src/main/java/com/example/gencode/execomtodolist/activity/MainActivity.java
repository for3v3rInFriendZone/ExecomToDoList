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
import com.example.gencode.execomtodolist.database.DatabaseHelper;
import com.example.gencode.execomtodolist.database.DatabaseManager;
import com.example.gencode.execomtodolist.model.Task;
import com.example.gencode.execomtodolist.model.User;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private UserDao userDao = null;
    private TaskDao taskDao = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.initDB();
        this.insertUsers();
        this.login();

    }


    @Override
    protected void onStop() {
        super.onStop();
        this.deleteDatabase(DatabaseHelper.DATABASE_NAME);
    }

    /**
     * Method for a login button click event.
     */
    private void login() {
        Button login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText username = (EditText) findViewById(R.id.username);
                String usernameText = username.getText().toString();

                EditText password = (EditText) findViewById(R.id.password);
                String passwordText = password.getText().toString();

                checkUsernameAndPassword(usernameText, passwordText);
            }
        });
    }

    /**
     * Method for a user credential validation.
     * @param username
     * @param password
     */
    private void checkUsernameAndPassword(String username, String password){
        List<User> users = (List<User>) userDao.findAll();
        TextView errorText = (TextView) findViewById(R.id.errorText);

        for(User user: users){
            if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                //predji na sledecu aktivnost
                Log.i("LOGIN", "Login is sucsessful.");
                errorText.setText("");

                Intent intent = new Intent(this, TaskViewActivity.class);
                intent.putExtra("user", user.getId());
                startActivity(intent);
                return;
            }
        }
        errorText.setText("Username or password is invalid.");
    }

    /**
     * Init of database.
     */
    private void initDB()
    {
        DatabaseManager.init(this);
        userDao = new UserDao(this);
        taskDao = new TaskDao(this);
    }

    /**
     * Method for creating users and tasks, for easier use of app.
     */
    private void insertUsers(){
        User user1 = new User("Djavo", "666");
        User user2 = new User("Martel", "123");

        userDao.create(user1);
        userDao.create(user2);


        Task task1 = new Task(user1, "nasov1", "opis1", false);
        Task task2 = new Task(user1, "nasov2", "opiswdqwd", false);
        Task task3 = new Task(user2, "naslov3", "opis232", false);

        taskDao.create(task1);
        taskDao.create(task2);
        taskDao.create(task3);

    }

}
