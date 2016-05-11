package com.example.rnztx.donors.feeds;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.rnztx.donors.R;
import com.example.rnztx.donors.models.KeyReference;
import com.example.rnztx.donors.models.Requirement;
import com.example.rnztx.donors.models.utils.Constants;
import com.example.rnztx.donors.models.utils.Utilities;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.shaded.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class RequirementForm extends Fragment  {
    @Bind(R.id.btn_submit) Button btn_submit;

    // with dropDown List
    @Bind(R.id.spinner_areaName) Spinner spinner_areaName;
    @Bind(R.id.spinner_bloodGroup) Spinner spinner_bloodGroup;

    final static String[] dataBloodGroup = new String[]{"O+", "O-","A+","A-","B+","B-","AB+","AB-"};
    final static String[] dataAreaName = new String[] {"Kondhwa","Market Yard","Viman Nagar","Vishrantwadi","Wakad","Bajirao Road"};
    final static String[] dataAreaPincode = new String[] {"411048","411037","411014","411015","411057","411002"};
    private static final String LOG_TAG = RequirementForm.class.getSimpleName();

    public RequirementForm() {
        // Required empty public constructor
    }
    public static RequirementForm newInstance(int position){
        RequirementForm fragment = new RequirementForm();
        Bundle args = new Bundle();
        args.putInt(Constants.ARG_SECTION_NUMBER,position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_blood_requrement, container, false);

        //initialize firebase
        Firebase.setAndroidContext(getContext());

        ArrayAdapter<String> adapterBloodGroup = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_dropdown_item,dataBloodGroup);

        ArrayAdapter<String> adapterAreaName = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_dropdown_item,dataAreaName);

        //Bind Fragment
        ButterKnife.bind(this,rootView);

        spinner_bloodGroup.setAdapter(adapterBloodGroup);
        spinner_areaName.setAdapter(adapterAreaName);

        return rootView;
    }

    @OnClick(R.id.btn_submit)
    public void publishBloodRequirement(View view){
        int pinCode = 0;
        String bloodGroup = null;
        String locName = null;
        try {
            //when Exception is thrown, spinner will also raise Error.
            int iLocation = spinner_areaName.getSelectedItemPosition();
            locName = dataAreaName[iLocation];
            pinCode = Integer.parseInt(dataAreaPincode[iLocation]);
            bloodGroup = spinner_bloodGroup.getSelectedItem().toString();
        }catch (Exception e){
            Log.e(LOG_TAG,e.toString());
            Toast.makeText(getContext(),"Invalid Input",Toast.LENGTH_SHORT).show();
            return;
        }

        if(pinCode==0 || pinCode<100000 || bloodGroup==null ){
            Toast.makeText(getContext(),"Invalid Input",Toast.LENGTH_SHORT).show();
            return;
        }else {
            try {
                String userId = Utilities.getUserId();
                KeyReference objKeyReference = null;

                Firebase.goOffline();
                Firebase.goOnline();
                Firebase fRoot = new Firebase(Constants.FIREBASE_URL);
                Firebase fChildRequirement = fRoot.child(Constants.FIREBASE_LOCATION_REQUIREMENTS);
                Firebase fChildReference = fRoot.child(Constants.FIREBASE_LOCATION_REQ_REFERENCES);

                String keyRequirement = fChildRequirement.push().getKey();
                String keyReference = fChildReference.push().getKey();
                Requirement objRequirement = new Requirement(bloodGroup,pinCode,locName,keyRequirement);
                objRequirement.setRecipientId(userId);
                objRequirement.setDonorId("Unknown");

                objKeyReference = new KeyReference(keyRequirement);

                // map POJO to hashmap
                Map<String,Object> mapRequirement = new ObjectMapper().convertValue(objRequirement,Map.class);
                Map<String, Object> mapReference = new ObjectMapper().convertValue(objKeyReference,Map.class);

                Map<String,Object> mapData = new HashMap<>();
                mapData.put(Constants.FIREBASE_LOCATION_REQUIREMENTS+"/"+keyRequirement,mapRequirement);
                mapData.put(Constants.FIREBASE_LOCATION_REQ_REFERENCES+"/"+keyReference,mapReference);

                fRoot.updateChildren(mapData, new Firebase.CompletionListener() {
                    @Override
                    public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                        if(firebaseError!=null)
                            Log.e(LOG_TAG,firebaseError.getMessage());
                        else
                            Toast.makeText(getActivity(),"Done",Toast.LENGTH_SHORT).show();
                    }
                });
            }
            catch (Exception e){
                Log.e(LOG_TAG,e.toString());
            }
//            createReference(bloodGroup,pinCode,uniqueId);
//            Log.e(LOG_TAG,"index: "+spinner_bloodGroup.getSelectedItemPosition());
        }
    }

    private String getReferenceURL(String bloodGroup,int pinCode){
        // base url + ref + blood group+ pincode
        String url =  Constants.FIREBASE_URL_REFERENCES;
//                "/"+bloodGroup+"/"+pinCode;
        Log.e(LOG_TAG,url);
        return url;
    }
}
