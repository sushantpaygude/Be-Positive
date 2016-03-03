package com.example.rnztx.donors.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Calendar;

/**
 * Created by rnztx on 29/2/16.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class Requirement {
    private String bloodGroup;
    private int pinCode;
    private long date;
    private String userId ;
    private boolean status = false;
    private String locName;

    public Requirement(){ }

    public Requirement(String bloodGroup, int pinCode, String userId, String locName) {
        this.bloodGroup = bloodGroup;
        this.pinCode = pinCode;
        this.userId = userId;
        this.date = Calendar.getInstance().getTimeInMillis();
        this.locName = locName;
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

    public boolean getStatus() {return status;}

    public String getLocName() {
        return locName;
    }

    @Override
    public String toString() {
        return getBloodGroup()+"---"+getLocName()+"---"+getPinCode()+"---"+getDate()+"---"+getUserId()+"---"+getStatus();
    }
}
