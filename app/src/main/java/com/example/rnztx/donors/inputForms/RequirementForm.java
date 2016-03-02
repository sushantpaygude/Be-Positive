package com.example.rnztx.donors.inputForms;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rnztx.donors.R;
import com.example.rnztx.donors.models.Requirement;
import com.example.rnztx.donors.utils.Constants;
import com.firebase.client.Firebase;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class RequirementForm extends Fragment  {
    @Bind(R.id.btn_submit) Button btn_submit;

    @Bind(R.id.edt_location_pincode) EditText edt_location_pin;
    @Bind(R.id.edt_blood_group) EditText edt_blood_group;

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
        //Bind Fragment
        ButterKnife.bind(this,rootView);
        //initialize firebase
        Firebase.setAndroidContext(getContext());
        return rootView;
    }

    @OnClick(R.id.btn_submit)
    public void publishBloodRequirement(){
        int pinCode = 0;
        String bloodGroup = null;
        try {
            pinCode = Integer.parseInt(edt_location_pin.getText().toString());
            bloodGroup = edt_blood_group.getText().toString();
        }catch (Exception e){
            Log.e(LOG_TAG,e.toString());
            Toast.makeText(getContext(),"Invalid Input",Toast.LENGTH_SHORT).show();
            return;
        }
        if(pinCode<100000 || bloodGroup==null){
            Toast.makeText(getContext(),"Invalid Input",Toast.LENGTH_SHORT).show();
            return;
        }

        String userName = "rohit";

        Firebase rootFirebase= new Firebase(Constants.FIREBASE_URL_REQUIREMENTS);

        Firebase newRef = rootFirebase.push();
        String uniqueId = newRef.getKey();

        Requirement requirement = new Requirement(bloodGroup,pinCode,userName,uniqueId);
        Firebase child_requirement = rootFirebase.child(uniqueId);
        child_requirement.setValue(requirement);

        edt_blood_group.setText("");
        edt_location_pin.setText("");

        //focus
        edt_location_pin.setFocusable(true);
        Toast.makeText(getContext(),"Done",Toast.LENGTH_SHORT).show();
    }
}
