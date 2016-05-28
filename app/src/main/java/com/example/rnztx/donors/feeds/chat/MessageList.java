package com.example.rnztx.donors.feeds.chat;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.rnztx.donors.R;
import com.example.rnztx.donors.models.UserInfo;
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
public class MessageList extends Fragment {
    private static String LOG_TAG = MessageList.class.getSimpleName();
    @Bind(R.id.listView_message_list) ListView listViewChatMessages;
    ArrayAdapter mArrayAdapter;
    Firebase mMessageListRoot = new Firebase(Constants.FIREBASE_URL_MESSAGE_LIST);
    private static ArrayList<String> mChatList = new ArrayList<>();
    public MessageList() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mArrayAdapter = new ArrayAdapter(getActivity(),R.layout.item_message_list,new ArrayList());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_message_list, container, false);
        ButterKnife.bind(this,rootView);
        listViewChatMessages.setAdapter(mArrayAdapter);
        mMessageListRoot.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                String[] chatUsers = dataSnapshot.getKey().split("_");
                String loggedUserId = Utilities.getUserId();

                if (chatUsers[0].equals(loggedUserId) || chatUsers[1].equals(loggedUserId)){
                    String targetId = null;
                    if(chatUsers[0].equals(loggedUserId))
                        targetId = chatUsers[1];
                    else
                        targetId = chatUsers[0];

                    UserInfo targetUserObj = Utilities.userInfoMap.get(targetId);
                    if (!mChatList.contains(targetId)){
                        mArrayAdapter.add(targetUserObj.getUserDisplayName());
                        mChatList.add(targetId);
                    }
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

        listViewChatMessages.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String targetUserId = mChatList.get(position);

                Intent intent = new Intent(getActivity(), ChatActivity.class);
                Bundle arguments = new Bundle();
                arguments.putString(Constants.EXTRA_TARGET_USERID,targetUserId);
                intent.putExtras(arguments);
                startActivity(intent);
            }
        });
        return rootView;
    }
    public static MessageList newInstance(){
        return new MessageList();
    }

}
