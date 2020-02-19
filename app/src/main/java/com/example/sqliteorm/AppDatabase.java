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


    public static AppDatabase getINSTANCE() {
        return INSTANCE;
    }


    static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "table_contacts")
                            .addCallback(roomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }


    private static RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            ContactDao dao = INSTANCE.contactDao();
            dao.getAlphabetizedContacts();
        }

        ;
    };

}
