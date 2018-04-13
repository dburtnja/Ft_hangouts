package com.example.ezburde.ft_hangouts;

import android.content.Context;
import android.graphics.Movie;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ContactAdapter extends ArrayAdapter {
    private Context             context;
    private List<ContactModel>  contacts;

    public ContactAdapter(@NonNull Context context, @NonNull List<ContactModel> contacts) {
        super(context, 0, contacts);
        this.contacts = contacts;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View            view;
        TextView        name;
        TextView        phone;
        ContactModel    contactModel;

        view = convertView;
        if (view == null)
            view = LayoutInflater.from(this.context).inflate(R.layout.contact_item, parent, false);
        contactModel = this.contacts.get(position);
        name = view.findViewById(R.id.contactName);
        phone = view.findViewById(R.id.contactPhone);

        name.setText(contactModel.getName());
        phone.setText(contactModel.getPhone());
        return view;
    }
}
