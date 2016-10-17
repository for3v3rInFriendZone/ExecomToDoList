package com.example.gencode.execomtodolist.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.example.gencode.execomtodolist.DAO.TaskDao;
import com.example.gencode.execomtodolist.DAO.UserDao;
import com.example.gencode.execomtodolist.R;
import com.example.gencode.execomtodolist.database.DatabaseManager;
import com.example.gencode.execomtodolist.model.User;


public class MainActivity extends AppCompatActivity {

    UserDao userDao = null;
    TaskDao taskDao = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.initDB();
        this.insertUsers();

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
