package com.zhuoxin.videonews.bombapi.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2016/12/27.
 */

public class NewsPointer {
    @SerializedName("__type")
    private String type;
    private String className;
    private String objectId;

    public NewsPointer(String objectId) {
        type = "Pointer";
        className = "News";
        this.objectId = objectId;
    }
}
