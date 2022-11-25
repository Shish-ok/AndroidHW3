package com.example.hw3.entities;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "courses",
        foreignKeys = {
                @ForeignKey(entity = Student.class,
                        parentColumns = "id",
                        childColumns = "studentId",
                        onDelete = CASCADE
                ),
                @ForeignKey(entity = Faculties.class,
                        parentColumns = "id",
                        childColumns = "facultiesId",
                        onDelete = CASCADE)
        })
public class Course {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "studentId")
    public int studentId;
    @ColumnInfo(name = "facultiesId")
    public int facultiesId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getFacultiesId() {
        return facultiesId;
    }

    public void setFacultiesId(int facultiesId) {
        this.facultiesId = facultiesId;
    }

    @Override
    public String toString() {
        return
                "ID: " + id +
                        " NAME:" + name +
                        " STUDENTS_ID " + studentId +
                        " FACULTIES_ID " + facultiesId;
    }
}