package com.zhuoxin.videonews.ui.news.comments;

import android.content.Context;
import android.util.AttributeSet;

import com.zhuoxin.videonews.bombapi.BombConst;
import com.zhuoxin.videonews.bombapi.entity.CommentsEntity;
import com.zhuoxin.videonews.bombapi.other.InQuery;
import com.zhuoxin.videonews.bombapi.result.QueryResult;
import com.zhuoxin.videonews.ui.base.BaseResourceView;

import retrofit2.Call;


/**
 * Created by Administrator on 2016/12/26 0026.
 */

public class CommentsListView extends BaseResourceView<CommentsEntity,CommentsItemView> {
    public CommentsListView(Context context) {
        super(context);
    }

    public CommentsListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CommentsListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public String newsId;

    public void setNewsId(String newsId){
        this.newsId = newsId;
    }

    @Override
    protected Call<QueryResult<CommentsEntity>> queryData(int limit, int skip) {
        InQuery where = new InQuery(BombConst.FIELD_NEWS,BombConst.TABLE_NEWS,newsId);
        return newsApi.getComments(limit,skip,where);
    }

    @Override
    protected int getLimit() {
        return 20;
    }

    @Override
    protected CommentsItemView createItemView() {
        return new CommentsItemView(getContext());
    }
}
