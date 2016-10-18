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

    /**
     * Metoda koja obradjuje klik na dugme login.
     */
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

    /**
     * Metoda koja proverava da li uneti kredencijali postoje u bazi.
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
                intent.putExtra("username", username);
                startActivity(intent);
                return;
            }
        }
        errorText.setText("Korisničko ime ili lozinka su pogrešni.");
    }

    /**
     * Inicijalizacija baze podataka prilikom pokretanja aplikacije.
     */
    private void initDB()
    {
        DatabaseManager.init(this);
        userDao = new UserDao(this);
        taskDao = new TaskDao(this);
    }

    /**
     * Unos korisnika, radi lakseg rada sa aplikacijom.
     */
    private void insertUsers(){
        User user1 = new User("Djavo", "666");
        User user2 = new User("Martel", "123");

        userDao.create(user1);
        userDao.create(user2);
        Log.d("ORMLITEDEMO", "User1 CREATED!!!");
    }

}
