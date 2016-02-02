package com.example.rnztx.donors;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.rnztx.donors.inputForms.BloodRequirement;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if(findViewById(R.id.mainActivity_fragmentContainer)!=null){
            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if(savedInstanceState != null)
                return;

            // create a fragment to be placed in main Activity
            BloodRequirement mainFragment = new BloodRequirement();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            mainFragment.setArguments(getIntent().getExtras());

            //Add fragment to ActivityMain
            getSupportFragmentManager().beginTransaction().add(R.id.mainActivity_fragmentContainer,mainFragment).commit();

        }
    }
}
