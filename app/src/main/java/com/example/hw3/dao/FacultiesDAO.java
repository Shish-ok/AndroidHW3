package com.example.hw3.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.hw3.entities.Faculties;

import java.util.List;

@Dao
public interface FacultiesDAO {

    @Insert
    void insert(Faculties faculties);

    @Query("SELECT * FROM faculties")
    List<Faculties> findAllLanguages();



    @Query("DELETE FROM faculties where name LIKE :name")
    void deleteLanguageByName(String name);
}