package com.example.rnztx.donors.models;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.rnztx.donors.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by rnztx on 1/3/16.
 */
public class CustomAdapter extends ArrayAdapter<Requirement> {
    private static String LOG_TAG = CustomAdapter.class.getSimpleName();
    @Bind(R.id.title) TextView title;

    public CustomAdapter(Activity activity, List<Requirement> requirement){
        super(activity,0,requirement);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Requirement bloodRequirement = getItem(position);
        if (convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.element_news_feed,parent,false);
        }
        ButterKnife.bind(this,convertView);

        String titleTxt = Integer.toString(bloodRequirement.getPinCode());
        title.setText(titleTxt);

        return convertView;
    }
}
