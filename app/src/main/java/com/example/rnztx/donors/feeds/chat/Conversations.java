package com.example.rnztx.donors.feeds.chat;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.rnztx.donors.R;
import com.example.rnztx.donors.models.utils.Constants;
import com.firebase.client.Firebase;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class Conversations extends Fragment {
    @Bind(R.id.listView_chat_messages) ListView listViewChatMessages;
    ArrayAdapter mArrayAdapter;

    public Conversations() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mArrayAdapter = new ArrayAdapter(getActivity(),R.layout.item_conversations,new ArrayList());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.conversations_fragment, container, false);
        ButterKnife.bind(this,rootView);
        listViewChatMessages.setAdapter(mArrayAdapter);
        Firebase root = new Firebase(Constants.FIREBASE_URL).child(Constants.FIREBASE_LOCATION_CHATMESSAGES);
        return rootView;
    }
    public static Conversations newInstance(){
        return new Conversations();
    }

}
