package com.example.rnztx.donors.feeds.chat;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.rnztx.donors.R;
import com.example.rnztx.donors.models.ChatMessage;
import com.example.rnztx.donors.models.utils.Constants;
import com.example.rnztx.donors.models.utils.Utilities;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment {

    ArrayAdapter mArrayAdapter;
    private static final String LOG_TAG = ChatFragment.class.getSimpleName();
    @Bind(R.id.img_send_chat_message) ImageButton imgSendChatMessage;
    @Bind(R.id.edtx_new_message) EditText edtxNewMesssage;
    @Bind(R.id.list_view_chats) ListView listViewChats;
    ArrayList<String> mList ;
    String mTargetUserId = "879322342";
    Firebase mFirebaseNewMessage;

    public ChatFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mList = new ArrayList<>();
        mArrayAdapter = new ArrayAdapter(getActivity(),R.layout.item_chat_messages,mList);

        Bundle arguments = getActivity().getIntent().getExtras();
        if (arguments.containsKey(Constants.EXTRA_TARGET_USERID)){
            mTargetUserId = arguments.getString(Constants.EXTRA_TARGET_USERID);
        }

        // set target userName to Actionbar Title
        getActivity().setTitle(
                Utilities.userInfoMap
                        .get(mTargetUserId) // get UserInfo Obj
                        .getUserDisplayName() // get his name
        );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.chat_fragment, container, false);
        ButterKnife.bind(this,rootView);
        listViewChats.setAdapter(mArrayAdapter);

        Firebase firebase = new Firebase(Constants.FIREBASE_URL)
                .child(Constants.FIREBASE_LOCATION_CHATMESSAGES);
        mFirebaseNewMessage = firebase.child(Utilities.getChatId(mTargetUserId));

        mFirebaseNewMessage.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ChatMessage incomingMessage = dataSnapshot.getValue(ChatMessage.class);
                String userName = Utilities.userInfoMap.get(incomingMessage.getUserId()).getUserDisplayName();
                String message = userName.split(" ")[0] + ": " +incomingMessage.getMessage();
                mArrayAdapter.add(message);
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
        return rootView;
    }

    @OnClick(R.id.img_send_chat_message)
    public void onMessageSend(){
        String newMessage = edtxNewMesssage.getText().toString();
        if (!newMessage.isEmpty()){
            ChatMessage newMessageObj = new ChatMessage(Utilities.getUserId(),newMessage);
            String uniqueKey = mFirebaseNewMessage.push().getKey();
            mFirebaseNewMessage.child(uniqueKey).setValue(newMessageObj, new Firebase.CompletionListener() {
                @Override
                public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                    if (firebaseError==null){
                        edtxNewMesssage.setText("");
                    }
                }
            });
        }
    }

}
