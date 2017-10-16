package com.jusenr.androidgithub.user.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jusenr.androidgithub.R;
import com.jusenr.androidgithub.base.PTActivity;
import com.jusenr.androidgithub.utils.Constants;
import com.jusenr.toolslibrary.utils.AppUtils;

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
public class SettingsActivity extends PTActivity {

    @BindView(R.id.tv_current_version)
    TextView mTvCurrentVersion;
    @BindView(R.id.ll_upgrade)
    LinearLayout mLlUpgrade;
    @BindView(R.id.tv_about_author)
    TextView mTvAboutAuthor;
    @BindView(R.id.tv_about)
    TextView mTvAbout;
    @BindView(R.id.ll_about)
    LinearLayout mLlAbout;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_settings;
    }

    @Override
    protected void onViewCreated(@Nullable Bundle savedInstanceState) {
        setTitle(R.string.settings);

        mTvCurrentVersion.setText(AppUtils.getVersionName(this));
    }

    @OnClick({R.id.ll_upgrade, R.id.tv_about_author, R.id.tv_about})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_upgrade:
                break;
            case R.id.tv_about_author:
                Intent intent = new Intent(mActivity, UserActivity.class);
                intent.putExtra(Constants.BundleKey.BUNDLE_USER_NAME, Constants.Value.AUTHOR);
                startActivity(intent);
                break;
            case R.id.tv_about:
                break;
        }
    }
}
