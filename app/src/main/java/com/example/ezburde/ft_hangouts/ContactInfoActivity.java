package com.example.ezburde.ft_hangouts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ContactInfoActivity extends AppCompatActivity {
    private boolean     saveButton = false;
    private DbHelper    dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);

        Button  close;
        Button  saveDeleteButton;

        close = findViewById(R.id.closeButton);
        saveDeleteButton = findViewById(R.id.saveDeleteButton);
        dbHelper = new DbHelper(this);

        saveButton = getIntent().getBooleanExtra("saveButton", false);

        saveDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.insertContact(
                        ((EditText)findViewById(R.id.nameInput)).getText().toString(),
                        ((EditText)findViewById(R.id.phoneInput)).getText().toString(),
                        "photo"
                );
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
