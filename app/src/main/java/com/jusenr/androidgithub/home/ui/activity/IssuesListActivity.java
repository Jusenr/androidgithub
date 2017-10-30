package com.jusenr.androidgithub.home.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jusenr.androidgithub.R;
import com.jusenr.androidgithub.base.PTMVPActivity;
import com.jusenr.androidgithub.home.contract.IssuesListContract;
import com.jusenr.androidgithub.home.di.component.DaggerIssuesListComponent;
import com.jusenr.androidgithub.home.di.module.IssuesListModule;
import com.jusenr.androidgithub.home.model.model.IssuesModel;
import com.jusenr.androidgithub.home.presenter.IssuesListPresenter;
import com.jusenr.androidgithub.home.ui.adapter.IssuesListRecyclerAdapter;
import com.jusenr.androidgithub.utils.Constants;
import com.jusenr.toolslibrary.utils.ListUtils;
import com.jusenr.toolslibrary.utils.StringUtils;
import com.jusenr.toolslibrary.utils.ToastUtils;

import java.util.ArrayList;

import butterknife.BindView;

public class IssuesListActivity extends PTMVPActivity<IssuesListPresenter> implements IssuesListContract.View {

    @BindView(R.id.rlv_issues_list)
    RecyclerView mRlvIssuesList;

    private IssuesListRecyclerAdapter mAdapter;

    private String mOwner;
    private String mRepoName;

    @Override
    protected void injectComponent() {
        DaggerIssuesListComponent.builder()
                .appComponent(mApplication.getAppComponent())
                .issuesListModule(new IssuesListModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_issues_list;
    }


    @Override
    protected void onViewCreated(@Nullable Bundle savedInstanceState) {
        super.onViewCreated(savedInstanceState);

        mAdapter = new IssuesListRecyclerAdapter(null);
        mAdapter.setOnItemClickListener(mItemtClickListener);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        mRlvIssuesList.setAdapter(mAdapter);

        mOwner = getIntent().getStringExtra(Constants.BundleKey.BUNDLE_OWNER);
        mRepoName = getIntent().getStringExtra(Constants.BundleKey.BUNDLE_REPO_NAME);

        if (!StringUtils.isEmpty(mOwner) && !StringUtils.isEmpty(mRepoName)) {
            mPresenter.onLoadIssues(mOwner, mRepoName);
        }
    }

    @Override
    public void loadIssuesResult(ArrayList<IssuesModel> issues) {
        if (!ListUtils.isEmpty(issues)) {
            mAdapter.setNewData(issues);
        }
    }

    @Override
    public void loadIssuesFailed(int code, String msg) {
        if (!StringUtils.isEmpty(msg))
            ToastUtils.show(this, msg);
    }

    private BaseQuickAdapter.OnItemClickListener mItemtClickListener = new BaseQuickAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            IssuesModel issues = mAdapter.getItem(position);
            if (issues != null) {
//                Intent intent = new Intent(mActivity, RepoDetailActivity.class);
//                intent.putExtra(Constants.BundleKey.BUNDLE_OWNER, repo.getOwner().getLogin());
//                intent.putExtra(Constants.BundleKey.BUNDLE_REPO_NAME, repo.getName());
//                startActivity(intent);
            }
        }
    };
}
