package com.example.rnztx.donors.models.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.rnztx.donors.models.UserInfo;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by rnztx on 8/5/16.
 */
public class Utilities {

    private static final String LOG_TAG = Utilities.class.getSimpleName();
    private static SharedPreferences mPrefs;
    public static Map<String, UserInfo> userInfoMap = new HashMap<>();
    public static Set<String> userChatListSet = new HashSet<>();

    public static void storeUserCredential(GoogleSignInAccount account, Activity activity){

        SharedPreferences sharedPref = activity.getSharedPreferences(Constants.PREF_KEYS,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        if (account.getPhotoUrl()!=null)
            editor.putString(Constants.PREF_USER_AVATAR,account.getPhotoUrl().toString());
        if (account.getEmail()!=null)
            editor.putString(Constants.PREF_USER_EMAIL,account.getEmail());
        if (account.getDisplayName()!=null)
            editor.putString(Constants.PREF_USER_NAME,account.getDisplayName());
        editor.apply();
        mPrefs = sharedPref;
    }

    public static void storeUserInfo(UserInfo userInfo){
        SharedPreferences.Editor editor = mPrefs.edit();
        if (userInfo.getMobileNumber()!=null){
            editor.putString(Constants.PREF_USER_MOBILE,userInfo.getMobileNumber());
        }
        if (userInfo.getAddress()!=null)
            editor.putString(Constants.PREF_USER_ADDRESS,userInfo.getAddress());
        if (userInfo.getBloodGroup()!=null)
            editor.putString(Constants.PREF_USER_BLOODGROUP,userInfo.getBloodGroup());
        if (userInfo.getPinCode()!=null)
            editor.putString(Constants.PREF_USER_PINCODE,userInfo.getPinCode());

        editor.apply();
    }

    public static boolean isUserLogged(Activity activity){
        SharedPreferences sharedPreferences = activity.getSharedPreferences(Constants.PREF_KEYS,Context.MODE_PRIVATE);
        return sharedPreferences.contains(Constants.PREF_USER_EMAIL);
    }

    private static void deleteUserCredentials(Activity activity){
        SharedPreferences pref = activity.getSharedPreferences(Constants.PREF_KEYS,Context.MODE_PRIVATE);
        SharedPreferences.Editor  editor = pref.edit();
        if (pref.contains(Constants.PREF_USER_AVATAR))
            editor.remove(Constants.PREF_USER_AVATAR);
        if (pref.contains(Constants.PREF_USER_EMAIL))
            editor.remove(Constants.PREF_USER_EMAIL);
        if (pref.contains(Constants.PREF_USER_NAME))
            editor.remove(Constants.PREF_USER_NAME);
        if (pref.contains(Constants.PREF_USER_MOBILE))
            editor.remove(Constants.PREF_USER_MOBILE);
        if (pref.contains(Constants.PREF_USER_ADDRESS))
            editor.remove(Constants.PREF_USER_ADDRESS);
        if (pref.contains(Constants.PREF_USER_PINCODE))
            editor.remove(Constants.PREF_USER_PINCODE);
        if (pref.contains(Constants.PREF_USER_BLOODGROUP))
            editor.remove(Constants.PREF_USER_BLOODGROUP);
        editor.apply();
    }

    public static String getUserId(){
        String email = getUserEmail();
        return String.valueOf(email.hashCode());
    }

    public static String getUserEmail(){
        return mPrefs.getString(Constants.PREF_USER_EMAIL,Constants.PREF_DEFAULT_VALUE);
    }

    public static String getUserDisplayName(){
        return mPrefs.getString(Constants.PREF_USER_NAME,Constants.PREF_DEFAULT_VALUE);
    }

    public static String getUserPhotoUrl(){
        return mPrefs.getString(Constants.PREF_USER_AVATAR,Constants.PREF_DEFAULT_VALUE);
    }

    public static String getUserMobileNumber(){
        return mPrefs.getString(Constants.PREF_USER_MOBILE,Constants.PREF_DEFAULT_VALUE);
    }
    public static void signOut(Activity activity, GoogleApiClient mGoogleApiClient) {
        try {
            Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                    new ResultCallback<Status>() {
                        @Override
                        public void onResult(Status status) {

                        }
                    });

            deleteUserCredentials(activity);
        }catch (Exception e){
            Log.e(LOG_TAG,e.toString());
        }
    }

    public static void printUserData(Activity activity, String message){
        SharedPreferences pref = activity.getSharedPreferences(Constants.PREF_KEYS,Context.MODE_PRIVATE);
        Log.e(LOG_TAG,message+pref.getString(Constants.PREF_USER_EMAIL,Constants.PREF_DEFAULT_VALUE));
    }

    public static String getChatId(String targetUserId){
        String loggedUserId = getUserId();
        int result = targetUserId.compareTo(loggedUserId);
        if ( result <= 0){
            return targetUserId.concat("_").concat(loggedUserId);
        }
        else
            return loggedUserId.concat("_").concat(targetUserId);
    }

}
