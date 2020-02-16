package com.example.sqliteorm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private final static String BUNDLE_KEY1 = "message1";
    private final static String BUNDLE_KEY2 = "message2";
    private final static String BUNDLE_KEY3 = "message3";
    private final static String BUNDLE_KEY4 = "message4";
    private EditText editTextLastName;
    private EditText editTextFirstName;
    private EditText editTextMiddleName;
    private EditText editTextAge;
    private String lastName;
    private String firstName;
    private String middleName;
    private String ageText;
    private int age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextLastName = findViewById(R.id.edit_text_last_name);
        editTextFirstName = findViewById(R.id.edit_text_first_name);
        editTextMiddleName = findViewById(R.id.edit_text_middle_name);
        editTextAge = findViewById(R.id.edit_text_age);
        addButtonMainActivity();
    }

    private void addButtonMainActivity() {
        final Button buttonSendData = findViewById(R.id.button_send);
        buttonSendData.setOnClickListener(view -> {
            lastName = editTextLastName.getText().toString();
            firstName = editTextFirstName.getText().toString();
            middleName = editTextMiddleName.getText().toString();
            ageText = editTextAge.getText().toString();
            age = 0;
            if (!TextUtils.isEmpty(ageText)) {
                age = Integer.parseInt(ageText);
            } else {
                editTextAge.setError("Enter your age");
            }
            if (TextUtils.isEmpty(lastName)) {
                editTextLastName.setError("Enter your last name");
                return;
            }
            startActivity(buildIntent());
        });
    }

    private Intent buildIntent() {
        Intent intent = new Intent(MainActivity.this, ShowDatabaseActivity.class);
        Bundle extras = new Bundle();
        extras.putString(BUNDLE_KEY1, lastName);
        extras.putString(BUNDLE_KEY2, firstName);
        extras.putString(BUNDLE_KEY3, middleName);
        extras.putInt(BUNDLE_KEY4, age);
        intent.putExtras(extras);
        return intent;
    }
}