package com.example.rnztx.donors.feeds.pending;


import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.rnztx.donors.R;
import com.example.rnztx.donors.feeds.DialogDetail;
import com.example.rnztx.donors.models.AdapterFeedList;
import com.example.rnztx.donors.models.Requirement;
import com.example.rnztx.donors.models.utils.Constants;
import com.example.rnztx.donors.models.utils.Utilities;
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
public class FeedPending extends Fragment {
    @Bind(R.id.listView_news_feed)
    ListView newsFeedListView;
    private static String LOG_TAG = FeedPending.class.getSimpleName();
    private AdapterFeedList mAdapterPendingList;
    private ArrayList<Requirement> arrayListData;
    private ArrayList<Requirement> dummyData;
    private static Firebase mFirebaseRef;
    Context mContext = getActivity();
    public FeedPending() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dummyData = new ArrayList<>();
        dummyData.add(new Requirement("O+",415304,"swargate","key"));
        mAdapterPendingList = new AdapterFeedList(getActivity(),dummyData, AdapterFeedList.TYPE_PENDING_LIST);
        Firebase.setAndroidContext(getActivity());
    }

    @Override
    public void onResume() {
        super.onResume();
        // to Avoid Duplicate Data
        mAdapterPendingList.clear();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View rootView =  inflater.inflate(R.layout.fragment_news_feed, container, false);
        ButterKnife.bind(this,rootView);
        newsFeedListView.setAdapter(mAdapterPendingList);

        mFirebaseRef = new Firebase(Constants.FIREBASE_URL_REQUIREMENTS);

        mFirebaseRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Requirement requirements = dataSnapshot.getValue(Requirement.class);
                if(requirements!=null){
                    // status is not accepted
                    if (!requirements.getStatus() &&
                            // req is not sent my logged user
                            !requirements.getRecipientId().equals(Utilities.getUserId()))
                        mAdapterPendingList.add(requirements);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Requirement obj = dataSnapshot.getValue(Requirement.class);
                if (obj!=null){
                    // if status accepted
                    if (obj.getStatus())
                        mAdapterPendingList.remove(obj);
                }
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Requirement obj = dataSnapshot.getValue(Requirement.class);
                if (obj!=null){
                    mAdapterPendingList.remove(obj);
                }
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
                Requirement requirement = mAdapterPendingList.getItem(position);
                DialogDetail detail = new DialogDetail();
                Bundle bundle = new Bundle();

                String EXTRA_NAME = getString(R.string.feed_detail);
                bundle.putParcelable(EXTRA_NAME,requirement);
                detail.setArguments(bundle);


                // launch dialog box
                FragmentManager fm = getActivity().getFragmentManager();
                detail.show(fm,"oll");

            }
        });
        return rootView;
    }

    public static FeedPending newInstance(){
        FeedPending fragment = new FeedPending();
        return fragment;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAdapterPendingList.clear();
    }
}
