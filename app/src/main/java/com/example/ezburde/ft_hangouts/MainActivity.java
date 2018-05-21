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

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DbHelper            dbHelper;
    private ListView            myList;
    private List<ContactModel>  contactModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.dbHelper = new DbHelper(this);
        this.myList = findViewById(R.id.myList);
        prepareListView(this.myList);

        showListFromDB(this.dbHelper, this.myList);

    }

    private void openContact(int id) {
        Intent          intent;
        ContactModel    contactModel;

        intent = new Intent(this, ContactInfoActivity.class);
        if (id != -1) {
            contactModel = this.contactModels.get(id);
            intent.putExtra("contactModel", contactModel);
        }
        startActivity(intent);
    }

    private void prepareListView(ListView listView) {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                openContact(position);
                Log.i("position", Long.toString(position));
                Log.i("id", Long.toString(id));
                return false;
            }
        });
    }

    private void showListFromDB(DbHelper dbHelper, ListView view) {
        ContactAdapter          adapter;

        this.contactModels = dbHelper.getContactsList();
        adapter = new ContactAdapter(this, this.contactModels);
        view.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.add_contact:
                openContact(-1);
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
