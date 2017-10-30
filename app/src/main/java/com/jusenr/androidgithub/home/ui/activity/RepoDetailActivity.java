package com.jusenr.androidgithub.home.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jusenr.androidgithub.R;
import com.jusenr.androidgithub.base.PTMVPActivity;
import com.jusenr.androidgithub.home.contract.RepoDetailContract;
import com.jusenr.androidgithub.home.di.component.DaggerRepoDetailComponent;
import com.jusenr.androidgithub.home.di.module.RepoDetailModule;
import com.jusenr.androidgithub.home.model.model.Repo;
import com.jusenr.androidgithub.home.model.model.RepoDetail;
import com.jusenr.androidgithub.home.presenter.RepoDetailPresenter;
import com.jusenr.androidgithub.home.ui.adapter.ContributorListAdapter;
import com.jusenr.androidgithub.home.ui.adapter.ForkUserListAdapter;
import com.jusenr.androidgithub.user.model.model.UserModel;
import com.jusenr.androidgithub.user.ui.activity.UserActivity;
import com.jusenr.androidgithub.utils.AccountHelper;
import com.jusenr.androidgithub.utils.Constants;
import com.jusenr.androidgithub.widgets.RepoItemView;
import com.jusenr.androidlibrary.widgets.recyclerView.BaseRecyclerView;
import com.jusenr.toolslibrary.utils.StringUtils;
import com.jusenr.toolslibrary.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class RepoDetailActivity extends PTMVPActivity<RepoDetailPresenter> implements RepoDetailContract.View {

    @BindView(R.id.ll_root_layout)
    LinearLayout mLlRootLayout;
    @BindView(R.id.riv_repo_item_view)
    RepoItemView mRivRepoItemView;
    @BindView(R.id.contributors_count)
    TextView mContributorsCount;
    @BindView(R.id.rlv_contributor_list)
    BaseRecyclerView mRlvContributorList;
    @BindView(R.id.ll_contributor_layout)
    LinearLayout mLlContributorLayout;
    @BindView(R.id.tv_forks_count)
    TextView mTvForksCount;
    @BindView(R.id.rlv_fork_list)
    BaseRecyclerView mRlvForkList;
    @BindView(R.id.ll_fork_layout)
    LinearLayout mLlForkLayout;
    @BindView(R.id.tv_code_label)
    TextView mTvCodeLabel;
    @BindView(R.id.ll_code_layout)
    LinearLayout mLlCodeLayout;
    @BindView(R.id.tv_issues_count)
    TextView mTvIssuesCount;
    @BindView(R.id.ll_issues_layout)
    LinearLayout mLlIssuesLayout;
    @BindView(R.id.tv_readme_label)
    TextView mTvReadmeLabel;
    @BindView(R.id.ll_readme_layout)
    LinearLayout mLlReadmeLayout;

    private ForkUserListAdapter mForkUserAdapter;
    private ContributorListAdapter mContributorAdapter;

    private String mRepoName;
    private String mOwner;
    private RepoDetail mRepoDetail;

    @Override
    protected void injectComponent() {
        DaggerRepoDetailComponent.builder()
                .appComponent(mApplication.getAppComponent())
                .repoDetailModule(new RepoDetailModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_repo_detail;
    }

    @Override
    protected void onViewCreated(@Nullable Bundle savedInstanceState) {
        super.onViewCreated(savedInstanceState);

        initViews();

        mOwner = getIntent().getStringExtra(Constants.BundleKey.BUNDLE_OWNER);
        mRepoName = getIntent().getStringExtra(Constants.BundleKey.BUNDLE_REPO_NAME);
        if (!TextUtils.isEmpty(mOwner) && !TextUtils.isEmpty(mRepoName)) {
            mPresenter.onLoadRepoDetails(mOwner, mRepoName);
        }
    }

    @Override
    public void loadRepoDetailsResult(RepoDetail detail) {
        mLlRootLayout.setVisibility(View.VISIBLE);
        mRepoDetail = detail;
        mOwner = detail.getBaseRepo().getOwner().getLogin();
        mRepoName = detail.getBaseRepo().getName();

        setTitle(mRepoName);

        mRivRepoItemView.setRepo(detail.getBaseRepo());
        mRivRepoItemView.setRepoActionListener(mRepoActionListener);

        int forks = detail.getBaseRepo().getForks_count();
        if (forks == 0) {
            mLlForkLayout.setVisibility(View.GONE);
        } else {
            mLlForkLayout.setVisibility(View.VISIBLE);
            mTvForksCount.setText(getResources().getString(R.string.count, forks));
            mForkUserAdapter.setNewData(detail.getForks());
        }

        boolean has_issues = detail.getBaseRepo().isHas_issues();
        if (has_issues) {
            mLlIssuesLayout.setVisibility(View.VISIBLE);
            mTvIssuesCount.setText(getResources().getString(R.string.count, detail.getBaseRepo().getOpen_issues_count()));
        } else {
            mLlIssuesLayout.setVisibility(View.GONE);
        }

        mContributorsCount.setText(getResources().getString(R.string.count, detail.getContributors().size()));
        mContributorAdapter.setNewData(detail.getContributors());
    }

    @Override
    public void loadRepoDetailsFailed(int code, String msg) {
        if (!StringUtils.isEmpty(msg))
            ToastUtils.show(this, msg);
    }

    @Override
    public void starRepoResult(boolean b) {
        Snackbar.make(mRivRepoItemView, b ? "Star Success" : "Star Failed", Snackbar.LENGTH_LONG).show();
        if (b)
            mPresenter.onLoadRepoDetails(mOwner, mRepoName);
    }

    @Override
    public void unstarRepoResult(boolean b) {
        Snackbar.make(mRivRepoItemView, b ? "UnStar Success" : "UnStar Failed", Snackbar.LENGTH_LONG).show();
        if (b)
            mPresenter.onLoadRepoDetails(mOwner, mRepoName);
    }

    @OnClick({R.id.ll_code_layout, R.id.ll_issues_layout, R.id.ll_readme_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_code_layout:
                Intent intent = new Intent(mActivity, RepoTreeActivity.class);
                intent.putExtra(Constants.BundleKey.BUNDLE_OWNER, mOwner);
                intent.putExtra(Constants.BundleKey.BUNDLE_REPO_NAME, mRepoName);
                startActivity(intent);
                break;
            case R.id.ll_issues_layout:
                Intent intent1 = new Intent(mActivity, IssuesListActivity.class);
                intent1.putExtra(Constants.BundleKey.BUNDLE_OWNER, mOwner);
                intent1.putExtra(Constants.BundleKey.BUNDLE_REPO_NAME, mRepoName);
                startActivity(intent1);
                break;
            case R.id.ll_readme_layout:
                Intent intent2 = new Intent(mActivity, ReadmeActivity.class);
                intent2.putExtra(Constants.BundleKey.BUNDLE_README, mRepoDetail.getReadme());
                startActivity(intent2);
                break;
        }
    }

    private void initViews() {
        mLlRootLayout.setVisibility(View.INVISIBLE);
        mContributorAdapter = new ContributorListAdapter(null);
        mContributorAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                UserModel user = mContributorAdapter.getItem(position);
                if (user != null) {
                    launchUser(user.getLogin());
                }
            }
        });
        mRlvContributorList.setAdapter(mContributorAdapter);

        mForkUserAdapter = new ForkUserListAdapter(null);
        mForkUserAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Repo repo = mForkUserAdapter.getItem(position);
                if (repo != null && repo.getOwner() != null) {
                    launchUser(repo.getOwner().getLogin());
                }
            }
        });
        mRlvForkList.setAdapter(mForkUserAdapter);
    }

    private RepoItemView.RepoActionListener mRepoActionListener = new RepoItemView.RepoActionListener() {
        @Override
        public void onStarAction(Repo repo) {
            if (AccountHelper.isLogin() && repo != null) {
                mPresenter.onStarRepo(repo.getOwner().getLogin(), repo.getName());
            }
        }

        @Override
        public void onUnstarAction(Repo repo) {
            if (AccountHelper.isLogin() && repo != null) {
                mPresenter.onUnstarRepo(repo.getOwner().getLogin(), repo.getName());
            }
        }

        @Override
        public void onUserAction(String username) {
            launchUser(username);
        }
    };

    private void launchUser(String username) {
        if (!StringUtils.isEmpty(username)) {
            Intent intent = new Intent(mActivity, UserActivity.class);
            intent.putExtra(Constants.BundleKey.BUNDLE_USER_NAME, username);
            startActivity(intent);
        }
    }
}
