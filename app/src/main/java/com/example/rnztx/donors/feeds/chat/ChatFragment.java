package com.example.rnztx.donors.feeds.chat;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.rnztx.donors.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

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

    public ChatFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mList = new ArrayList<>();
        mArrayAdapter = new ArrayAdapter(getActivity(),R.layout.chat_items,mList);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.chat_fragment, container, false);
        ButterKnife.bind(this,rootView);
        listViewChats.setAdapter(mArrayAdapter);
        mList.add("Rohit");
        mList.add("Rohan");
        mArrayAdapter.add("Where");

        Log.e(LOG_TAG,"Size: "+listViewChats.getCount());
        return rootView;
    }

}
