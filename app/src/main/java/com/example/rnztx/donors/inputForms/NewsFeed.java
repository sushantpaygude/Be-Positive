package com.example.rnztx.donors.inputForms;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.rnztx.donors.R;
import com.example.rnztx.donors.models.CustomAdapter;
import com.example.rnztx.donors.models.Requirement;
import com.example.rnztx.donors.utils.Constants;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFeed extends Fragment {
    @Bind(R.id.listView_news_feed)
    ListView newsFeedListView;
    private static String LOG_TAG = NewsFeed.class.getSimpleName();
    private CustomAdapter mCustomAdapter;
    private ArrayList<Requirement> arrayListData;
    private ArrayList<Requirement> dummyData;
    public NewsFeed() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dummyData = new ArrayList<>();
//        dummyData.add(new Requirement("O+",415304,"user"));
        mCustomAdapter = new CustomAdapter(getActivity(),dummyData);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        Firebase.setAndroidContext(getActivity());
        View rootView =  inflater.inflate(R.layout.fragment_news_feed, container, false);
        ButterKnife.bind(this,rootView);
        newsFeedListView.setAdapter(mCustomAdapter);

        Firebase firebase = new Firebase(Constants.FIREBASE_URL_REQUIREMENTS);

        firebase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Requirement requirements = dataSnapshot.getValue(Requirement.class);
                if(requirements!=null){
                    Log.e(LOG_TAG,requirements.toString());
                    mCustomAdapter.add(requirements);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

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

        newsFeedListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Requirement requirement = mCustomAdapter.getItem(position);
                Log.e("Data: ",requirement.toString());
//                Toast.makeText(getContext(),,Toast.LENGTH_SHORT).show();
            }
        });
        return rootView;
    }

    public static NewsFeed newInstance(){
        NewsFeed fragment = new NewsFeed();
//        Bundle args = new Bundle();
//        args.putInt(Constants.ARG_SECTION_NUMBER,position);
        return fragment;
    }

}
