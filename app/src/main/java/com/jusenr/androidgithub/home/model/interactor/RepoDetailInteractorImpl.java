package com.jusenr.androidgithub.home.model.interactor;

import com.jusenr.androidgithub.home.contract.RepoDetailContract;
import com.jusenr.androidgithub.home.model.model.Content;
import com.jusenr.androidgithub.home.model.model.Repo;
import com.jusenr.androidgithub.home.model.model.RepoDetail;
import com.jusenr.androidgithub.retrofit.RxRetrofitComposer;
import com.jusenr.androidgithub.retrofit.api.RepoApi;
import com.jusenr.androidgithub.user.model.model.UserModel;
import com.jusenr.androidgithub.utils.AccountHelper;
import com.jusenr.androidgithub.utils.Constants;
import com.jusenr.androidgithub.utils.Utils;
import com.jusenr.androidlibrary.di.scope.ActivityScope;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.functions.Func5;
import rx.schedulers.Schedulers;

@ActivityScope
public class RepoDetailInteractorImpl implements RepoDetailContract.Interactor {

    private RepoApi repoApi;

    @Inject
    public RepoDetailInteractorImpl(RepoApi repoApi) {
        this.repoApi = repoApi;
    }

    @Override
    public void onDestroy() {

    }

    private Observable<Boolean> isStarred(String owner, final String repo) {
        return repoApi.checkIfRepoIsStarred(AccountHelper.getCurrentUid(), owner, repo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<Response, Boolean>() {
                    @Override
                    public Boolean call(Response response) {
                        /**
                         * Response if this repository is starred by you
                         *  Status: 204 No Content
                         * Response if this repository is not starred by you
                         *  Status: 404 Not Found
                         */
                        return response != null && response.code() == 204;
                    }
                });
    }

    @Override
    public Observable<RepoDetail> getRepoDetail(String owner, String repo) {
        return Observable.zip(repoApi.get(AccountHelper.getCurrentUid(), owner, repo),
                repoApi.contributors(AccountHelper.getCurrentUid(), owner, repo),
                repoApi.listForks(AccountHelper.getCurrentUid(), owner, repo, Constants.Value.NEWEST),
                repoApi.readme(AccountHelper.getCurrentUid(), owner, repo),
                isStarred(owner, repo),
                new Func5<Repo, ArrayList<UserModel>, ArrayList<Repo>, Content, Boolean, RepoDetail>() {
                    @Override
                    public RepoDetail call(Repo repo, ArrayList<UserModel> users, ArrayList<Repo> forks, Content readme, Boolean isStarred) {
                        RepoDetail detail = new RepoDetail();
                        repo.setStarred(isStarred);
                        detail.setBaseRepo(repo);
                        detail.setForks(forks);

                        // because the readme content is encode with Base64 by github.
                        readme.content = Utils.base64Decode(readme.content);
                        detail.setReadme(readme);
                        detail.setContributors(users);
                        return detail;
                    }
                }).compose(RxRetrofitComposer.<RepoDetail>applySchedulers());
    }

    @Override
    public Observable<Boolean> starRepo(String owner, String repo) {
        return repoApi.starRepo(AccountHelper.getCurrentUid(), owner, repo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<Response, Boolean>() {
                    @Override
                    public Boolean call(Response response) {
                        return response != null && response.code() == 204;
                    }
                });
    }

    @Override
    public Observable<Boolean> unstarRepo(String owner, String repo) {
        return repoApi.unstarRepo(AccountHelper.getCurrentUid(), owner, repo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<Response, Boolean>() {
                    @Override
                    public Boolean call(Response response) {
                        return response != null && response.code() == 204;
                    }
                });
    }
}