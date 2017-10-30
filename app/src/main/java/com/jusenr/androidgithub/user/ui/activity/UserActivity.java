package com.jusenr.androidgithub.user.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jusenr.androidgithub.R;
import com.jusenr.androidgithub.base.PTMVPActivity;
import com.jusenr.androidgithub.home.ui.activity.RepoListActivity;
import com.jusenr.androidgithub.user.contract.UserContract;
import com.jusenr.androidgithub.user.di.component.DaggerUserComponent;
import com.jusenr.androidgithub.user.di.module.UserModule;
import com.jusenr.androidgithub.user.model.model.OrganizationsModel;
import com.jusenr.androidgithub.user.model.model.UserModel;
import com.jusenr.androidgithub.user.presenter.UserPresenter;
import com.jusenr.androidgithub.user.ui.adapter.OrgsListRecyclerAdapter;
import com.jusenr.androidgithub.utils.Constants;
import com.jusenr.androidgithub.widgets.UserCardView;
import com.jusenr.toolslibrary.utils.ListUtils;
import com.jusenr.toolslibrary.utils.StringUtils;
import com.jusenr.toolslibrary.utils.ToastUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class UserActivity extends PTMVPActivity<UserPresenter> implements UserContract.View {

    @BindView(R.id.ll_root_layout)
    LinearLayout mLlRootLayout;
    @BindView(R.id.user_card)
    UserCardView mUserCardView;
    @BindView(R.id.starred_layout)
    LinearLayout mStarredLayout;
    @BindView(R.id.tv_repositories_count)
    TextView mTvRepositoriesCount;
    @BindView(R.id.tv_following_count)
    TextView mTvFollowingCount;
    @BindView(R.id.tv_followers_count)
    TextView mTvFollowersCount;
    @BindView(R.id.organizations_layout)
    LinearLayout mLlOrganizationsLayout;
    @BindView(R.id.ll_orgs_info)
    LinearLayout mLlOrgsInfo;
    @BindView(R.id.brv_orgs_list)
    RecyclerView mBrvOrgsList;


    private OrgsListRecyclerAdapter mAdapter;
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
    protected void onViewCreated(@Nullable Bundle savedInstanceState) {
        super.onViewCreated(savedInstanceState);

        mUsername = getIntent().getStringExtra(Constants.BundleKey.BUNDLE_USER_NAME);
        if (!TextUtils.isEmpty(mUsername)) {
            setTitle(mUsername);
            mPresenter.onUserInfo(mUsername);
            mPresenter.onOrganizations(mUsername);
        }

        mLlRootLayout.setVisibility(View.INVISIBLE);
        mLlOrgsInfo.setVisibility(View.INVISIBLE);

        mAdapter = new OrgsListRecyclerAdapter(null);
        mAdapter.setOnItemClickListener(mItemtClickListener);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        mBrvOrgsList.setAdapter(mAdapter);
    }

    @Override
    public void getUserinfoResult(UserModel bean) {
        mLlRootLayout.setVisibility(View.VISIBLE);
        if (bean != null) {
            mUserCardView.setUser(bean);
            mTvRepositoriesCount.setText(getString(R.string.count, (int) (bean.getPublic_repos())));
            mTvFollowingCount.setText(getString(R.string.count, (int) (bean.getFollowing())));
            mTvFollowersCount.setText(getString(R.string.count, (int) (bean.getFollowers())));
        }
    }

    @Override
    public void getUserinfoFailed(int code, String msg) {
        if (!StringUtils.isEmpty(msg))
            ToastUtils.show(this, msg);
    }

    @Override
    public void organizationsResult(ArrayList<OrganizationsModel> modelArrayList) {
        if (!ListUtils.isEmpty(modelArrayList)) {
            mLlOrganizationsLayout.setVisibility(View.VISIBLE);
            mAdapter.setNewData(modelArrayList);
        } else {
            mLlOrganizationsLayout.setVisibility(View.GONE);
            mLlOrgsInfo.setVisibility(View.GONE);
        }
    }

    @Override
    public void organizationsFailed(int code, String msg) {
        if (!StringUtils.isEmpty(msg))
            ToastUtils.show(this, msg);
    }

    @OnClick({R.id.repo_layout, R.id.starred_layout, R.id.following_layout, R.id.followers_layout, R.id.organizations_layout})
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
                Intent intent2 = new Intent(this, UserListActivity.class);
                intent2.putExtra(Constants.BundleKey.BUNDLE_USER_NAME, mUsername);
                intent2.setAction(Constants.ActionKey.ACTION_FOLLOWING);
                startActivity(intent2);
                break;

            case R.id.followers_layout:
                Intent intent3 = new Intent(this, UserListActivity.class);
                intent3.putExtra(Constants.BundleKey.BUNDLE_USER_NAME, mUsername);
                intent3.setAction(Constants.ActionKey.ACTION_FOLLOWERS);
                startActivity(intent3);
                break;
            case R.id.organizations_layout:
                mLlOrgsInfo.setVisibility(View.VISIBLE);
                break;
        }
    }

    private BaseQuickAdapter.OnItemClickListener mItemtClickListener = new BaseQuickAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            OrganizationsModel model = mAdapter.getItem(position);
            if (model != null) {
                mUsername = model.getLogin();
                mLlOrgsInfo.setVisibility(View.INVISIBLE);
                mStarredLayout.setVisibility(View.GONE);
                mPresenter.onUserInfo(model.getLogin());
                mPresenter.onOrganizations(model.getLogin());
            }
        }
    };
}
