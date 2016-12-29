package com.zhuoxin.videonews.ui.likes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.zhuoxin.videonews.R;
import com.zhuoxin.videonews.UserManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/21.
 */

public class LikesFragment extends Fragment implements RegisterFragment.OnRegisterSuccessListener, LoginFragment.OnLoginSuccessListener {
    @BindView(R.id.tvUsername)
    TextView mTvUsername;
    @BindView(R.id.btnRegister)
    Button mBtnRegister;
    @BindView(R.id.btnLogin)
    Button mBtnLogin;
    @BindView(R.id.btnLogout)
    Button mBtnLogout;
    @BindView(R.id.divider)
    View mDivider;
    @BindView(R.id.likeListView)
    LikesListView likesListView;


    private View view;
    private RegisterFragment mRegisterFragment;
    //登录
    private LoginFragment mLoginFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_likes, container, false);
            ButterKnife.bind(this, view);
            //判断用户登录状态，更新UI
            UserManager userManager = UserManager.getInstance();
            if (!userManager.isOffline()) {
                userOnLine(userManager.getUsername(), userManager.getObjectId());
            }
        }
        return view;
    }

    @OnClick({R.id.btnRegister, R.id.btnLogin, R.id.btnLogout})
    public void onClick(View view) {
        switch (view.getId()) {
            //注册
            case R.id.btnRegister:
                if (mRegisterFragment == null) {
                    mRegisterFragment = new RegisterFragment();
                    mRegisterFragment.setOnRegisterSuccessListener(this);
                }
                mRegisterFragment.show(getChildFragmentManager(), "Register Dialog");
                break;
            //登录
            case R.id.btnLogin:
                if (mLoginFragment == null) {
                    mLoginFragment = new LoginFragment();
                    mLoginFragment.setListener(this);
                }
                mLoginFragment.show(getChildFragmentManager(), "Login Dialog");
                break;
            //退出登录
            case R.id.btnLogout:
                //用户下线
                userOffline();
                break;
        }
    }

    //添加成功注册的监听
    @Override
    public void registerSuccess(String username, String objectId) {
        //关闭注册对话框
        mRegisterFragment.dismiss();
        //用户上线
        userOnLine(username, objectId);
    }

    private void userOnLine(String username, String objectId) {
        //更新UI
        mBtnRegister.setVisibility(View.INVISIBLE);
        mBtnLogin.setVisibility(View.INVISIBLE);
        mBtnLogout.setVisibility(View.VISIBLE);
        mDivider.setVisibility(View.INVISIBLE);
        mTvUsername.setText(username);
        // 存储用户信息
        UserManager.getInstance().setUsername(username);
        UserManager.getInstance().setObjectId(objectId);
        //刷新收藏列表
        likesListView.autoRefresh();
    }

    //登录成功
    public void loginSuccess(String username, String objectId) {
        mLoginFragment.dismiss();
        //用户上线
        userOnLine(username, objectId);
    }


    //用户下线
    private void userOffline() {
        //清除用户相关信息
        UserManager.getInstance().clear();
        //更新UI
        mBtnLogin.setVisibility(View.VISIBLE);
        mBtnRegister.setVisibility(View.VISIBLE);
        mBtnLogout.setVisibility(View.INVISIBLE);
        mDivider.setVisibility(View.VISIBLE);
        mTvUsername.setText(R.string.tourist);
        // 清空收藏列表
        likesListView.clear();
    }

}
