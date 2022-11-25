package com.example.hw3.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.hw3.entities.Course;

import java.util.List;

@Dao
public interface CoursesDAO {

    @Insert
    void insert(Course course);

    @Query("SELECT * FROM courses")
    List<Course> findAllCourses();

    @Query("SELECT * FROM courses WHERE studentId=:studentId")
    List<Course> findCoursesForProfessor(int studentId);

    @Query("SELECT courses.id, courses.name,courses.studentId,courses.facultiesId  FROM courses,Faculties WHERE courses.facultiesId = Faculties.id AND Faculties.name=:name")
    List<Course> findCoursesForFaculties(String name);

    @Query("UPDATE courses SET facultiesId = :facultiesId, studentId= :studentId WHERE name =:name")
    void updateCourseByID(int facultiesId,int studentId, String name);

    @Query("DELETE FROM courses WHERE name=:name")
    void deleteCourseByName(String name);

}