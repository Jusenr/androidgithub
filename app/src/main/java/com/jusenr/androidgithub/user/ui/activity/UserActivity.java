package com.jusenr.androidgithub.user.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;

import com.jusenr.androidgithub.R;
import com.jusenr.androidgithub.base.PTMVPActivity;
import com.jusenr.androidgithub.home.ui.activity.RepoListActivity;
import com.jusenr.androidgithub.user.contract.UserContract;
import com.jusenr.androidgithub.user.di.component.DaggerUserComponent;
import com.jusenr.androidgithub.user.di.module.UserModule;
import com.jusenr.androidgithub.user.model.model.UserModel;
import com.jusenr.androidgithub.user.presenter.UserPresenter;
import com.jusenr.androidgithub.utils.Constants;
import com.jusenr.androidgithub.widgets.UserCard;
import com.jusenr.toolslibrary.utils.StringUtils;
import com.jusenr.toolslibrary.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jusenr.androidgithub.utils.Constants.ActionKey.ACTION_REPOS;
import static com.jusenr.androidgithub.utils.Constants.ActionKey.ACTION_STARRED_REPOS;

public class UserActivity extends PTMVPActivity<UserPresenter> implements UserContract.View {

    @BindView(R.id.user_card)
    UserCard mUserCard;

    private String mUsername;

    @Override
    protected void injectComponent() {
        DaggerUserComponent.builder()
                .appComponent(mApplication.getAppComponent())
                .userModule(new UserModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user;
    }

    @Override
    public String getLoadingMessage() {
        return null;
    }

    @Override
    protected void onViewCreated(@Nullable Bundle savedInstanceState) {
        super.onViewCreated(savedInstanceState);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loadUser();
    }

    @Override
    public void getUserinfoResult(UserModel bean) {
        mUserCard.setUser(bean);
    }

    @Override
    public void getUserinfoFailed(int code, String msg) {
        if (!StringUtils.isEmpty(msg))
            ToastUtils.show(this, msg);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.repo_layout, R.id.starred_layout, R.id.following_layout, R.id.followers_layout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.repo_layout:
             Intent intent = new Intent(this, RepoListActivity.class);
                intent.putExtra(Constants.BundleKey.BUNDLE_USER_NAME, mUsername);
                intent.setAction(Constants.ActionKey.ACTION_REPOS);
                startActivity(intent);
                break;
            case R.id.starred_layout:
                Intent intent1 = new Intent(this, RepoListActivity.class);
                intent1.putExtra(Constants.BundleKey.BUNDLE_USER_NAME, mUsername);
                intent1.setAction(Constants.ActionKey.ACTION_STARRED_REPOS);
                startActivity(intent1);
                break;
            case R.id.following_layout:
//                UserListActivity.launchToShowFollowing(this, mUsername);
                break;

            case R.id.followers_layout:
//                UserListActivity.launchToShowFollowers(this, mUsername);
                break;
        }
    }

    private void loadUser() {
        mUsername = getIntent().getStringExtra(Constants.BundleKey.BUNDLE_USER_NAME);
        if (!TextUtils.isEmpty(mUsername)) {
            setTitle(mUsername);
            mPresenter.onUserInfo(mUsername);
        }
    }
}
