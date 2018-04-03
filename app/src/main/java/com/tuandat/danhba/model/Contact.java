package com.tuandat.danhba.model;

/**
 * Created by tuand on 3/25/2018.
 */

public class Contact {

    private boolean isMale;
    private String name;
    private String phone;


    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Contact(boolean isMale, String name, String phone) {
        this.isMale = isMale;
        this.name = name;
        this.phone = phone;
    }

    public Contact() {
    }
}
