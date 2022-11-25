package com.example.hw3.ui;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hw3.AppDb;
import com.example.hw3.R;
import com.example.hw3.entities.Course;

import java.util.ArrayList;
import java.util.List;

public class CoursesActivity extends AppCompatActivity {

    ListView listView;
    private EditText etIdStudent, etName, etIdLanguage;
    private Button btAdd, btShowCoursesByStudent, btUpdateCourseInfo,btShowAllCourses,btDeleteByName;
    private Course course;
    private List<Course> courses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        configView();
    }

    private void configView() {
        course = new Course();
        courses = new ArrayList<>();
        etIdStudent = findViewById(R.id.courseActivityIdStudent);
        etName = findViewById(R.id.courseActivityName);
        etIdLanguage = findViewById(R.id.courseActivityIdFaculty);
        listView = findViewById(R.id.listView);

        btAdd = findViewById(R.id.courseActivityBtAdd);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                course.setName(etName.getText().toString());
                course.setStudentId(Integer.parseInt(etIdStudent.getText().toString()));
                course.setFacultiesId(Integer.parseInt(etIdLanguage.getText().toString()));
                AppDb.getAppDb(getApplicationContext()).courseDAO().insert(course);

                courses = AppDb.getAppDb(getApplicationContext()).courseDAO().findAllCourses();
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(CoursesActivity.this,
                        android.R.layout.simple_list_item_1, show(courses));
                listView.setAdapter(adapter);

            }
        });

        btShowAllCourses = findViewById(R.id.courseActivityBtShowAllCourses);
        btShowAllCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                courses = AppDb.getAppDb(getApplicationContext()).courseDAO().findAllCourses();
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(CoursesActivity.this,
                        android.R.layout.simple_list_item_1, show(courses));
                listView.setAdapter(adapter);
            }
        });



        btShowCoursesByStudent = findViewById(R.id.courseActivityBtShowCoursesByStudent);
        btShowCoursesByStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                courses = AppDb.getAppDb(getApplicationContext()).courseDAO().findCoursesForProfessor(Integer.parseInt(etIdStudent.getText().toString()));

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(CoursesActivity.this,
                        android.R.layout.simple_list_item_1, show(courses));
                listView.setAdapter(adapter);
            }
        });

        btUpdateCourseInfo = findViewById(R.id.courseActivityBtUpdateCourseInfo);
        btUpdateCourseInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppDb.getAppDb(getApplicationContext()).courseDAO().updateCourseByID(Integer.parseInt(etIdLanguage.getText().toString()), Integer.parseInt(etIdStudent.getText().toString()), etName.getText().toString());
                courses = AppDb.getAppDb(getApplicationContext()).courseDAO().findAllCourses();
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(CoursesActivity.this,
                        android.R.layout.simple_list_item_1, show(courses));
                listView.setAdapter(adapter);
                Toast.makeText(getApplicationContext(),"Info update "+ etName.getText().toString(),Toast.LENGTH_LONG).show();
            }
        });

        btDeleteByName = findViewById(R.id.courseActivityBtDeleteByName);
        btDeleteByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppDb.getAppDb(getApplicationContext()).courseDAO().deleteCourseByName(etName.getText().toString());
                courses = AppDb.getAppDb(getApplicationContext()).courseDAO().findAllCourses();
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(CoursesActivity.this,
                        android.R.layout.simple_list_item_1, show(courses));
                listView.setAdapter(adapter);
            }
        });
    }

    private <E> String[] show(List<E> array){
        return array.stream().map(E::toString).toArray(String[]::new);
    }


}