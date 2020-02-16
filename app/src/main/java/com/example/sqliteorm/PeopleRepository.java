package com.example.sqliteorm;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PeopleRepository {
    private PersonDao personDao;
    private LiveData<List<Person>> allPeople;

    PeopleRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        personDao = db.personDao();
        allPeople = personDao.getAlphabetizedPeople();
    }

    LiveData<List<Person>> getAllPeople() {
        return allPeople;
    }

    void insert(Person person) {
        AppDatabase.executorService.execute(() -> personDao.insert(person));
    }
}