package com.example.rnztx.donors.utils;

import com.example.rnztx.donors.BuildConfig;

/**
 * Created by rnztx on 2/2/16.
 */
public class Constants {

    public static final String FIREBASE_LOCATION_USERS = "users";
    public static final String FIREBASE_LOCATION_REQUIREMENTS = "requirements";

    public static final String FIREBASE_PROPERTY_PINCODE = "pincode";
    public static final String FIREBASE_PROPERTY_BLOOD_GROUP = "blood_group";
    /**
     * Constaants Firebase URL
     * */
    public static final String FIREBASE_URL = BuildConfig.UNIQUE_FIREBASE_ROOT_URL;

    public static final String FIREBASE_URL_REQUIREMENTS = FIREBASE_URL + "/"+FIREBASE_LOCATION_REQUIREMENTS;
}
