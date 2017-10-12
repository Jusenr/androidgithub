package com.jusenr.androidgithub.home.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jusenr.androidgithub.R;
import com.jusenr.androidgithub.base.PTMVPActivity;
import com.jusenr.androidgithub.home.contract.SearchContract;
import com.jusenr.androidgithub.home.di.component.DaggerSearchComponent;
import com.jusenr.androidgithub.home.di.module.SearchModule;
import com.jusenr.androidgithub.home.model.model.Repo;
import com.jusenr.androidgithub.home.presenter.SearchPresenter;
import com.jusenr.androidgithub.home.ui.adapter.RepoListRecyclerAdapter;
import com.jusenr.androidgithub.utils.InputMethodUtils;
import com.jusenr.toolslibrary.log.logger.Logger;
import com.jusenr.toolslibrary.utils.ListUtils;
import com.jusenr.toolslibrary.utils.StringUtils;
import com.jusenr.toolslibrary.utils.ToastUtils;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.mikepenz.devicon_typeface_library.DevIcon;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;

import java.util.ArrayList;

import butterknife.BindView;

public class SearchActivity extends PTMVPActivity<SearchPresenter> implements SearchContract.View {

    @BindView(R.id.repo_list)
    RecyclerView mRepoList;
    @BindView(R.id.search_view)
    MaterialSearchView mSearchView;

    private Drawer mDrawer;
    private RepoListRecyclerAdapter mAdapter;

    @Override
    protected void injectComponent() {
        DaggerSearchComponent.builder()
                .appComponent(mApplication.getAppComponent())
                .searchModule(new SearchModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    public String getLoadingMessage() {
        return getString(R.string.load_searching);
    }

    @Override
    protected void onViewCreated(@Nullable Bundle savedInstanceState) {
        super.onViewCreated(savedInstanceState);

        initViews();
    }

    @Override
    public void searchRepoResult(ArrayList<Repo> repos) {
        if (!ListUtils.isEmpty(repos)) {
            mAdapter.setNewData(repos);
        }
    }

    @Override
    public void searchRepoFailed(int code, String msg) {
        if (!StringUtils.isEmpty(msg))
            ToastUtils.show(this, msg);
    }

    private void initViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle(R.string.search);

        mSearchView.setVoiceSearch(false);
        mSearchView.setSuggestions(getResources().getStringArray(R.array.query_suggestions));
        mSearchView.setOnQueryTextListener(mQueryListener);
        mSearchView.post(new Runnable() {
            @Override
            public void run() {
                mSearchView.showSearch(false);
            }
        });

        mDrawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withTranslucentStatusBar(false)
                .withDisplayBelowStatusBar(true)
                .withActionBarDrawerToggleAnimated(true)
                .withDrawerWidthRes(R.dimen.distance_180dp)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Java").withIcon(DevIcon.Icon.dev_java_plain),
                        new PrimaryDrawerItem().withName("Objective-C").withIcon(DevIcon.Icon.dev_apple_plain),
                        new PrimaryDrawerItem().withName("Swift").withIcon(R.mipmap.ic_swift),
                        new PrimaryDrawerItem().withName("JavaScript").withIcon(DevIcon.Icon.dev_javascript_plain),
                        new PrimaryDrawerItem().withName("Python").withIcon(DevIcon.Icon.dev_python_plain),
                        new PrimaryDrawerItem().withName("HTML").withIcon(DevIcon.Icon.dev_html5_plain),
                        new PrimaryDrawerItem().withName("C#").withIcon(DevIcon.Icon.dev_csharp_plain_wordmark),
                        new PrimaryDrawerItem().withName("C++").withIcon(DevIcon.Icon.dev_cplusplus_plain_wordmark),
                        new PrimaryDrawerItem().withName("Ruby").withIcon(DevIcon.Icon.dev_ruby_plain)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        Logger.d("onItemClick, position = " + position + ", item = " + ((Nameable) drawerItem).getName());

                        mCurrentLang = ((Nameable) drawerItem).getName().toString();
                        search(mCurrentKey, mCurrentLang);
                        mDrawer.closeDrawer();
                        return true;
                    }
                })
                .build();

        mAdapter = new RepoListRecyclerAdapter(null);
        mAdapter.setOnRecyclerViewItemClickListener(mItemtClickListener);

        mRepoList.setAdapter(mAdapter);

        // default is null
        mCurrentLang = "";
    }

    private BaseQuickAdapter.OnRecyclerViewItemClickListener mItemtClickListener = new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            Repo repo = mAdapter.getItem(position);
//            RepoDetailActivity.launch(SearchActivity.this, repo.getOwner().getLogin(), repo.getName());
        }
    };

    private MaterialSearchView.OnQueryTextListener mQueryListener = new MaterialSearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            InputMethodUtils.hideSoftInput(SearchActivity.this);
            mSearchView.closeSearch();

            mCurrentKey = query;
            search(mCurrentKey, mCurrentLang);
            return true;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    };

    private String mCurrentKey;
    private String mCurrentLang;

    private void search(String key, String language) {
        Logger.d("search, key = " + key + ", language = " + language);
        if (!TextUtils.isEmpty(key)) {
            mPresenter.onSearchRepo(key, language);
        }
    }
}
