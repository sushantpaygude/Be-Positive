package com.example.rnztx.donors.inputForms;


import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
public class FeedPending extends Fragment {
    @Bind(R.id.listView_news_feed)
    ListView newsFeedListView;
    private static String LOG_TAG = FeedPending.class.getSimpleName();
    private CustomAdapter mCustomAdapter;
    private ArrayList<Requirement> arrayListData;
    private ArrayList<Requirement> dummyData;
    private static Firebase mFirebaseRef;
    public FeedPending() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dummyData = new ArrayList<>();
        dummyData.add(new Requirement("O+",415304,"user","swargate","key"));
        mCustomAdapter = new CustomAdapter(getActivity(),dummyData);
        Firebase.setAndroidContext(getActivity());
    }

    @Override
    public void onResume() {
        super.onResume();
        // to Avoid Duplicate Data
        mCustomAdapter.clear();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View rootView =  inflater.inflate(R.layout.fragment_news_feed, container, false);
        ButterKnife.bind(this,rootView);
        newsFeedListView.setAdapter(mCustomAdapter);

        mFirebaseRef = new Firebase(Constants.FIREBASE_URL_REQUIREMENTS);

        mFirebaseRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Requirement requirements = dataSnapshot.getValue(Requirement.class);
                if(requirements!=null){
                    if (!requirements.getStatus())
                        mCustomAdapter.add(requirements);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Requirement obj = dataSnapshot.getValue(Requirement.class);
                if (obj!=null){
                    // if status accepted
                    if (obj.getStatus())
                        mCustomAdapter.remove(obj);
                }
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Requirement obj = dataSnapshot.getValue(Requirement.class);
                if (obj!=null){
                    mCustomAdapter.remove(obj);
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
                Requirement requirement = mCustomAdapter.getItem(position);
                FeedDetail detail = new FeedDetail();
                Bundle bundle = new Bundle();

                String EXTRA_NAME = getString(R.string.news_detail);
                bundle.putParcelable(EXTRA_NAME,requirement);
                detail.setArguments(bundle);

                // launch dialog box
                FragmentManager fm = getActivity().getFragmentManager();
                detail.show(fm,"oll");

//                FeedDetail newsDetail = new FeedDetail();

            }
        });
        return rootView;
    }

    public static FeedPending newInstance(){
        FeedPending fragment = new FeedPending();
//        Bundle args = new Bundle();
//        args.putInt(Constants.ARG_SECTION_NUMBER,position);
        return fragment;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCustomAdapter.clear();
    }
}
