package com.example.hw3.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.hw3.entities.Student;

import java.util.List;

@Dao
public interface StudentsDAO {
    @Insert
    void insertProfessor(Student student);

    @Query("SELECT * FROM students")
    List<Student> findAllProfessor();

    @Query("SELECT * FROM students where name LIKE :name")
    List<Student> findProfessorByName(String name);

    @Query("DELETE FROM students where name LIKE :name")
    void deleteProfessorByName(String name);

}