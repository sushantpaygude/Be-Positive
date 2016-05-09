package com.example.rnztx.donors.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

/**
 * Created by rnztx on 8/5/16.
 */
public class Utilities {

    private static final String LOG_TAG = Utilities.class.getSimpleName();
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
        editor.apply();
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
        Log.e(LOG_TAG,message+pref.getString(Constants.PREF_USER_EMAIL,"NA"));
    }
}
