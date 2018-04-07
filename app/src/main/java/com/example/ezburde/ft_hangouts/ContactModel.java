package com.example.ezburde.ft_hangouts;

public class ContactModel {
    private int     id;
    private String  name;
    private String  phone;
    private String  photo;

    public ContactModel(int id, String name, String phone, String photo) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getPhoto() {
        return photo;
    }
}
