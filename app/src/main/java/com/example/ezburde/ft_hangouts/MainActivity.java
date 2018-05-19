package com.example.ezburde.ft_hangouts;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private DbHelper    dbHelper;
    private ListView    myList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DbHelper(this);
        myList = findViewById(R.id.myList);
        prepareListView(myList);

        showListFromDB(dbHelper, myList);

    }

    private void prepareListView(ListView listView) {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("position", Long.toString(position));
                Log.i("id", Long.toString(id));
                return false;
            }
        });
    }

    private void showListFromDB(DbHelper dbHelper, ListView view) {
        ContactAdapter          adapter;

        adapter = new ContactAdapter(this, dbHelper.getContactsList());
        view.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent  intent;

        switch (item.getItemId()) {
            case R.id.add_contact:
                intent = new Intent(this, ContactInfoActivity.class);
                intent.putExtra("saveButton", true);
                startActivity(intent);
                Log.i("button", "add contact");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        showListFromDB(dbHelper, myList);
    }
}
