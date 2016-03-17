package com.example.rnztx.donors.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Calendar;

/**
 * Created by rnztx on 29/2/16.
 */
//@JsonIgnoreProperties(ignoreUnknown=true)
public class Requirement implements Parcelable{
    private String bloodGroup;
    private int pinCode;
    private long date;
    private String userId ;
    private boolean status = false;
    private String locName;
    private String objKey;
    public Requirement(){ }

    public String getObjKey() {
        return objKey;
    }

    public Requirement(String bloodGroup, int pinCode, String userId, String locName, String objKey) {
        this.bloodGroup = bloodGroup;
        this.pinCode = pinCode;
        this.userId = userId;
        this.date = Calendar.getInstance().getTimeInMillis();
        this.locName = locName;
        this.objKey = objKey;

    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(bloodGroup);
        parcel.writeInt(pinCode);
        parcel.writeString(userId);
        parcel.writeLong(date);
        parcel.writeString(locName);
        parcel.writeByte(((byte)(status?1:0)));
        parcel.writeString(objKey);
    }
    public Requirement(Parcel parcel){
        bloodGroup = parcel.readString();
        pinCode = parcel.readInt();
        userId = parcel.readString();
        date = parcel.readLong();
        locName = parcel.readString();
        status = parcel.readByte() != 0;
        objKey = parcel.readString();

    }
    public static final Parcelable.Creator<Requirement> CREATOR = new Parcelable.Creator<Requirement>(){
        @Override
        public Requirement createFromParcel(Parcel source) {
            return new Requirement(source);
        }

        public Requirement[] newArray(int size){
            return new Requirement[size];
        }
    };

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

    @Override
    public int describeContents() { return 0; }


}
