package com.example.ezburde.ft_hangouts;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView                myList;
        ContactAdapter          adapter;
        DbHelper                dbHelper;

        dbHelper = new DbHelper(this);
        myList = findViewById(R.id.myList);
        adapter = new ContactAdapter(this, dbHelper.getContactsList());
        myList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("position", Long.toString(position));
                Log.i("id", Long.toString(id));
                return false;
            }
        });
        myList.setAdapter(adapter);

        FragmentContact    fragment;
        FragmentTransaction fragmentTransaction;

        fragment = new FragmentContact();
        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.myF, fragment);
        fragmentTransaction.commit();
    }
}
