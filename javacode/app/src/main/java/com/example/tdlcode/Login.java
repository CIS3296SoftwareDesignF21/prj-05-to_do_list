package com.example.tdlcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //let user to log in.
        Button loginBtn = (Button)findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //get user's name and password
                TextView nametx = (TextView)findViewById(R.id.uname);
                TextView passwordtx = (TextView)findViewById(R.id.upassword);
                String name = nametx.getText().toString();
                String password = passwordtx.getText().toString();
                //check if there is an existing user, then allow him to log in
                if("testname".equals(name) && "testpassword".equals(password)){
                    Toast.makeText(Login.this,"Welcome Home",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this,Account.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(Login.this,"Incorrect name or password",Toast.LENGTH_SHORT).show();
                }
            }
        });

        //let user to register
        Button regBtn = (Button)findViewById(R.id.regBtn);
        regBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {


                //show registration frame
                Intent intent = new Intent(Login.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    //function to check User information by getting string value from TextView

}