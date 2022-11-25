package com.example.hw3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.hw3.ui.CoursesActivity;
import com.example.hw3.ui.FacultiesActivity;
import com.example.hw3.ui.StudentsActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnStudents, btnCourses, btnFaculties;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configView();
    }
    private void configView(){
        btnStudents = findViewById(R.id.mainAcitivityBtStudents);
        btnStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), StudentsActivity.class));
            }
        });
        btnCourses = findViewById(R.id.mainAcitivityBtCourses);
        btnCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CoursesActivity.class));
            }
        });

        btnFaculties = findViewById(R.id.mainAcitivityBtFaculties);
        btnFaculties.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), FacultiesActivity.class));
            }
        });
    }
}