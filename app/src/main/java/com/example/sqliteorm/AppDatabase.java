
package com.example.sqliteorm;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Contact.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ContactDao contactDao();

    private static AppDatabase INSTANCE;

    public synchronized static AppDatabase getINSTANCE(Context context) {
        INSTANCE = getDatabase(context);
        return INSTANCE;
    }

    static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, "table_contacts")
                        .build();
                Log.d("LOG", "Getting the database instance");
            }
        }
        return INSTANCE;
    }
}