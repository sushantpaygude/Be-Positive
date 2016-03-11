package com.example.rnztx.donors.models;

/**
 * Created by rnztx on 9/3/16.
 */
public class ReferenceRequirement {
    String uniqueKey;
    public ReferenceRequirement() {
    }

    public ReferenceRequirement(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

    public String getUniqueKey() {
        return uniqueKey;
    }
}
