package com.example.tdlcode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainAct extends AppCompatActivity {

    CardView btn_grocery,btn_school,btn_workout,btn_collab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        btn_grocery=findViewById(R.id.btn_grocery);
        btn_school=findViewById(R.id.btn_school);
        btn_workout=findViewById(R.id.btn_workout);
        btn_collab=findViewById(R.id.btn_collab);
        btn_grocery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainAct.this,ActivityGrocery.class));
            }
        });
        btn_school.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainAct.this,ActivitySchool.class));
            }
        });
        btn_workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainAct.this,ActivityWorkout.class));
            }
        });
        btn_collab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainAct.this,ActivityCollabrative.class));
            }
        });

    }
}