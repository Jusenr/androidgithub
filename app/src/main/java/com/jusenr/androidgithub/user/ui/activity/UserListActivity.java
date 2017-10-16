package com.jusenr.androidgithub.user.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jusenr.androidgithub.R;
import com.jusenr.androidgithub.base.PTMVPActivity;
import com.jusenr.androidgithub.retrofit.api.RepoApi;
import com.jusenr.androidgithub.user.contract.UserListContract;
import com.jusenr.androidgithub.user.di.component.DaggerUserListComponent;
import com.jusenr.androidgithub.user.di.module.UserListModule;
import com.jusenr.androidgithub.user.model.model.UserModel;
import com.jusenr.androidgithub.user.presenter.UserListPresenter;
import com.jusenr.androidgithub.user.ui.adapter.UserListRecyclerAdapter;
import com.jusenr.androidgithub.utils.AccountHelper;
import com.jusenr.androidgithub.utils.Constants;
import com.jusenr.toolslibrary.utils.ListUtils;
import com.jusenr.toolslibrary.utils.StringUtils;
import com.jusenr.toolslibrary.utils.ToastUtils;

import java.util.ArrayList;

import butterknife.BindView;

public class UserListActivity extends PTMVPActivity<UserListPresenter> implements UserListContract.View {

    @BindView(R.id.user_list)
    RecyclerView mUserList;

    private UserListRecyclerAdapter mAdapter;

    @Override
    protected void injectComponent() {
        DaggerUserListComponent.builder()
                .appComponent(mApplication.getAppComponent())
                .userListModule(new UserListModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_list;
    }

    @Override
    protected void onViewCreated(@Nullable Bundle savedInstanceState) {
        super.onViewCreated(savedInstanceState);

        initViews();

        String action = getIntent().getAction();
        String username = getIntent().getStringExtra(Constants.BundleKey.BUNDLE_USER_NAME);
        String nickname = AccountHelper.getNickname();
        boolean isSelf = !StringUtils.isEmpty(nickname) && nickname.equalsIgnoreCase(username);

        if (Constants.ActionKey.ACTION_FOLLOWING.equals(action)) {
            setTitle(isSelf ? getString(R.string.my_following) : getString(R.string.following, username));
            mPresenter.onLoadUsers(username, isSelf, RepoApi.FOLLOWING);
        } else if (Constants.ActionKey.ACTION_FOLLOWERS.equals(action)) {
            setTitle(isSelf ? getString(R.string.my_followers) : getString(R.string.followers, username));
            mPresenter.onLoadUsers(username, isSelf, RepoApi.FOLLOWER);
        }
    }

    @Override
    public void loadUsersResult(ArrayList<UserModel> users) {
        if (!ListUtils.isEmpty(users)) {
            mAdapter.setNewData(users);
        }
    }

    @Override
    public void loadUsersFailed(int code, String msg) {
        if (!StringUtils.isEmpty(msg))
            ToastUtils.show(this, msg);
    }

    private void initViews() {
        mAdapter = new UserListRecyclerAdapter(null);
        mAdapter.setOnRecyclerViewItemClickListener(mItemtClickListener);

        mUserList.setAdapter(mAdapter);
    }

    private BaseQuickAdapter.OnRecyclerViewItemClickListener mItemtClickListener = new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            UserModel user = mAdapter.getItem(position);
            if (user != null) {
                Intent intent = new Intent(mActivity, UserActivity.class);
                intent.putExtra(Constants.BundleKey.BUNDLE_USER_NAME, user.getLogin());
                startActivity(intent);
            }
        }
    };
}
