package com.example.hw3;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.hw3.dao.CoursesDAO;
import com.example.hw3.dao.FacultiesDAO;
import com.example.hw3.dao.StudentsDAO;
import com.example.hw3.entities.Course;
import com.example.hw3.entities.Faculties;
import com.example.hw3.entities.Student;

@Database(entities = {Student.class, Course.class, Faculties.class}, version = 3)
public abstract class AppDb extends RoomDatabase {

    private static AppDb INSTANCE;

    public abstract StudentsDAO professorDAO();

    public abstract CoursesDAO courseDAO();

    public abstract FacultiesDAO languagesDAO();


    public static AppDb getAppDb(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDb.class, "db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}