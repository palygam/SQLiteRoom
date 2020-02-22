package com.example.sqliteorm;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ContactDao {

    @Query("SELECT * FROM table_contacts")
    List<Contact> getAll();

    @Query("SELECT first_name FROM table_contacts")
    List<String> getFirstNames();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Contact contact);

    @Query("DELETE FROM table_contacts")
    void deleteAll();
}