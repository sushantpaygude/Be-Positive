package com.example.rnztx.donors.models;

import java.util.Calendar;

/**
 * Created by rnztx on 29/2/16.
 */
public class Requirement {
    private String bloodGroup;
    private int pinCode;
    private long date;
    private String userId ;

    public Requirement(){ }

    public Requirement(String bloodGroup, int pinCode, String userId) {
        this.bloodGroup = bloodGroup;
        this.pinCode = pinCode;
        this.userId = userId;
        this.date = Calendar.getInstance().getTimeInMillis();
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public int getPinCode() {
        return pinCode;
    }

    public long getDate() {
        return date;
    }

    public String getUserId() {
        return userId;
    }
}
