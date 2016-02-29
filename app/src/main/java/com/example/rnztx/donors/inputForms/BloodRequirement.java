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

/**
 * A simple {@link Fragment} subclass.
 */
public class BloodRequirement extends Fragment {

    private static final String LOG_TAG = BloodRequirement.class.getSimpleName();
    public BloodRequirement() {
        // Required empty public constructor
    }
    public static BloodRequirement newInstance(int position){
        BloodRequirement fragment = new BloodRequirement();
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

        //initilise firebase
        Firebase.setAndroidContext(getContext());

        Button btn_submit = (Button) rootView.findViewById(R.id.btn_submit);

//        final EditText edt_uniqueId = (EditText)rootView.findViewById(R.id.edt_unique_id);
        final EditText edt_location_pin = (EditText)rootView.findViewById(R.id.edt_location_pincode);
        final EditText edt_blood_group = (EditText)rootView.findViewById(R.id.edt_blood_group);

        // set on click listers
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String id = edt_uniqueId.getText().toString();
                int pinCode = Integer.parseInt(edt_location_pin.getText().toString());
                String bloodGroup = edt_blood_group.getText().toString();
                String userName = "rohit";

                Requirement requirement = new Requirement(bloodGroup,pinCode,userName);
                pushData(requirement);

                edt_blood_group.setText("");
                edt_location_pin.setText("");

                //focus
                edt_location_pin.setFocusable(true);
                Toast.makeText(getContext(),"Done",Toast.LENGTH_SHORT);
            }
        });
        return rootView;
    }

    public void pushData(Requirement requirement){
        Log.e(LOG_TAG,"Data inserted");

        Firebase rootFirebase= new Firebase(Constants.FIREBASE_URL_REQUIREMENTS);

        Firebase newRef = rootFirebase.push();
        String uniqueId = newRef.getKey();

        Firebase child_requirement = rootFirebase.child(uniqueId);
        child_requirement.setValue(requirement);

    }
}
