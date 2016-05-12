package com.example.rnztx.donors.models;

import com.example.rnztx.donors.models.utils.Constants;
import com.firebase.client.ServerValue;

import java.util.HashMap;

/**
 * Created by rnztx on 12/5/16.
 */
public class ChatMessage {
    private String userId ;
    private HashMap<String,Object> timeStamp;
    private String message;

    public ChatMessage(String userId, String message) {
        this.userId = userId;
        this.message = message;
        timeStamp = new HashMap<>();
        timeStamp.put(Constants.FIREBASE_PROPERTY_CHATMESSAGE_TIMESTAMP, ServerValue.TIMESTAMP);
    }

    public ChatMessage() {
    }

    public String getUserId() {
        return userId;
    }

    public HashMap<String, Object> getTimeStamp() {
        return timeStamp;
    }

    public String getMessage() {
        return message;
    }
}
