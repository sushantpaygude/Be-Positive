package com.example.rnztx.donors.inputForms;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.rnztx.donors.R;
import com.example.rnztx.donors.models.Requirement;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by rnztx on 3/3/16.
 */
public class NewsDetail extends DialogFragment {
    @Bind(R.id.txtUserName) TextView txtUserName;
    @Bind(R.id.btn_close) Button btnClose;
    @Bind(R.id.txtBloodGroup) TextView txtBloodGroup;
    @Bind(R.id.txtLocationName) TextView txtLocationName;

    private Requirement requirement;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.news_detail_dialog,container);
        Bundle bundle = getArguments();
        requirement = bundle.getParcelable(getString(R.string.news_detail));
        ButterKnife.bind(this,rootView);

        txtUserName.setText(requirement.getUserId());
        txtLocationName.setText(requirement.getLocName());
        txtBloodGroup.setText(requirement.getBloodGroup());

        return rootView;
    }

    @OnClick(R.id.btn_close)
    void close(){
        dismiss();
    }

}
