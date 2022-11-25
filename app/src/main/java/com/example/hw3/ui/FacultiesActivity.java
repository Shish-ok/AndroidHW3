package com.example.hw3.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hw3.AppDb;
import com.example.hw3.R;
import com.example.hw3.entities.Course;
import com.example.hw3.entities.Faculties;

import java.util.ArrayList;
import java.util.List;

public class FacultiesActivity extends AppCompatActivity {

    private EditText etName;
    private Button btAdd, btShowCourses, btDelete, btShowAllFaculties;

    private Faculties faculties;
    private List<Faculties> listFaculties;
    private  List<Course> listCours;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculties);
        configView();
    }

    private void configView(){
        faculties = new Faculties();
        listFaculties = new ArrayList<>();
        listCours = new ArrayList<>();
        listView = findViewById(R.id.listView);

        etName = findViewById(R.id.facultiesActivityName);
        btAdd = findViewById(R.id.languageActivityBtAdd);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                faculties.setName(etName.getText().toString());
                AppDb.getAppDb(getApplicationContext()).languagesDAO().insert(faculties);
                listFaculties = AppDb.getAppDb(getApplicationContext()).languagesDAO().findAllLanguages();
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(FacultiesActivity.this,
                        android.R.layout.simple_list_item_1, show(listFaculties));
                listView.setAdapter(adapter);
            }
        });

        btShowAllFaculties = findViewById(R.id.FacultiesActivityBtShowAllFaculties);
        btShowAllFaculties.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                listFaculties = AppDb.getAppDb(getApplicationContext()).languagesDAO().findAllLanguages();
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(FacultiesActivity.this,
                        android.R.layout.simple_list_item_1, show(listFaculties));
                listView.setAdapter(adapter);
            }
        });

        btShowCourses = findViewById(R.id.FacultiesActivityBtShowCourses);
        btShowCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listCours = AppDb.getAppDb(getApplicationContext()).courseDAO().findCoursesForFaculties(etName.getText().toString());
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(FacultiesActivity.this,
                        android.R.layout.simple_list_item_1, show(listCours));
                listView.setAdapter(adapter);
            }
        });



        btDelete = findViewById(R.id.FacultiesActivityBtDeleteCourseByName);
        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppDb.getAppDb(getApplicationContext()).languagesDAO().deleteLanguageByName(etName.getText().toString());
                listFaculties = AppDb.getAppDb(getApplicationContext()).languagesDAO().findAllLanguages();
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(FacultiesActivity.this,
                        android.R.layout.simple_list_item_1, show(listFaculties));
                listView.setAdapter(adapter);
                Toast.makeText(getApplicationContext(),"Deleted",Toast.LENGTH_LONG).show();
            }
        });
    }

    private <E> String[] show(List<E> array){
        return array.stream().map(E::toString).toArray(String[]::new);
    }
}