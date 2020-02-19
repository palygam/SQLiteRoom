package com.example.sqliteorm;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.List;

@Database(entities = {Contact.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ContactDao contactDao();

    private List<Contact> allContacts;

    List<Contact> getAllContacts() {
        return allContacts;
    }

    private static AppDatabase INSTANCE;

    public synchronized static AppDatabase getINSTANCE(Context context) {
        if (INSTANCE == null) {
            INSTANCE = getDatabase(context);
        }
        return INSTANCE;
    }

    static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "table_contacts")
                            .addCallback(new Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    getINSTANCE(context).contactDao().insert(new Contact("Petrov", "Petr", "Petrovich", 32));
                                }

                                ;
                            })
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

