package com.example.rnztx.donors.models;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rnztx.donors.R;
import com.example.rnztx.donors.utils.Constants;
import com.example.rnztx.donors.utils.Utilities;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by rnztx on 1/3/16.
 */
public class AdapterFeedList extends ArrayAdapter<Requirement> {
    public static final int TYPE_PENDING_LIST = 0;
    public static final int TYPE_APPROVED_LIST = 1;

    private static String LOG_TAG = AdapterFeedList.class.getSimpleName();
    private  Context mContext;
    ArrayList<String> keySet = null;
    int listType;
   public AdapterFeedList(Activity activity, List<Requirement> requirement, int type){
        super(activity,0,requirement);
        keySet = new ArrayList<>();
       listType = type;
       this.mContext = activity;
    }

    @Override
    public View getView(int position, View rootView, ViewGroup parent) {
        Requirement bloodRequirement = getItem(position);
        int layoutFileId = 0;
//        decide layout file
        if (listType == TYPE_PENDING_LIST)
            layoutFileId = R.layout.item_pending_feed;
        else if (listType == TYPE_APPROVED_LIST)
            layoutFileId = R.layout.item_approved_feed;
//        build root view
        if (rootView==null){
            rootView = LayoutInflater.from(getContext()).inflate(layoutFileId,parent,false);
        }
        if (listType == TYPE_PENDING_LIST){
            ItemPending itemPending = new ItemPending(rootView);
            itemPending.txtBloodGroup.setText(bloodRequirement.getBloodGroup());
            itemPending.txtLocationName.setText(bloodRequirement.getLocName());
            SimpleDateFormat shortDate = new SimpleDateFormat("EEE MMM dd");
            itemPending.txtDate.setText(shortDate.format(bloodRequirement.getDate()));
        }
        else if (listType == TYPE_APPROVED_LIST){
            ItemApproved itemApproved = new ItemApproved(rootView);
            UserInfo userInfo = Utilities.userInfoMap.get(bloodRequirement.getDonorId());
            itemApproved.txtDonorName.setText(userInfo.getUserDisplayName());
            itemApproved.txtDonorPhoneNumber.setText(userInfo.getMobileNumber());

            if (!userInfo.getUserPhotoUrl().equals(Constants.PREF_DEFAULT_VALUE))
                Picasso.with(mContext)
                    .load(userInfo.getUserPhotoUrl())
                    .into(itemApproved.imgAvatar);
        }


        return rootView;
    }
    static class ItemApproved{
        @Bind(R.id.txtDonorName) TextView txtDonorName;
        @Bind(R.id.imgAvatar) ImageView imgAvatar;
        @Bind(R.id.txtDonorPhoneNumber) TextView txtDonorPhoneNumber;

        public ItemApproved(View view){
            ButterKnife.bind(this,view);
        }
    }
    static class ItemPending{
        @Bind(R.id.txtBloodGroup) TextView txtBloodGroup;
        @Bind(R.id.txtLocationName) TextView txtLocationName;
        @Bind(R.id.txtDate) TextView txtDate;

        public ItemPending(View view){
            ButterKnife.bind(this,view);
        }
    }

    @Override
    public void add(Requirement object) {
        if (!keySet.contains(object.getObjKey())){
            super.add(object);
            keySet.add(object.getObjKey());
        }
    }

    @Override
    public void remove(Requirement object) {
        // make sure you use getPosition
        int id = getPosition(object);
//        Log.e(LOG_TAG,"ID: "+id);
        if (id!=-1){
            // as this is Custom adapter, direct Remove won't work .
            // so, reinitialising object from superclass
            object = getItem(id);
            super.remove(object);
            keySet.remove(object.getObjKey());
        }
    }

    @Override
    public void clear() {
        super.clear();
        keySet.clear();
    }

    @Override
    public int getPosition(Requirement item) {
        int id = 0;
        for (String key: keySet){
            if (key.equals(item.getObjKey())){
                return id;
            }
            id++;
        }
        return -1;
    }
}
