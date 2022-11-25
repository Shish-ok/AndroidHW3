package com.example.hw3.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.hw3.AppDb;
import com.example.hw3.R;
import com.example.hw3.entities.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentsActivity extends AppCompatActivity {

    private EditText etName, etEmail;
    private Button btAdd, btListStudents,btFindByName,btDeleteByName;
    ListView listView;

    private Student student;
    private List<Student> listStudents;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);
        configView();
    }

    private void configView(){
        student = new Student();
        listStudents = new ArrayList<>();
        etName = findViewById(R.id.studentActivityInputName);
        listView = findViewById(R.id.listView);
        etEmail = findViewById(R.id.studentActivityInputEmail);
        btAdd = findViewById(R.id.studentActivityBtAdd);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                student.setName(etName.getText().toString());
                student.setEmail(etEmail.getText().toString());

                AppDb.getAppDb(getApplicationContext()).professorDAO().insertProfessor(student);
                listStudents = AppDb.getAppDb(getApplicationContext()).professorDAO().findAllProfessor();
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(StudentsActivity.this,
                        android.R.layout.simple_list_item_1, show(listStudents));
                listView.setAdapter(adapter);
            }
        });




        btListStudents = findViewById(R.id.studentActivityBtListProfessors);
        btListStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listStudents = AppDb.getAppDb(getApplicationContext()).professorDAO().findAllProfessor();
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(StudentsActivity.this,
                        android.R.layout.simple_list_item_1, show(listStudents));
                listView.setAdapter(adapter);
            }
        });



        btFindByName = findViewById(R.id.studentActivityBtFindByName);
        btFindByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listStudents = AppDb.getAppDb(getApplicationContext()).professorDAO().findProfessorByName(etName.getText().toString());

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(StudentsActivity.this,
                        android.R.layout.simple_list_item_1, show(listStudents));
                listView.setAdapter(adapter);
                if( listStudents.size() == 0){
                    Log.d("TAG", "Students list is empty");
                }
            }
        });



        btDeleteByName = findViewById(R.id.studentActivityBtDelete);
        btDeleteByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listStudents = AppDb.getAppDb(getApplicationContext()).professorDAO().findProfessorByName(etName.getText().toString());
                if (listStudents.size() == 0){
                    Log.d("FIND", "Can't find student with that name");
                }
                else {
                    AppDb.getAppDb(getApplicationContext()).professorDAO().deleteProfessorByName(etName.getText().toString());
                    listStudents = AppDb.getAppDb(getApplicationContext()).professorDAO().findAllProfessor();
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(StudentsActivity.this,
                            android.R.layout.simple_list_item_1, show(listStudents));
                    listView.setAdapter(adapter);
                }



            }
        });
    }

    private <E> String[] show(List<E> array){
        return array.stream().map(E::toString).toArray(String[]::new);
    }

}