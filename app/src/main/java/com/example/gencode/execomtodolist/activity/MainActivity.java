package com.example.gencode.execomtodolist.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.gencode.execomtodolist.DAO.TaskDao;
import com.example.gencode.execomtodolist.DAO.UserDao;
import com.example.gencode.execomtodolist.R;
import com.example.gencode.execomtodolist.database.DatabaseManager;
import com.example.gencode.execomtodolist.model.User;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    UserDao userDao = null;
    TaskDao taskDao = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.initDB();
        this.insertUsers();

        Login();

    }

    private void Login() {
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

    private void checkUsernameAndPassword(String username, String password){
        List<User> users = (List<User>) userDao.findAll();

        for(User user: users){
            if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                //predji na sledecu aktivnost
                Log.i("LOGIN", "Login is sucsessful.");
                break;
            }
        }
        Log.i("LOGIN", "Login has failed.");
    }


    private void initDB()
    {
        DatabaseManager.init(this);
        userDao = new UserDao(this);
        taskDao = new TaskDao(this);
    }

    private void insertUsers(){
        User user1 = new User("Djavo", "666");
        User user2 = new User("Martel", "123");

        userDao.create(user1);
        userDao.create(user2);
        Log.d("ORMLITEDEMO", "User1 CREATED!!!");
    }

}
