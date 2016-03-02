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
    private String requirementId ;
    private boolean status = false;

    public Requirement(){ }

    public Requirement(String bloodGroup, int pinCode, String userId, String uniqueId) {
        this.bloodGroup = bloodGroup;
        this.pinCode = pinCode;
        this.userId = userId;
        this.date = Calendar.getInstance().getTimeInMillis();
        this.requirementId = uniqueId;
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

    public String getRequirementId() {
        return requirementId;
    }

    public boolean isStatus() {
        return status;
    }
}
