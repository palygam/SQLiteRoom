package com.example.sqliteorm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import java.util.ArrayList;
import java.util.List;


public class ShowDatabaseActivity extends AppCompatActivity {
    private List<Contact> allContacts;
    private ContactsListAdapter adapter;
    private ContactDao contactDao;


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
        final ContactsListAdapter adapter = new ContactsListAdapter(new ArrayList<>());
        adapter.notifyDataSetChanged();
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }

    private void unpack(Intent intent) {
        Bundle extras = intent.getExtras();
        String lastName = extras.getString(Constants.LAST_NAME_KEY);
        String firstName = extras.getString(Constants.FIRST_NAME_KEY);
        String middleName = extras.getString(Constants.MIDDLE_NAME_KEY);
        int age = extras.getInt(Constants.AGE_KEY);
        Contact contact = new Contact(lastName, firstName, middleName, age);
        final Handler handler = new Handler();
        Thread backgroundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase.getINSTANCE(ShowDatabaseActivity.this).contactDao().insert(contact);
                AppDatabase.getINSTANCE(ShowDatabaseActivity.this).contactDao().getAlphabetizedContacts();
                handler.post(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        });
        backgroundThread.start();
    }
}
