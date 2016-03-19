package com.example.rnztx.donors.feeds.pending;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rnztx.donors.R;
import com.example.rnztx.donors.models.Requirement;
import com.example.rnztx.donors.utils.Constants;
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
    @Bind(R.id.txtUserName) TextView txtUserName;
    @Bind(R.id.btn_close) Button btnClose;
    @Bind(R.id.txtBloodGroup) TextView txtBloodGroup;
    @Bind(R.id.txtLocationName) TextView txtLocationName;
    @Bind(R.id.btnAccept) Button btnAccept;

    private Requirement mObjReq = null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.news_detail_dialog,container);
        Bundle bundle = getArguments();
        mObjReq = bundle.getParcelable(getString(R.string.news_detail));
        ButterKnife.bind(this,rootView);

        txtUserName.setText(mObjReq.getUserId());
        txtLocationName.setText(mObjReq.getLocName());
        txtBloodGroup.setText(mObjReq.getBloodGroup());

        return rootView;
    }

    @OnClick(R.id.btn_close)
    void close(){
        dismiss();
    }
    @OnClick(R.id.btnAccept)
    void acceptRequest(){
        if(mObjReq != null){
            Firebase.goOffline();
            Firebase.goOnline();

            Firebase fRoot = new Firebase(Constants.FIREBASE_URL_REQUIREMENTS);
            Firebase fChild = fRoot.child(mObjReq.getObjKey());

            Map<String,Object> mapObj = new HashMap<>();
            mapObj.put(Constants.FIREBASE_PROPERTY_STATUS,true);
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
        }
    }

}
