package com.example.sqliteorm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

public class ShowDatabaseActivity extends AppCompatActivity {
    private List<Contact> allContacts = new ArrayList<>();
    public ContactsListAdapter adapter;
    private ViewGroup parent;

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
        adapter = new ContactsListAdapter(allContacts, ShowDatabaseActivity.this);
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
                String middleName = extras.getString(Constants.MIDDLE_NAME_KEY);
                int age = extras.getInt(Constants.AGE_KEY);
                Contact contact = new Contact(lastName, firstName, middleName, age);
                AppDatabase.getINSTANCE(ShowDatabaseActivity.this).contactDao().insert(contact);
                AppDatabase.getINSTANCE(ShowDatabaseActivity.this).contactDao().getAlphabetizedContacts();
                getTableData();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.addContact(contact);
                    }
                });
            }
        });
        backgroundThread.start();
    }

    private void getTableData() {
        View itemView = adapter.getInflater().inflate(R.layout.recycler_view, parent, false);
        ContactsListAdapter.ContactViewHolder holder = adapter.new ContactViewHolder(itemView);
        String[] tableData = AppDatabase.getINSTANCE(ShowDatabaseActivity.this).contactDao().getAlphabetizedContacts().toString().split(" ");
        for (int i = 0; i < tableData.length; i++) {
            int test = i % 4;
            StringBuilder sb = new StringBuilder(tableData[i]);
            switch (test) {
                case 0:
                    holder.getContactLastNameView().setText(sb);
                case 1:
                    tableData[i] = sb.substring(0, 1);
                    holder.getContactFirstNameView().setText(tableData[i]);
                case 2:
                    tableData[i] = sb.substring(0, 1);
                    holder.getContactMiddleNameView().setText(tableData[i]);
                case 3:
                    holder.getContactAgeView().setText(sb);
            }
        }
    }
}