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

    @Query("SELECT id, last_name , substr(first_name,1,1) || '.' AS first_name, substr(middle_name,1,1) || '.' AS middle_name, age from table_contacts ORDER BY last_name ASC")
    List<Contact> getAlphabetizedContacts();

    @Query("SELECT first_name FROM table_contacts")
    List<String> getFirstNames();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Contact contact);

    @Query("DELETE FROM table_contacts")
    void deleteAll();
}