package com.example.rnztx.donors.feeds.intro.auth;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rnztx.donors.MainActivity;
import com.example.rnztx.donors.R;
import com.example.rnztx.donors.feeds.intro.RegisterActivity;
import com.example.rnztx.donors.models.UserInfo;
import com.example.rnztx.donors.models.utils.Constants;
import com.example.rnztx.donors.models.utils.Utilities;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SigninActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{
    private GoogleApiClient mGoogleApiClient;
    private static final String LOG_TAG = SigninActivity.class.getSimpleName();
    private static final int RC_SIGN_IN = 9001;
    ImageView imgGoogleAvatar;
    SignInButton btnSignIn;
    @Bind(R.id.btn_sign_out) Button btnSignOut;
    @Bind(R.id.txt_project_title) TextView txtProjectTitle;
    //1. Initialisation
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        btnSignIn = (SignInButton) findViewById(R.id.sign_in_button);

        ButterKnife.bind(this);
        Firebase.setAndroidContext(this);

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .build();

        // Build a GoogleApiClient with access to the Google Sign-In API and the
        // options specified by gso.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        try {
            mGoogleApiClient.connect();
            if (Utilities.isUserLogged(this))
                signIn();

        }
        catch (Exception e){
            Log.e(LOG_TAG,e.getMessage());
        }
        Typeface typeface=Typeface.createFromAsset(getAssets(), "fonts/SimplyRoundedBold.ttf");
        txtProjectTitle.setTypeface(typeface);

        btnSignIn.setEnabled(true);
    }

    // opens popup menu for account selection
    public void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    // when user selects account
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    // handling signin result
    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount userAccount = result.getSignInAccount();

//            only for debugging
            imgGoogleAvatar = (ImageView)findViewById(R.id.img_google_plus_avatar);
//            Picasso.with(this).load(userAccount.getPhotoUrl()).into(imgGoogleAvatar);

            // store User data
            Utilities.storeUserCredential(userAccount,this);

            // update user information on Firebase
            uploadUserInfo();

            //Store all Users Data Locally
            storeUsersInfo();

            // start Main Activity

            Intent intent;
            if (Utilities.getUserMobileNumber().equals(Constants.PREF_DEFAULT_VALUE)){
                intent = new Intent(this, RegisterActivity.class);
            }
            else {
                intent = new Intent(this, MainActivity.class);
            }
            startActivity(intent);
        } else {
            // Signed out, show unauthenticated UI.
            Log.e(LOG_TAG,"Failed Sign in : "+result.getStatus());
        }
    }

    private void uploadUserInfo(){
//        Firebase.goOffline();
//        Firebase.goOnline();
        Firebase fRoot = new Firebase(Constants.FIREBASE_URL);
        Firebase fUsersLocation = fRoot.child(Constants.FIREBASE_LOCATION_USERS);

        UserInfo currentUser = new UserInfo(
                Utilities.getUserEmail(),
                Utilities.getUserDisplayName(),
                Utilities.getUserPhotoUrl());

        if (Utilities.getUserMobileNumber().equals(Constants.PREF_DEFAULT_VALUE))
            fUsersLocation
                .child(Utilities.getUserId())
                .setValue(currentUser, new Firebase.CompletionListener() {
                    @Override
                    public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                        if (firebaseError!=null)
                            uploadUserInfo();
                        // recursion Be Careful !!!
                    }
                });

    }
    private void storeUsersInfo(){
        // locally store User Data
        Firebase usersRoot = new Firebase(Constants.FIREBASE_URL_USERS);
        usersRoot.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                UserInfo userInfo = dataSnapshot.getValue(UserInfo.class);
                Utilities.userInfoMap.put(dataSnapshot.getKey(),userInfo);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                UserInfo userInfo = dataSnapshot.getValue(UserInfo.class);
                Utilities.userInfoMap.put(dataSnapshot.getKey(),userInfo);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }

    @OnClick(R.id.btn_sign_out)
    public void signOut(){
        Utilities.signOut(this,mGoogleApiClient);
        if (imgGoogleAvatar!=null)
            imgGoogleAvatar.setImageBitmap(null);
    }


    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.e(LOG_TAG,connectionResult.getErrorMessage());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mGoogleApiClient.disconnect();
    }
}
