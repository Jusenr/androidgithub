package com.jusenr.androidgithub.home.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jusenr.androidgithub.R;
import com.jusenr.androidgithub.base.PTMVPActivity;
import com.jusenr.androidgithub.home.contract.RepoListContract;
import com.jusenr.androidgithub.home.di.component.DaggerRepoListComponent;
import com.jusenr.androidgithub.home.di.module.RepoListModule;
import com.jusenr.androidgithub.home.model.model.Repo;
import com.jusenr.androidgithub.home.presenter.RepoListPresenter;
import com.jusenr.androidgithub.home.ui.adapter.RepoListRecyclerAdapter;
import com.jusenr.androidgithub.retrofit.api.RepoApi;
import com.jusenr.androidgithub.utils.AccountHelper;
import com.jusenr.androidgithub.utils.Constants;
import com.jusenr.toolslibrary.utils.ListUtils;
import com.jusenr.toolslibrary.utils.StringUtils;
import com.jusenr.toolslibrary.utils.ToastUtils;

import java.util.ArrayList;

import butterknife.BindView;

public class RepoListActivity extends PTMVPActivity<RepoListPresenter> implements RepoListContract.View {

    @BindView(R.id.rlv_repo_list)
    RecyclerView mRlvRepoList;

    private RepoListRecyclerAdapter mAdapter;

    @Override
    protected void injectComponent() {
        DaggerRepoListComponent.builder()
                .appComponent(mApplication.getAppComponent())
                .repoListModule(new RepoListModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_repo_list;
    }

    @Override
    protected void onViewCreated(@Nullable Bundle savedInstanceState) {
        super.onViewCreated(savedInstanceState);

        initViews();

        String action = getIntent().getAction();
        String username = getIntent().getStringExtra(Constants.BundleKey.BUNDLE_USER_NAME);
        String nickname = AccountHelper.getNickname();
        boolean isSelf = !StringUtils.isEmpty(nickname) && nickname.equalsIgnoreCase(username);

        if (Constants.ActionKey.ACTION_REPOS.equals(action)) {
            setTitle(isSelf ? getString(R.string.my_repositories) : getString(R.string.repositories, username));
            mPresenter.onLoadRepos(username, isSelf, RepoApi.OWNER_REPOS);
        } else if (Constants.ActionKey.ACTION_STARRED_REPOS.equals(action)) {
            setTitle(isSelf ? getString(R.string.my_stars) : getString(R.string.your_stars, username));
            mPresenter.onLoadRepos(username, isSelf, RepoApi.STARRED_REPOS);
        }
    }

    @Override
    public void loadReposResult(ArrayList<Repo> repos) {
        if (!ListUtils.isEmpty(repos)) {
            mAdapter.setNewData(repos);
        }
    }

    @Override
    public void loadReposFailed(int code, String msg) {
        if (!StringUtils.isEmpty(msg))
            ToastUtils.show(this, msg);
    }

    private void initViews() {
        mAdapter = new RepoListRecyclerAdapter(null);
        mAdapter.setOnRecyclerViewItemClickListener(mItemtClickListener);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);
        mRlvRepoList.setAdapter(mAdapter);
    }

    private BaseQuickAdapter.OnRecyclerViewItemClickListener mItemtClickListener = new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            Repo repo = mAdapter.getItem(position);
            if (repo != null) {
                Intent intent = new Intent(mActivity, RepoDetailActivity.class);
                intent.putExtra(Constants.BundleKey.BUNDLE_OWNER, repo.getOwner().getLogin());
                intent.putExtra(Constants.BundleKey.BUNDLE_REPO_NAME, repo.getName());
                startActivity(intent);
            }
        }
    };
}
