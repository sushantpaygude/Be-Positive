package com.example.rnztx.donors.utils;

import com.example.rnztx.donors.BuildConfig;

/**
 * Created by rnztx on 2/2/16.
 */
public class Constants {

    public static final String FIREBASE_LOCATION_USERS = "users";
    /**
     * Constaants Firebase URL
     * */
    public static final String FIREBASE_URL = BuildConfig.UNIQUE_FIREBASE_ROOT_URL;

    public static final String FIREBASE_URL_USER = FIREBASE_URL + "/"+ FIREBASE_LOCATION_USERS;
}
