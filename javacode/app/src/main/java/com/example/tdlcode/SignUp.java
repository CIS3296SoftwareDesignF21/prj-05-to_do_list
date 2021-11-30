package com.example.tdlcode;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Set;
import domain.User;


public class SignUp extends AppCompatActivity {
    private static HashMap<String,User> usersMap = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Button registerBtn = (Button)findViewById(R.id.rigbtn);
        registerBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                loadMap();
                //get information from inputs
                TextView nameView = (TextView) findViewById(R.id.name);
                String name = nameView.getText().toString();
                TextView passwordView = (TextView) findViewById(R.id.password);
                String password = passwordView.getText().toString();
                TextView addressView = (TextView) findViewById(R.id.address);
                String address = "".equals(addressView.getText().toString())?"null":addressView.getText().toString();
                TextView phoneView = (TextView) findViewById(R.id.phone);
                String phone = "".equals(phoneView.getText().toString())?"null":phoneView.getText().toString();
                TextView codeView = (TextView) findViewById(R.id.code);
                String code = "".equals(codeView.getText().toString())?"null":codeView.getText().toString();
                //check if the user name had been registered
                String errorMsg = ""; //error message
                if(usersMap.get(name) == null && !"".equals(name) && !"".equals(password)){
                    //no such user
                    //store the new user
                    User newUser = new User(name,password,address,phone,code);
                    usersMap.put(newUser.getName(),newUser);
                    storeUser();
                    //go to welcome page
                    Toast.makeText(SignUp.this,"Restart the app please",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignUp.this, MainAct.class);
                    startActivity(intent);
                }else if("".equals(name) || "".equals(password)){
                    //ask user to make another name
                    errorMsg = "Name or password cannot be empty";
                }else{
                    errorMsg = "User had been registered";
                }
                Toast.makeText(SignUp.this,errorMsg,Toast.LENGTH_SHORT).show();
            }
        });
    }


    //load existing users
    private void loadMap(){
        InputStream input = null;
        BufferedReader reader = null;
        try{
            //get users.txt from data/data/com.example.tdlcode/files
            input = openFileInput("users.txt");
            //delete original file location
            //input = getResources().openRawResource(R.raw.users);
            reader = new BufferedReader(new InputStreamReader(input));
            String content = reader.readLine();
            while (!"".equals(content) && content != null){
                //parse user information
                User exisUser = parseUser(content);
                //store the user into userMap
                usersMap.put(exisUser.getName(),exisUser);
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
        for(String v : infoList){
            System.out.println(v);
        }
        User user = new User(infoList[0],infoList[1],infoList[2],infoList[3],infoList[4]);
        return user;
    }

    private void storeUser(){
        //make users.txt
        //get users information from usersMap
        FileOutputStream out = null;
        try {
            out = openFileOutput("users.txt",Context.MODE_PRIVATE);
            Set<String> keys = usersMap.keySet();
            for(String key : keys) {
                //write the user into users.txt
                User user = usersMap.get(key);
                //write user into users.txt
                out.write(user.toString().getBytes(StandardCharsets.UTF_8));
                out.write("\n".getBytes(StandardCharsets.UTF_8));
            }
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(out!= null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}