package com.example.bohyun.fitime;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button skipButton = (Button) findViewById(R.id.skip_button);
        Button loginButton = (Button) findViewById(R.id.login_button);



        skipButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast.makeText(LoginActivity.this,"Continuing as guest", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),MainActivity.class ));
            }
        });


        loginButton.setOnClickListener(new View.OnClickListener(){
           @Override
            public void onClick(View view){

               final String username = ((EditText)findViewById(R.id.editText)).getText().toString();
               final String password = ((EditText)findViewById(R.id.editText2)).getText().toString();

               if (correctLogin(username, password)) {
                   Toast.makeText(LoginActivity.this,"Successfully logged in", Toast.LENGTH_SHORT).show();
                   startActivity(new Intent(getApplicationContext(),MainActivity.class ));
               }
               else {

                   Toast.makeText(LoginActivity.this,"Successfully did not log in", Toast.LENGTH_SHORT).show();
               }
           }
        });

    }

    public static boolean correctLogin(String inputUser, String inputPass) {
        List usernames = Arrays.asList("alex", "cameron", "justin", "boh", "jared", "topa");
        List passwords = Arrays.asList("alex1", "cameron1", "justin1", "boh1", "jared1", "topa1");

        if ((usernames.indexOf(inputUser) != -1) && ((usernames.indexOf(inputUser)) == (passwords.indexOf(inputPass)))) {
            return true;
        }

        return false;
    }
}
