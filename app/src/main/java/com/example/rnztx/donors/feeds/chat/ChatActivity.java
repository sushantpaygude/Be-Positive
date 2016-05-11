package com.example.rnztx.donors.feeds.chat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.rnztx.donors.R;

public class ChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_activity);
        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container_chat_activity,new ChatFragment())
                    .commit();
        }

    }
}
