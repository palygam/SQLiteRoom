
package com.example.sqliteorm;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText textInputLastName;
    private TextInputEditText textInputFirstName;
    private TextInputEditText textInputMiddleName;
    private TextInputEditText textInputAge;
    private String lastName;
    private String firstName;
    private String middleName;
    private String ageText;
    private int age;
    private TextInputLayout lastNameWrapper;
    private TextInputLayout ageWrapper;

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
            lastName = String.valueOf(textInputLastName.getText());
            firstName = textInputFirstName.getText().toString();
            middleName = textInputMiddleName.getText().toString();
            ageText = textInputAge.getText().toString();
            if (lastName.isEmpty()) {
                lastNameWrapper.setErrorEnabled(true);
                lastNameWrapper.setError("Enter your Last Name");
                return;
            }
            if (ageText.isEmpty()) {
                ageWrapper.setErrorEnabled(true);
                ageWrapper.setError("Enter your Age");
                return;
            }
            age = Integer.parseInt(ageText);
            startActivity(buildIntent());
        });
    }

    private Intent buildIntent() {
        Intent intent = new Intent(MainActivity.this, ShowDatabaseActivity.class);
        Bundle extras = new Bundle();
        extras.putString(Constants.LAST_NAME_KEY, lastName);
        extras.putString(Constants.FIRST_NAME_KEY, firstName);
        extras.putString(Constants.MIDDLE_NAME_KEY, middleName);
        extras.putInt(Constants.AGE_KEY, age);
        intent.putExtras(extras);
        return intent;
    }
}