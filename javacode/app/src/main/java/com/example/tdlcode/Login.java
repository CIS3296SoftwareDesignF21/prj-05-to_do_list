package com.example.tdlcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import domain.User;


public class Login extends AppCompatActivity {
    //private static LoadMap loadMap = LoadMap.getLoadMap();
    private static HashMap<String,User> usersMap = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //let user to log in.
        Button loginBtn = (Button)findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                loadMap();
                //get user's name and password
                TextView nametx = (TextView)findViewById(R.id.uname);
                TextView passwordtx = (TextView)findViewById(R.id.upassword);
                String name = nametx.getText().toString();
                String password = passwordtx.getText().toString();
                //load user's information
                //check user's exist status
                if(checkUser(name,password)){
                    //display banner to welcome the user
                    Toast.makeText(Login.this,"Welcome Home",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this, MainAct.class);
                    startActivity(intent);
                }else{
                    //stay and requires to try again.
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
                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
            }
        });
    }

    //function to check User information by getting string value from TextView
    //true exist, false not
    private boolean checkUser(String name, String password){
        //get existing user from loaded map
        User user = usersMap.get(name);
        System.out.println(user);
        //go to check password
        if(user!=null){
            if(user.getPassword().equals(password)){
                //find an existed user
                return true;
            }
        }
        //no such user
        return false;
    }

    private void loadMap(){
        InputStream input = null;
        BufferedReader reader = null;
        try{
            //get file from users.txt
            //input = getResources().openRawResource(R.raw.users);
            input = openFileInput("users.txt");
            reader = new BufferedReader(new InputStreamReader(input));
            String content = reader.readLine();
            while (!"".equals(content) && content != null){
                //parse user information
                User existUser = parseUser(content);
                //store the user into userMap
                usersMap.put(existUser.getName(),existUser);
                //loop next user
                content = reader.readLine();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private User parseUser(String userInfo){
        //parse userInfo
        //{user1,password1,111@gmail.com,111111,123123}
        //remove first and last
        userInfo = userInfo.substring(1,userInfo.length()-1);
        //user1,password1,111@gmail.com,111111,123123
        String[] infoList = userInfo.split(",");
        User user = new User(infoList[0],infoList[1],infoList[2],infoList[3],infoList[4]);
        return user;
    }
}