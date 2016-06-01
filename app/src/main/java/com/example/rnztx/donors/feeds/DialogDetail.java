package com.example.rnztx.donors.feeds;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rnztx.donors.R;
import com.example.rnztx.donors.feeds.chat.ChatActivity;
import com.example.rnztx.donors.models.CircleTransform;
import com.example.rnztx.donors.models.Requirement;
import com.example.rnztx.donors.models.UserInfo;
import com.example.rnztx.donors.models.utils.Constants;
import com.example.rnztx.donors.models.utils.Utilities;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
/**
 * Created by rnztx on 3/3/16.
 */
public class DialogDetail extends DialogFragment {
    private static final String LOG_TAG = DialogDetail.class.getSimpleName();
    @Bind(R.id.txtUserName) TextView txtUserName;
    @Bind(R.id.btnClose) ImageButton btnClose;
    @Bind(R.id.txtBloodGroup) TextView txtBloodGroup;
    @Bind(R.id.txtLocationName) TextView txtLocationName;
    @Bind(R.id.btnAccept) ImageButton btnAccept;
    @Bind(R.id.btnChat) ImageButton btnChat;
    @Bind(R.id.btnCallback) ImageButton btnCallBack;
    @Bind(R.id.imgAvatar) ImageView imgAvatar;
    UserInfo mUserInfo;
    Context mContext;
    private Requirement mObjReq = null;
    private final float disabledBtnAlpha = 0.2f;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.news_detail_dialog,container);
        Bundle bundle = getArguments();
        mObjReq = bundle.getParcelable(getString(R.string.feed_detail));
        ButterKnife.bind(this,rootView);
        String userId = null;

        if (mObjReq.getStatus()){
            userId = mObjReq.getDonorId();
            getDialog().setTitle(getString(R.string.dialog_title_donor));
            btnAccept.setEnabled(false);
            btnAccept.setAlpha(disabledBtnAlpha);
        }else{
            userId = mObjReq.getRecipientId();
            getDialog().setTitle(getString(R.string.dialog_title_recipient));
            btnChat.setEnabled(false);
            btnCallBack.setEnabled(false);
            btnChat.setAlpha(disabledBtnAlpha);
            btnCallBack.setAlpha(disabledBtnAlpha);
        }


        mUserInfo = Utilities.userInfoMap.get(userId);
        txtUserName.setText(mUserInfo.getUserDisplayName());

        if (!mUserInfo.getUserPhotoUrl().equals(Constants.PREF_DEFAULT_VALUE))
            Picasso.with(getActivity())
                    .load(mUserInfo.getUserPhotoUrl())
                    .transform(new CircleTransform())
                    .into(imgAvatar);

        txtLocationName.setText(mObjReq.getLocName());
        txtBloodGroup.setText(mObjReq.getBloodGroup());

        return rootView;
    }

    @OnClick(R.id.btnClose)
    void close(){
        dismiss();
    }

    @OnClick(R.id.btnAccept)
    void acceptRequest(){
        if(mObjReq != null){
            try {
                // disable accept button
                btnAccept.setEnabled(false);
                btnAccept.setAlpha(disabledBtnAlpha);

                Firebase.goOffline();
                Firebase.goOnline();

                Firebase fRoot = new Firebase(Constants.FIREBASE_URL_REQUIREMENTS);
                Firebase fChild = fRoot.child(mObjReq.getObjKey());

                Map<String,Object> mapObj = new HashMap<>();
                mapObj.put(Constants.FIREBASE_PROPERTY_STATUS,true);
                mapObj.put(Constants.FIREBASE_PROPERTY_DONOR_ID, Utilities.getUserId());
                fChild.updateChildren(mapObj, new Firebase.CompletionListener() {
                    @Override
                    public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                        close();
                        // If user Closes the Dialog box it may throw error
                        try {
                            if (firebaseError!=null)
                                Toast.makeText(mContext,"Failed",Toast.LENGTH_SHORT).show();
                            else{
                                Toast.makeText(mContext,"Accepted",Toast.LENGTH_LONG).show();
                            }
                        }catch (Exception e){
                            Log.e(LOG_TAG,e.toString());
                        }
                    }
                });
            }catch (Exception e){
                Log.e(LOG_TAG,e.toString());
            }
        }
    }
    @OnClick(R.id.btnChat)
    void startChat(){
        Intent intent = new Intent(getActivity(), ChatActivity.class);
        Bundle arguments = new Bundle();
        arguments.putString(Constants.EXTRA_TARGET_USERID,mObjReq.getDonorId());
        intent.putExtras(arguments);
        startActivity(intent);
    }

    @OnClick(R.id.btnCallback)
    void onCallBackPressed(){
        Intent intent = new Intent(Intent.ACTION_DIAL);

        intent.setData(Uri.parse("tel:"+mUserInfo.getMobileNumber()));
        startActivity(intent);
    }


    @Override
    public void onPause() {
        super.onPause();
        close();
    }
}
