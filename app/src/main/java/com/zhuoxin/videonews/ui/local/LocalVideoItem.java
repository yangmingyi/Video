package com.zhuoxin.videonews.ui.local;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhuoxin.videonews.R;
import com.zhuoxin.videoplayer.full.VideoViewActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/28.
 */

public class LocalVideoItem extends FrameLayout{
    public LocalVideoItem(Context context) {
        super(context,null);
    }

    public LocalVideoItem(Context context, AttributeSet attrs) {
        super(context, attrs,0);
    }

    public LocalVideoItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }
    @BindView(R.id.ivPreview)
    ImageView ivPreview;
    @BindView(R.id.tvVideoName)
    TextView tvVideoName;
    private String filePath;//文件路径
    public String getFilePath(){
        return filePath;
    }

    public void setIvPreView(Bitmap bitmap){
        ivPreview.setImageBitmap(bitmap);
    }
    //设置预览图,可以在后台线程执行
    public void setIvPreView(String filePath, final Bitmap bitmap){
        if (!filePath.equals(this.filePath)) return;
        post(new Runnable() {
            @Override
            public void run() {
                ivPreview.setImageBitmap(bitmap);
            }
        });
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.item_local_video, this, true);
        ButterKnife.bind(this);
    }

    public void bind(Cursor cursor) {
        //取出视频名称
        String videoName = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DISPLAY_NAME));
        tvVideoName.setText(videoName);
        //取出文件路径
        filePath = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA));
    }

    //全屏播放
    @OnClick
    public void click(){
        VideoViewActivity.open(getContext(),filePath);
    }


}
