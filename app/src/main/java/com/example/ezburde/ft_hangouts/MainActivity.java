package com.example.ezburde.ft_hangouts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView                myList;
        ContactCursorAdapter    adapter;
        DbHelper                dbHelper;

        dbHelper = new DbHelper(this);
        myList = findViewById(R.id.myList);
        adapter = new ContactCursorAdapter(this, dbHelper.getCursor());
        myList.setAdapter(adapter);
    }
}
