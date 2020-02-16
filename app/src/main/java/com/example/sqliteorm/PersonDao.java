package com.example.sqliteorm;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PersonDao {

    @Query("SELECT * FROM table_people")
    LiveData<List<Person>> getAll();


    @Query("SELECT id, last_name , substr(first_name,1,1) || '.' AS first_name, substr(middle_name,1,1) || '.' AS middle_name, age from table_people ORDER BY last_name ASC")
    LiveData<List<Person>> getAlphabetizedPeople();


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Person person);

    @Query("DELETE FROM table_people")
    void deleteAll();
}