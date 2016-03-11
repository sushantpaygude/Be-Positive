package com.example.rnztx.donors.models;

/**
 * Created by rnztx on 9/3/16.
 */
public class KeyReference extends Object{
    String uniqueKey;
    public KeyReference() {
    }

    public KeyReference(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

    public String getUniqueKey() {
        return uniqueKey;
    }
}
