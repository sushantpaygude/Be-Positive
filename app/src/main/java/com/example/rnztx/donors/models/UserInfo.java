package com.example.rnztx.donors.models;

/**
 * Created by rnztx on 9/5/16.
 */
public class UserInfo {
    private String mobileNumber;
    private String userEmail;
    private String userDisplayName;
    private String userPhotoUrl;

    public UserInfo(String mobileNo, String userEmail, String userDisplayName, String userPhotoUrl) {
        this.mobileNumber = mobileNo;
        this.userEmail = userEmail;
        this.userDisplayName = userDisplayName;
        this.userPhotoUrl = userPhotoUrl;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserDisplayName() {
        return userDisplayName;
    }

    public String getUserPhotoUrl() {
        return userPhotoUrl;
    }
}
