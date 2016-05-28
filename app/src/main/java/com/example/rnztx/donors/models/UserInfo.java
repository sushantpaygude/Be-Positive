package com.example.rnztx.donors.models;

import com.example.rnztx.donors.models.utils.Constants;

/**
 * Created by rnztx on 9/5/16.
 */
public class UserInfo {
    private String mobileNumber;
    private String bloodGroup;
    private String pinCode;
    private String address;

    private String userEmail;
    private String userDisplayName;
    private String userPhotoUrl;


    public UserInfo(String userEmail, String userDisplayName, String userPhotoUrl) {
        this.userEmail = userEmail;
        this.userDisplayName = userDisplayName;
        this.userPhotoUrl = userPhotoUrl;

        this.mobileNumber = Constants.PREF_DEFAULT_VALUE;
        this.bloodGroup = Constants.PREF_DEFAULT_VALUE;
        this.pinCode = Constants.PREF_DEFAULT_VALUE;
        this.address = Constants.PREF_DEFAULT_VALUE;
    }

    public UserInfo() {
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public String getPinCode() {
        return pinCode;
    }

    public String getAddress() {
        return address;
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

    @Override
    public String toString() {
        return "UserInfo{" +
                "mobileNumber='" + mobileNumber + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userDisplayName='" + userDisplayName + '\'' +
                ", userPhotoUrl='" + userPhotoUrl + '\'' +
                '}';
    }
}
