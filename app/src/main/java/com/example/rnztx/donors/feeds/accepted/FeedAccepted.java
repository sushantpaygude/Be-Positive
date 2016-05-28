package com.example.rnztx.donors.feeds.accepted;

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
import com.example.rnztx.donors.feeds.DialogDetail;
import com.example.rnztx.donors.models.AdapterFeedList;
import com.example.rnztx.donors.models.Requirement;
import com.example.rnztx.donors.models.utils.Constants;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by rnztx on 19/3/16.
 */
public class FeedAccepted extends Fragment {
    @Bind(R.id.listView_feed_accepted) ListView listViewFeedAccepted;
    private AdapterFeedList mAdapterPendingList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        // populate dummy data
        ArrayList<Requirement> adapterList = new ArrayList<>();
        adapterList.add(new Requirement());
        mAdapterPendingList = new AdapterFeedList(getActivity(),adapterList, AdapterFeedList.TYPE_APPROVED_LIST);
        //clear dummy data
        mAdapterPendingList.clear();
        Firebase.setAndroidContext(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_feed_accepted,container,false);
        ButterKnife.bind(this,rootView);
        listViewFeedAccepted.setAdapter(mAdapterPendingList);
        Firebase fRoot = new Firebase(Constants.FIREBASE_URL_REQUIREMENTS);
        fRoot.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                addToListView(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                addToListView(dataSnapshot);
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
        listViewFeedAccepted.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
    public static FeedAccepted newInstance() {
        Bundle args = new Bundle();
        FeedAccepted fragment = new FeedAccepted();
        fragment.setArguments(args);
        return fragment;
    }
    protected void addToListView(DataSnapshot dataSnapshot){
        Requirement obj = dataSnapshot.getValue(Requirement.class);
        if (obj!=null){
//            // if logged users request is Accepted
//            if (obj.getStatus() && obj.getRecipientId().equals(Utilities.getUserId()))
//                mAdapterPendingList.add(obj);
            if (obj.getStatus())
                mAdapterPendingList.add(obj);
        }
    }
}
