package com.example.tdlcode;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ActivityCollabrative extends AppCompatActivity {

    EditText txt_input;
    ListView listView;
    Button btn_save;
    ArrayList<String> grocery_list=new ArrayList<>();
    ArrayList<String> extracted_list=new ArrayList<>();
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery);

        listView=findViewById(R.id.listview);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long id) {

                grocery_list.remove(pos);
                adapter.notifyDataSetChanged();

                return true;
            }
        });
        txt_input=findViewById(R.id.txt_input);
        btn_save=findViewById(R.id.btn_save);

        grocery_list.clear();
        grocery_list=load();
        adapter = new ArrayAdapter<String>(ActivityCollabrative.this, R.layout.activity_listview, grocery_list);
        listView.setAdapter(adapter);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(txt_input.getText().toString().trim().equals("")){
                    Toast.makeText(ActivityCollabrative.this, "Please input something", Toast.LENGTH_SHORT).show();
                }
                else{
                    save(Helper.GetData(ActivityCollabrative.this,"username")+","+txt_input.getText().toString());
                    grocery_list.clear();
                    grocery_list=load();
                    adapter = new ArrayAdapter<String>(ActivityCollabrative.this, R.layout.activity_listview, grocery_list);
                    listView.setAdapter(adapter);

                }

            }
        });


    }
    public ArrayList<String>  load() {
        ArrayList<String> list=new ArrayList<>();
        FileInputStream fis = null;
        try {
            fis = openFileInput("collab");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;
            while ((text = br.readLine()) != null) {
                sb.append(text).append("\n");


            }

            String[] array = sb.toString().split("\n");
            List<String> converted_list = Arrays.asList(array);


            for(int i=0;i<converted_list.size();i++){

                String[] separated = converted_list.get(i).split(",");
                if(separated[0].equals(Helper.GetData(ActivityCollabrative.this,"username")))
                    list.add( separated[1]);


            }



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }


    public void save(String text) {

        FileOutputStream fos = null;
        try {
            fos = openFileOutput("collab", MODE_APPEND);
            fos.write(text.getBytes());
            fos.write(10);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



}