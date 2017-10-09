package com.jusenr.androidgithub.home.model.interactor;


import android.text.TextUtils;

import com.jusenr.androidgithub.home.contract.SearchContract;
import com.jusenr.androidgithub.home.model.model.Repo;
import com.jusenr.androidgithub.home.model.model.SearchResultResp;
import com.jusenr.androidgithub.retrofit.RxRetrofitComposer;
import com.jusenr.androidgithub.retrofit.api.RepoApi;
import com.jusenr.androidgithub.utils.AccountHelper;
import com.jusenr.androidgithub.utils.Constants;
import com.jusenr.androidlibrary.di.scope.ActivityScope;

import java.util.ArrayList;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

@ActivityScope
public class SearchInteractorImpl implements SearchContract.Interactor {

    private RepoApi repoApi;

    @Inject
    public SearchInteractorImpl(RepoApi repoApi) {
        this.repoApi = repoApi;
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public Observable<ArrayList<Repo>> searchRepo(String key, String language) {
        // base on https://developer.github.com/v3/search/#search-repositories
        // build to query params.
        StringBuilder queryParams = new StringBuilder().append(key);

        if (!TextUtils.isEmpty(language)) {
            queryParams.append("+language:");
            queryParams.append(language);
        }

        return repoApi.searchRepo(AccountHelper.getCurrentUid(), queryParams.toString(), Constants.Value.SORT_BY_STARS, Constants.Value.ORDER_BY_DESC, 1, Constants.Value.PAGE_SIZE)
                .compose(RxRetrofitComposer.<SearchResultResp>applySchedulers())
                .map(new Func1<SearchResultResp, ArrayList<Repo>>() {
                    @Override
                    public ArrayList<Repo> call(SearchResultResp searchResultResp) {
                        return searchResultResp.getItems();
                    }
                });
    }
}