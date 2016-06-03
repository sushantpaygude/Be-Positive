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
    public static final String PREF_USER_ADDRESS = "user_address";
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
     */
    public static final String FIREBASE_URL = BuildConfig.UNIQUE_FIREBASE_ROOT_URL;

    public static final String FIREBASE_URL_REQUIREMENTS = FIREBASE_URL + "/" + FIREBASE_LOCATION_REQUIREMENTS;
    public static final String FIREBASE_URL_REFERENCES = FIREBASE_URL + "/" + FIREBASE_LOCATION_REQ_REFERENCES;
    public static final String FIREBASE_URL_USERS = FIREBASE_URL + "/" + FIREBASE_LOCATION_USERS;
    public static final String FIREBASE_URL_MESSAGE_LIST = FIREBASE_URL + "/" + FIREBASE_LOCATION_CHATMESSAGES;
    public static String[] BLOOD_GROUP_LIST = new String[]{"O+", "O-","A+","A-","B+","B-","AB+","AB-"};
    public static final String[] LOCATION_LIST = {"Pune H.O", "Pune City H.O", "Khadaki",
            "Deccan Gymkhana",
            "Shivajinagar H.O",
            "Yerawada",
            "Ganeshkind",
            "N.C.L",
            "Parvati",
            "S.S.C Board",
            "Kasba Peth",
            "Dapodi",
            "Hadapsar",
            "Dunkirk Line",
            "Dighi Camp",
            "Model Colony",
            "Pimpri Colony",
            "Pimpri Penicillin Factory",
            "Chinchwad E.",
            "0 Range Hill",
            "Armament",
            "S.R.P.F",
            "N.D.A",
            "Khadakwasla",
            "I.A.T Pune",
            "Bhosari",
            "Aundh",
            "Hadapsar",
            "Kothrud",
            "S.P College",
            "C.M.E",
            "I.A.F Station",
            "Chinchwad Gaon",
            "Kasarwadi",
            "Akurdi",
            "Mundhwa",
            "Market Yard",
            "Ex. Service Man Colony",
            "Bhosari Gaon",
            "Wanawadi",
            "Wadgaon Budruk",
            "Swargate",
            "Dhankawadi",
            "Pimpri Chinchwad",
            "Pashan",
            "Katraj",
            "Lohgaon",
            "N.I.B.M",
            "Anandnagar",
            "Navasahyadri",
            "Shivaji HSG Society"};


    public static final String[] PINCODE_LIST = {"411001",
            "411002",
            "411003",
            "411004",
            "411005",
            "411006",
            "411007",
            "411008",
            "411009",
            "411010",
            "411011",
            "411012",
            "411013",
            "411014",
            "411015",
            "411016",
            "411017",
            "411018",
            "411019",
            "411020",
            "411021",
            "411022",
            "411023",
            "411024",
            "411025",
            "411026",
            "411027",
            "411028",
            "411029",
            "411030",
            "411031",
            "411032",
            "411033",
            "411034",
            "411035",
            "411036",
            "411037",
            "411038",
            "411039",
            "411040",
            "411041",
            "411042",
            "411043",
            "411044",
            "411045",
            "411046",
            "411047",
            "411048",
            "411051",
            "411052",
            "411053"
    };

}