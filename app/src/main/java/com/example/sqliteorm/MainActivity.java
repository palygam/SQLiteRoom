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
            if (textInputLastName.getText().toString().isEmpty()) {
                lastNameWrapper.setError("Enter your Last Name");
                return;
            }
            if (TextUtils.isEmpty(textInputAge.getText())) {
                ageWrapper.setError("Enter your Age");
                return;
            }
            age = Integer.parseInt(String.valueOf(textInputAge.getText()));
            startActivity(buildIntent());
        });
    }

    private Intent buildIntent() {
        Intent intent = new Intent(MainActivity.this, ShowDatabaseActivity.class);
        Bundle extras = new Bundle();
        extras.putString(Constants.LAST_NAME_KEY, textInputLastName.getText().toString());
        extras.putString(Constants.FIRST_NAME_KEY, textInputFirstName.getText().toString());
        extras.putString(Constants.MIDDLE_NAME_KEY, textInputMiddleName.getText().toString());
        extras.putInt(Constants.AGE_KEY, age);
        intent.putExtras(extras);
        return intent;
    }
}