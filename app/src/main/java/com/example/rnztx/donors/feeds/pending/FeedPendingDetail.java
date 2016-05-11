package com.example.rnztx.donors.feeds.pending;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rnztx.donors.R;
import com.example.rnztx.donors.feeds.chat.ChatActivity;
import com.example.rnztx.donors.models.Requirement;
import com.example.rnztx.donors.utils.Constants;
import com.example.rnztx.donors.utils.Utilities;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by rnztx on 3/3/16.
 */
public class FeedPendingDetail extends DialogFragment {
    private static final String LOG_TAG = FeedPendingDetail.class.getSimpleName();
    @Bind(R.id.txtUserName) TextView txtUserName;
    @Bind(R.id.btnClose) ImageButton btnClose;
    @Bind(R.id.txtBloodGroup) TextView txtBloodGroup;
    @Bind(R.id.txtLocationName) TextView txtLocationName;
    @Bind(R.id.btnAccept) ImageButton btnAccept;
    @Bind(R.id.btnChat) ImageButton btnChat;

    private Requirement mObjReq = null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.news_detail_dialog,container);
        Bundle bundle = getArguments();
        mObjReq = bundle.getParcelable(getString(R.string.news_detail));
        ButterKnife.bind(this,rootView);

        txtUserName.setText(mObjReq.getRecipientId());
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
                        if (firebaseError!=null)
                            Toast.makeText(getActivity(),"Failed",Toast.LENGTH_SHORT).show();
                        else{
                            Toast.makeText(getActivity(),"Accepted",Toast.LENGTH_LONG).show();
                            dismiss();
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
        startActivity(intent);
    }
}
