package com.example.ezburde.ft_hangouts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class ContactInfoActivity extends AppCompatActivity {
    private ContactModel    contactModel;
    private DbHelper        dbHelper;
    private LinearLayout    mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);

        Button  saveButton;
        Button  closeButton;

        saveButton = new Button(this);
        closeButton = new Button(this);
        mainLayout = findViewById(R.id.mainLayout);
        dbHelper = new DbHelper(this);

        this.contactModel = (ContactModel) getIntent().getSerializableExtra("contactModel");

        saveButton.setText(R.string.save);
        closeButton.setText(R.string.close);
        mainLayout.addView(saveButton);
        mainLayout.addView(closeButton);

        if (this.contactModel != null)
            setExistingContact();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (contactModel == null) {
                    dbHelper.insertContact(
                            ((EditText) findViewById(R.id.nameInput)).getText().toString(),
                            ((EditText) findViewById(R.id.phoneInput)).getText().toString(),
                            "photo"
                    );
                } else {
                    dbHelper.updateContact(contactModel.getId(),
                            ((EditText) findViewById(R.id.nameInput)).getText().toString(),
                            ((EditText) findViewById(R.id.phoneInput)).getText().toString(),
                            "photo"
                            );
                }
                finish();
            }
        });

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setExistingContact() {
        Button          delete;

        ((EditText) findViewById(R.id.nameInput)).setText(this.contactModel.getName());
        ((EditText) findViewById(R.id.phoneInput)).setText(this.contactModel.getPhone());

        delete = new Button(this);
        delete.setText(R.string.delete);
        mainLayout.addView(delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deleteContact(contactModel.getId());
                finish();
            }
        });

    }
}
