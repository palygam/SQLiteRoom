package com.example.sqliteorm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;


public class ShowDatabaseActivity extends AppCompatActivity {
    private final static String BUNDLE_KEY1 = "message1";
    private final static String BUNDLE_KEY2 = "message2";
    private final static String BUNDLE_KEY3 = "message3";
    private final static String BUNDLE_KEY4 = "message4";
    private PersonViewModel personViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_database);
        setupToolbar();
        initRecyclerView();
        Intent intent = getIntent();
        unpack(intent);
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            toolbar.setNavigationOnClickListener(v -> onBackPressed());
        }
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        final PeopleListAdapter adapter = new PeopleListAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        personViewModel = new ViewModelProvider(this).get(PersonViewModel.class);
        personViewModel.getAllPeople().observe(this, people -> adapter.setPeople(people));
    }

    private void unpack(Intent intent) {
        Bundle extras = intent.getExtras();
        String lastName = extras.getString(BUNDLE_KEY1);
        String firstName = extras.getString(BUNDLE_KEY2);
        String middleName = extras.getString(BUNDLE_KEY3);
        int age = extras.getInt(BUNDLE_KEY4);
        Person person = new Person(lastName, firstName, middleName, age);
        personViewModel.insert(person);
    }
}
