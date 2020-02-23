package com.example.sqliteorm;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class MainActivity extends AppCompatActivity {
    private TextInputEditText textInputLastName;
    private TextInputEditText textInputFirstName;
    private TextInputEditText textInputMiddleName;
    private TextInputEditText textInputAge;
    private TextInputLayout lastNameWrapper;
    private TextInputLayout ageWrapper;
    private String lastName;
    private String firstName;
    private String middleName;
    private int age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lastNameWrapper = findViewById(R.id.last_name_wrapper);
        ageWrapper = findViewById(R.id.age_wrapper);
        textInputLastName = findViewById(R.id.text_input_last_name);
        textInputFirstName = findViewById(R.id.text_input_first_name);
        textInputMiddleName = findViewById(R.id.text_input_middle_name);
        textInputAge = findViewById(R.id.text_input_age);
        addButtonMainActivity();
    }

    private void addButtonMainActivity() {
        final Button buttonSendData = findViewById(R.id.button_send);
        buttonSendData.setOnClickListener(view -> {
            lastName = textInputLastName.getText().toString();
            firstName = textInputFirstName.getText().toString().substring(0, 1).toUpperCase();
            middleName = textInputMiddleName.getText().toString().substring(0, 1).toUpperCase();
            if (lastName.isEmpty()) {
                lastNameWrapper.setError("Enter your Last Name");
                return;
            }
            if (TextUtils.isEmpty(textInputAge.getText())) {
                ageWrapper.setError("Enter your Age");
                return;
            }
            age = Integer.parseInt(String.valueOf(textInputAge.getText()));
            insertData();
            startActivity(new Intent(MainActivity.this, ShowDatabaseActivity.class));
        });
    }

    private void insertData() {
        Thread backgroundThread = new Thread(() -> {
            Contact contact = new Contact(lastName, firstName, middleName, age);
            AppDatabase.getINSTANCE(MainActivity.this).contactDao().insert(contact);
        });
        backgroundThread.start();
    }
}
