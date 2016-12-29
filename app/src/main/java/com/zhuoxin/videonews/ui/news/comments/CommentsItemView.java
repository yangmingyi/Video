package com.zhuoxin.videonews.ui.news.comments;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;


import com.zhuoxin.videonews.R;
import com.zhuoxin.videonews.bombapi.entity.AuthorEntity;
import com.zhuoxin.videonews.bombapi.entity.CommentsEntity;
import com.zhuoxin.videonews.commons.CommonUtils;
import com.zhuoxin.videonews.ui.base.BaseItemView;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/26 0026.
 */

public class CommentsItemView extends BaseItemView<CommentsEntity> {

    @BindView(R.id.tvContent)
    TextView tvContent; // 评论内容
    @BindView(R.id.tvAuthor)
    TextView tvAuthor; // 评论作者
    @BindView(R.id.tvCreatedAt)
    TextView tvCreatedAt; // 评论时间

    public CommentsItemView(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.item_comments,this,true);
        ButterKnife.bind(this);
    }

    @Override
    protected void bindModel(CommentsEntity commentsEntity) {
        //数据绑定
        String content = commentsEntity.getContent();//评论内容
        Date createdAt = commentsEntity.getCreatedAt();// 评论时间
        AuthorEntity authorEntity = commentsEntity.getAuthor();
        String username = authorEntity.getUsername(); // 评论作者
        tvContent.setText(content);
        tvAuthor.setText(username);
        tvCreatedAt.setText(CommonUtils.format(createdAt));
    }
}
