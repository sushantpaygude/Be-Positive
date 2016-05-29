package com.example.rnztx.donors.models.utils;

import com.example.rnztx.donors.BuildConfig;

/**
 * Created by rnztx on 2/2/16.
 */
public class Constants {
    public static final String EXTRA_TARGET_USERID = "target_user_id";
//    Shreed Preference Keys
    public static final String PREF_DEFAULT_VALUE = "default.NA.value";
    public static final String PREF_USER_NAME = "user_name";
    public static final String PREF_USER_EMAIL = "user_email";
    public static final String PREF_USER_AVATAR = "user_avatar";
    public static final String PREF_USER_MOBILE = "user_mobile";
    public static final String PREF_USER_ADDRESS= "user_address";
    public static final String PREF_USER_BLOODGROUP = "user_bloodgroup";
    public static final String PREF_USER_PINCODE = "user_pincode";

    public static final String PREF_KEYS = "com.example.rnztx.donors.PREF_KEYS";
    public static final String ARG_SECTION_NUMBER = "section_number";

    public static final String FIREBASE_LOCATION_USERS = "users";
    public static final String FIREBASE_LOCATION_REQUIREMENTS = "blood_requirements";
    public static final String FIREBASE_LOCATION_REQ_REFERENCES = "requirement_references";
    public static final String FIREBASE_LOCATION_CHATMESSAGES = "chat_messages";

    public static final String FIREBASE_PROPERTY_PINCODE = "pinCode";
    public static final String FIREBASE_PROPERTY_BLOOD_GROUP = "bloodGroup";
    public static final String FIREBASE_PROPERTY_DONOR_ID = "donorId";
    public static final String FIREBASE_PROPERTY_DATE = "date";
    public static final String FIREBASE_PROPERTY_STATUS = "status";
    public static final String FIREBASE_PROPERTY_CHATMESSAGE_TIMESTAMP = "timeStamp";

    /**
     * Constaants Firebase URL
     * */
    public static final String FIREBASE_URL = BuildConfig.UNIQUE_FIREBASE_ROOT_URL;

    public static final String FIREBASE_URL_REQUIREMENTS = FIREBASE_URL + "/"+FIREBASE_LOCATION_REQUIREMENTS;
    public static final String FIREBASE_URL_REFERENCES = FIREBASE_URL + "/"+ FIREBASE_LOCATION_REQ_REFERENCES ;
    public static final String FIREBASE_URL_USERS = FIREBASE_URL + "/"+ FIREBASE_LOCATION_USERS;
    public static final String FIREBASE_URL_MESSAGE_LIST = FIREBASE_URL + "/" + FIREBASE_LOCATION_CHATMESSAGES;
}
