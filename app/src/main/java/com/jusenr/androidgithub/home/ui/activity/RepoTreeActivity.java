package com.jusenr.androidgithub.home.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jusenr.androidgithub.R;
import com.jusenr.androidgithub.base.PTMVPActivity;
import com.jusenr.androidgithub.home.contract.RepoTreeContract;
import com.jusenr.androidgithub.home.di.component.DaggerRepoTreeComponent;
import com.jusenr.androidgithub.home.di.module.RepoTreeModule;
import com.jusenr.androidgithub.home.model.model.Content;
import com.jusenr.androidgithub.home.presenter.RepoTreePresenter;
import com.jusenr.androidgithub.home.ui.adapter.RepoContentAdapter;
import com.jusenr.androidgithub.utils.Constants;
import com.jusenr.androidgithub.widgets.LinearBreadcrumbView;
import com.jusenr.toolslibrary.utils.ListUtils;
import com.jusenr.toolslibrary.utils.StringUtils;
import com.jusenr.toolslibrary.utils.ToastUtils;

import java.util.ArrayList;

import butterknife.BindView;

public class RepoTreeActivity extends PTMVPActivity<RepoTreePresenter> implements RepoTreeContract.View {

    @BindView(R.id.brv_repo_tree)
    RecyclerView mRepoTree;
    @BindView(R.id.lbv_path_view)
    LinearBreadcrumbView mPathView;

    private RepoContentAdapter mAdapter;

    private String mOwner;
    private String mRepoName;

    @Override
    protected void injectComponent() {
        DaggerRepoTreeComponent.builder()
                .appComponent(mApplication.getAppComponent())
                .repoTreeModule(new RepoTreeModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_repo_tree;
    }


    @Override
    protected void onViewCreated(@Nullable Bundle savedInstanceState) {
        super.onViewCreated(savedInstanceState);

        initViews();

        mOwner = getIntent().getStringExtra(Constants.BundleKey.BUNDLE_OWNER);
        mRepoName = getIntent().getStringExtra(Constants.BundleKey.BUNDLE_REPO_NAME);

        setTitle(mOwner + "/" + mRepoName);

        mPresenter.onRepoContents(mOwner, mRepoName, null);
    }

    @Override
    public void repoContentsResult(ArrayList<Content> contents) {
        if (!ListUtils.isEmpty(contents)) {
            mAdapter.setNewData(contents);
        }
    }

    @Override
    public void repoContentsFailed(int code, String msg) {
        if (!StringUtils.isEmpty(msg))
            ToastUtils.show(this, msg);
    }

    private void initViews() {
        mPathView.initRootCrumb();
        mPathView.setCallback(mPathSelectionCallback);

        mAdapter = new RepoContentAdapter(null);
        mAdapter.setOnItemClickListener(mItemClickListener);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        mRepoTree.setAdapter(mAdapter);
    }

    private BaseQuickAdapter.OnItemClickListener mItemClickListener = new BaseQuickAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            Content content = mAdapter.getItem(position);
            if (content != null) {
                if (content.isDir()) {
                    mPathView.addCrumb(new LinearBreadcrumbView.Crumb(content.path, ""), true);
                    mPresenter.onRepoContents(mOwner, mRepoName, content.path);
                } else if (content.isFile()) {
                    Intent intent = new Intent(mActivity, CodeActivity.class);
                    intent.putExtra(Constants.BundleKey.BUNDLE_OWNER, mOwner);
                    intent.putExtra(Constants.BundleKey.BUNDLE_REPO_NAME, mRepoName);
                    intent.putExtra(Constants.BundleKey.BUNDLE_CODE, content.path);
                    startActivity(intent);
                }
            }
        }
    };

    private LinearBreadcrumbView.SelectionCallback mPathSelectionCallback = new LinearBreadcrumbView.SelectionCallback() {
        @Override
        public void onCrumbSelection(LinearBreadcrumbView.Crumb crumb, String absolutePath, int count, int index) {

        }
    };
}
