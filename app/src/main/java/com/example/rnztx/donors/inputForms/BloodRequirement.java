package com.example.rnztx.donors.inputForms;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rnztx.donors.R;
import com.example.rnztx.donors.utils.Constants;
import com.firebase.client.Firebase;

/**
 * A simple {@link Fragment} subclass.
 */
public class BloodRequirement extends Fragment {

    private static final String LOG_TAG = BloodRequirement.class.getSimpleName();
    public BloodRequirement() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //initilise firebase
        Firebase.setAndroidContext(getContext());
        pushData();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blood_requrement, container, false);
    }

    private void pushData(){
        Log.e(LOG_TAG,Constants.FIREBASE_URL_USER);
        Firebase firebaseRef = new Firebase(Constants.FIREBASE_URL_USER);
        String userName = "Rohit";
        firebaseRef.setValue(userName);
    }
}
