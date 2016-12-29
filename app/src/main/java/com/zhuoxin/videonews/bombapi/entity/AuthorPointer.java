package com.zhuoxin.videonews.bombapi.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2016/12/27.
 */

public class AuthorPointer {
    @SerializedName("__type")
    private String type;
    private String className;
    private String objectId;

    public AuthorPointer(String objectId) {
        type = "Pointer";
        className = "_User";
        this.objectId = objectId;
    }
}
