package com.jusenr.androidgithub.home.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.github.clans.fab.FloatingActionMenu;
import com.jusenr.androidgithub.R;
import com.jusenr.androidgithub.base.PTMVPFragment;
import com.jusenr.androidgithub.home.contract.MostStarContract;
import com.jusenr.androidgithub.home.di.component.DaggerMostStarComponent;
import com.jusenr.androidgithub.home.di.module.MostStarModule;
import com.jusenr.androidgithub.home.model.model.Repo;
import com.jusenr.androidgithub.home.presenter.MostStarPresenter;
import com.jusenr.androidgithub.home.ui.activity.RepoDetailActivity;
import com.jusenr.androidgithub.home.ui.adapter.RepoListRecyclerAdapter;
import com.jusenr.androidgithub.retrofit.api.RepoApi;
import com.jusenr.androidgithub.utils.Constants;
import com.jusenr.toolslibrary.log.logger.Logger;
import com.jusenr.toolslibrary.utils.ListUtils;
import com.jusenr.toolslibrary.utils.StringUtils;
import com.jusenr.toolslibrary.utils.ToastUtils;

import java.util.ArrayList;

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
public class MostStarFragment extends PTMVPFragment<MostStarPresenter> implements MostStarContract.View {

    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.brv_repo_list)
    RecyclerView mRepoListView;
    @BindView(R.id.fab_menu)
    FloatingActionMenu mFloatMenu;

    private RepoListRecyclerAdapter mAdapter;

    // default is java
    private int mCurrentType = RepoApi.TYPE_ANDROID;

    @Override
    protected void injectComponent() {
        DaggerMostStarComponent.builder()
                .appComponent(mApplication.getAppComponent())
                .mostStarModule(new MostStarModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_most_star;
    }

    @Override
    public void onViewCreateFinish(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreateFinish(view, savedInstanceState);

        mRefreshLayout.setOnRefreshListener(mRefreshListener);

        mAdapter = new RepoListRecyclerAdapter(null);
        mAdapter.setOnItemClickListener(mItemtClickListener);
        mAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        mRepoListView.setAdapter(mAdapter);

        mPresenter.onLoadMostStars(mCurrentType);
    }

    @Override
    public void top30StarsRepoResult(ArrayList<Repo> repos) {
        if (!ListUtils.isEmpty(repos)) {
            mAdapter.setNewData(repos);
        }
    }

    @Override
    public void top30StarsRepoFailed(int code, String msg) {
        if (!StringUtils.isEmpty(msg))
            ToastUtils.show(mActivity, msg);
    }

    @Override
    public void showLoading() {
        mRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.setRefreshing(true);
            }
        });
    }

    @Override
    public void dismissLoading() {
        mRefreshLayout.setRefreshing(false);
    }

    @OnClick({R.id.fab_repo_android, R.id.fab_repo_ios, R.id.fab_repo_python, R.id.fab_repo_web, R.id.fab_repo_php})
    public void onLangMenuClick(View view) {
        mFloatMenu.close(true);
        switch (view.getId()) {
            case R.id.fab_repo_android:
                mCurrentType = RepoApi.TYPE_ANDROID;
                break;
            case R.id.fab_repo_ios:
                mCurrentType = RepoApi.TYPE_IOS;
                break;
            case R.id.fab_repo_python:
                mCurrentType = RepoApi.TYPE_PYTHON;
                break;
            case R.id.fab_repo_web:
                mCurrentType = RepoApi.TYPE_WEB;
                break;
            case R.id.fab_repo_php:
                mCurrentType = RepoApi.TYPE_PHP;
                break;
        }
        mPresenter.onLoadMostStars(mCurrentType);
    }

    private BaseQuickAdapter.OnItemClickListener mItemtClickListener = new BaseQuickAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            Repo repo = mAdapter.getItem(position);
            if (repo != null) {
                Intent intent = new Intent(mActivity, RepoDetailActivity.class);
                intent.putExtra(Constants.BundleKey.BUNDLE_OWNER, repo.getOwner().getLogin());
                intent.putExtra(Constants.BundleKey.BUNDLE_REPO_NAME, repo.getName());
                startActivity(intent);
            }
        }
    };

    private SwipeRefreshLayout.OnRefreshListener mRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            Logger.d("onRefresh, mCurrentType:" + mCurrentType);
            mPresenter.onLoadMostStars(mCurrentType);
        }
    };
}
