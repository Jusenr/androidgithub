package com.jusenr.androidgithub.home.model.interactor;

import com.jusenr.androidgithub.home.contract.MostStarContract;
import com.jusenr.androidgithub.home.model.model.Repo;
import com.jusenr.androidgithub.home.model.model.SearchResultResp;
import com.jusenr.androidgithub.retrofit.api.RepoApi;
import com.jusenr.androidgithub.utils.AccountHelper;
import com.jusenr.androidgithub.utils.Constants;
import com.jusenr.androidlibrary.di.scope.FragmentScope;
import com.jusenr.toolslibrary.log.logger.Logger;

import java.util.ArrayList;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

@FragmentScope
public class MostStarInteractorImpl implements MostStarContract.Interactor {

    private RepoApi repoApi;

    @Inject
    public MostStarInteractorImpl(RepoApi repoApi) {
        this.repoApi = repoApi;
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public Observable<ArrayList<Repo>> top30StarsRepo(@RepoApi.MostStarsType int type) {
        // base on https://developer.github.com/v3/search/#search-repositories
        // build to query params.
        StringBuilder queryParams = new StringBuilder();

        String key = null;
        String[] lang = null;
        switch (type) {
            case RepoApi.TYPE_ANDROID:
                key = "android";
                lang = new String[]{"java"};
                break;
            case RepoApi.TYPE_IOS:
                key = "ios";
                lang = new String[]{"Swift", "Objective-C"};
                break;
            case RepoApi.TYPE_PYTHON:
                key = "python";
                lang = new String[]{"python"};
                break;
            case RepoApi.TYPE_WEB:
                key = "web";
                lang = new String[]{"HTML", "CSS", "JavaScript"};
                break;
            case RepoApi.TYPE_PHP:
                key = "php";
                lang = new String[]{"PHP"};
                break;
        }

        queryParams.append(key);
        if (lang != null && lang.length > 0) {
            for (String language : lang) {
                queryParams.append("+language:");
                queryParams.append(language);
            }
        }

        Logger.d("getTop30StarsRepo, q:" + queryParams.toString());

        // we get the most starred 30 repos.
        return repoApi.searchRepo(AccountHelper.getCurrentUid(), queryParams.toString(), Constants.Value.SORT_BY_STARS, Constants.Value.ORDER_BY_DESC, 1, Constants.Value.PAGE_SIZE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<SearchResultResp, ArrayList<Repo>>() {
                    @Override
                    public ArrayList<Repo> call(SearchResultResp searchResultResp) {
                        return searchResultResp.getItems();
                    }
                });
    }
}