package com.example.sqliteorm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

public class ShowDatabaseActivity extends AppCompatActivity {
    private ViewGroup parent;
    private List<Contact> contacts = new ArrayList<>();


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
        ContactListAdapter adapter = new ContactsListAdapter(ShowDatabaseActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }

    private void unpack(Intent intent) {
        final Handler handler = new Handler();
        Thread backgroundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Bundle extras = intent.getExtras();
                String lastName = extras.getString(Constants.LAST_NAME_KEY);
                String firstName = extras.getString(Constants.FIRST_NAME_KEY);
                firstName = firstName.substring(0, 1).toUpperCase();
                String middleName = extras.getString(Constants.MIDDLE_NAME_KEY);
                middleName = middleName.substring(0, 1).toUpperCase();
                int age = extras.getInt(Constants.AGE_KEY);
                Contact contact = new Contact(lastName, firstName, middleName, age);
                AppDatabase.getINSTANCE(ShowDatabaseActivity.this).contactDao().insert(contact);
                contacts = AppDatabase.getINSTANCE(ShowDatabaseActivity.this).contactDao().getAll();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.setContacts(contacts);
                    }
                });
            }
        });
        backgroundThread.start();
    }
}







