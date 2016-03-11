package com.example.rnztx.donors.utils;

import com.example.rnztx.donors.BuildConfig;

/**
 * Created by rnztx on 2/2/16.
 */
public class Constants {
    public static final String ARG_SECTION_NUMBER = "section_number";

    public static final String FIREBASE_LOCATION_USERS = "users";
    public static final String FIREBASE_LOCATION_REQUIREMENTS = "blood_requirements";
    public static final String FIREBASE_LOCATION_REQ_REFERENCES = "requirement_references";

    public static final String FIREBASE_PROPERTY_PINCODE = "pinCode";
    public static final String FIREBASE_PROPERTY_BLOOD_GROUP = "bloodGroup";
    public static final String FIREBASE_PROPERTY_USER_ID = "userId";
    public static final String FIREBASE_PROPERTY_DATE = "date";

    /**
     * Constaants Firebase URL
     * */
    public static final String FIREBASE_URL = BuildConfig.UNIQUE_FIREBASE_ROOT_URL;

    public static final String FIREBASE_URL_REQUIREMENTS = FIREBASE_URL + "/"+FIREBASE_LOCATION_REQUIREMENTS;
    public static final String FIREBASE_URL_REFERENCES = FIREBASE_URL + "/"+ FIREBASE_LOCATION_REQ_REFERENCES ;

}
