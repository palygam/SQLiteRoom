package com.example.sqliteorm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class PersonViewModel extends AndroidViewModel {

    private PeopleRepository repository;
    private LiveData<List<Person>> allPeople;

    public PersonViewModel(@NonNull Application application) {
        super(application);
        repository = new PeopleRepository(application);
        allPeople = repository.getAllPeople();
    }


    LiveData<List<Person>> getAllPeople() {
        return allPeople;
    }

    public void insert(Person person) {
        repository.insert(person);
    }
}