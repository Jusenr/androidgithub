package com.jusenr.androidgithub.home.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jusenr.androidgithub.R;
import com.jusenr.androidgithub.utils.SharePlatform;
import com.jusenr.androidgithub.base.PTFragment;
import com.jusenr.androidgithub.user.SettingsActivity;
import com.jusenr.androidgithub.utils.AccountHelper;
import com.jusenr.androidlibrary.widgets.fresco.FrescoImageView;
import com.jusenr.toolslibrary.utils.StringUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description:
 * Copyright  : Copyright (c) 2017
 * Email      : jusenr@163.com
 * Author     : Jusenr
 * Date       : 2017/09/30
 * Time       : 17:10
 * Project    ：androidgithub.
 */
public class MineFragment extends PTFragment {

    @BindView(R.id.iv_user_icon)
    FrescoImageView mIvUserIcon;
    @BindView(R.id.tv_username)
    TextView mTvUsername;
    @BindView(R.id.account_view)
    LinearLayout mAccountView;
    @BindView(R.id.tv_history)
    TextView mTvHistory;
    @BindView(R.id.tv_share_app)
    TextView mTvShareApp;
    @BindView(R.id.tv_feedback)
    TextView mTvFeedback;
    @BindView(R.id.tv_settings)
    TextView mTvSettings;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public String getLoadingMessage() {
        return null;
    }

    @Override
    public void onViewCreateFinish(View view, @Nullable Bundle savedInstanceState) {
        String avatar = AccountHelper.getAvatar();
        String nickname = AccountHelper.getNickname();
        if (!StringUtils.isEmpty(avatar)) {
            mIvUserIcon.setImageURL(avatar + ".png");
        }
        if (!StringUtils.isEmpty(nickname)) {
            mTvUsername.setText(nickname);
        }
    }

    @OnClick({R.id.account_view, R.id.tv_history, R.id.tv_share_app, R.id.tv_feedback, R.id.tv_settings})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.account_view:
                break;
            case R.id.tv_history:
                break;
            case R.id.tv_share_app:
                SharePlatform.share(getActivity());
                break;
            case R.id.tv_feedback:
                break;
            case R.id.tv_settings:
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                break;
        }
    }
}
