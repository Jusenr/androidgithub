package com.jusenr.androidgithub.home.ui.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jusenr.androidgithub.R;
import com.jusenr.androidgithub.guidance.AboutUsActivity;
import com.jusenr.androidgithub.utils.SharePlatform;
import com.jusenr.androidgithub.base.PTActivity;
import com.jusenr.androidgithub.home.ui.fragment.MineFragment;
import com.jusenr.androidgithub.home.ui.fragment.MostStarFragment;
import com.jusenr.androidgithub.home.ui.fragment.TrendingContainerFragment;
import com.jusenr.androidgithub.user.SettingsActivity;
import com.jusenr.androidgithub.utils.AccountHelper;
import com.jusenr.androidlibrary.widgets.fresco.FrescoImageView;
import com.jusenr.androidlibrary.widgets.scrollview.PTSlidingMenu;
import com.jusenr.toolslibrary.utils.StringUtils;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

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
public class MainActivity extends PTActivity {

    @BindView(R.id.ptslidemenu_layout)
    PTSlidingMenu mPTSlideMenu;

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
    @BindView(R.id.tv_about_us)
    TextView mTvAboutUs;
    @BindView(R.id.tv_loginout)
    TextView mTvLoginout;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.fl_content)
    FrameLayout mFlContent;


    private FragmentManager mFragmentManager = getFragmentManager();
    private Fragment mCurrentFragment;
    private BottomBar mBottomBar;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public String getLoadingMessage() {
        return null;
    }

    @Override
    protected void onViewCreated(@Nullable Bundle savedInstanceState) {
        mBottomBar = BottomBar.attach(this, savedInstanceState);
        initViews();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        mBottomBar.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                startActivity(new Intent(this, SearchActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPTSlideMenu.requestLayout();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (mPTSlideMenu.isOpen()) {
                mPTSlideMenu.toggle(false);
                return true;
            }
            return exit(2000);
        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick({R.id.account_view, R.id.tv_history, R.id.tv_share_app, R.id.tv_feedback, R.id.tv_settings, R.id.tv_about_us, R.id.tv_loginout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.account_view:
                break;
            case R.id.tv_history:
                break;
            case R.id.tv_share_app:
                SharePlatform.share(this);
                break;
            case R.id.tv_feedback:
                break;
            case R.id.tv_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                break;
            case R.id.tv_about_us:
                startActivity(new Intent(this, AboutUsActivity.class));
                break;
            case R.id.tv_loginout:
                AccountHelper.logout();
                finish();
                break;
        }
    }

    private void initViews() {
        mPTSlideMenu.requestDisallowInterceptTouchEvent(true);
        setSupportActionBar(mToolbar);

        mBottomBar.setItems(R.menu.bottombar_menu);
        mBottomBar.setOnMenuTabClickListener(mTabClickListener);

        String avatar = AccountHelper.getAvatar();
        String nickname = AccountHelper.getNickname();
        if (!StringUtils.isEmpty(avatar)) {
            mIvUserIcon.setImageURL(avatar + ".png");
        }
        if (!StringUtils.isEmpty(nickname)) {
            mTvUsername.setText(nickname);
        }
    }

    private OnMenuTabClickListener mTabClickListener = new OnMenuTabClickListener() {
        @Override
        public void onMenuTabSelected(@IdRes int menuItemId) {
            changeTitle(menuItemId);
            switchMenu(getFragmentName(menuItemId));
        }

        @Override
        public void onMenuTabReSelected(@IdRes int menuItemId) {

        }
    };

    private void switchMenu(String fragmentName) {
        Fragment fragment = mFragmentManager.findFragmentByTag(fragmentName);
        if (fragment != null) {
            if (fragment == mCurrentFragment) return;
            mFragmentManager.beginTransaction().show(fragment).commit();
        } else {
            fragment = Fragment.instantiate(this, fragmentName);
            mFragmentManager.beginTransaction().add(R.id.fl_content, fragment, fragmentName).commit();
        }
        if (mCurrentFragment != null) {
            mFragmentManager.beginTransaction().hide(mCurrentFragment).commit();
        }

        mCurrentFragment = fragment;
    }

    private String getFragmentName(int menuId) {
        switch (menuId) {
            case R.id.menu_trending:
                return TrendingContainerFragment.class.getName();
            case R.id.menu_most_stars:
                return MostStarFragment.class.getName();
            case R.id.menu_account:
                return MineFragment.class.getName();

            default:
                return null;
        }
    }

    private void changeTitle(int menuId) {
        switch (menuId) {
            case R.id.menu_trending:
                setTitle(R.string.menu_trending);
                break;

            case R.id.menu_most_stars:
                setTitle(R.string.menu_most_star);
                break;

            case R.id.menu_account:
                setTitle(R.string.menu_account);
                break;

            default:
                break;
        }
    }
}
