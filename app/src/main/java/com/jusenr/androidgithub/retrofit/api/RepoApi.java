package com.jusenr.androidgithub.retrofit.api;

import android.support.annotation.IntDef;

import com.jusenr.androidgithub.home.model.model.Content;
import com.jusenr.androidgithub.home.model.model.Repo;
import com.jusenr.androidgithub.home.model.model.SearchResultResp;
import com.jusenr.androidgithub.user.model.model.UserModel;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by mingjun on 16/7/18.
 */
public interface RepoApi {
    public int OWNER_REPOS = 1;
    public int STARRED_REPOS = 2;
    public int ORG_REPOS = 3;

    @IntDef({
            OWNER_REPOS,
            STARRED_REPOS,
            ORG_REPOS
    })
    @interface RepoType {
    }

    @Headers("Cache-Control: public, max-age=600")
    @GET("search/repositories")
    Observable<SearchResultResp> searchRepo(@Header("Authorization") String authorization, @Query("q") String key, @Query("sort") String sort,
                                            @Query("order") String order, @Query("page") int page,
                                            @Query("per_page") int pageSize);

    @GET("/users/{user}")
    Observable<UserModel> getSingleUser(@Header("Authorization") String authorization, @Path("user") String user);

    @GET("repos/{owner}/{name}")
    Observable<Repo> get(@Header("Authorization") String authorization, @Path("owner") String owner, @Path("name") String repo);


    @GET("repos/{owner}/{name}/contributors")
    Observable<ArrayList<UserModel>> contributors(@Header("Authorization") String authorization, @Path("owner") String owner, @Path("name") String repo);


    @GET("repos/{owner}/{name}/readme")
    Observable<Content> readme(@Header("Authorization") String authorization, @Path("owner") String owner, @Path("name") String repo);


    @GET("repos/{owner}/{name}/forks")
    Observable<ArrayList<Repo>> listForks(@Header("Authorization") String authorization, @Path("owner") String owner, @Path("name") String repo,
                                          @Query("sort") String sort);

    @GET("user/repos")
    Observable<ArrayList<Repo>> getMyRepos(@Header("Authorization") String authorization, @Query("sort") String sort, @Query("type") String type);

    @GET("users/{name}/repos")
    Observable<ArrayList<Repo>> getUserRepos(@Header("Authorization") String authorization, @Path("name") String user, @Query("sort") String sort);

    @GET("user/starred")
    Observable<ArrayList<Repo>> getMyStarredRepos(@Header("Authorization") String authorization, @Query("sort") String sort);

    @GET("users/{name}/starred")
    Observable<ArrayList<Repo>> getUserStarredRepos(@Header("Authorization") String authorization, @Path("name") String user, @Query("sort") String sort);

    @Headers("Content-Length: 0")
    @PUT("/user/starred/{owner}/{repo}")
    Observable<Response<ResponseBody>> starRepo(@Header("Authorization") String authorization, @Path("owner") String owner, @Path("repo") String repo);

    @GET("/user/starred/{owner}/{repo}")
    Observable<Response<ResponseBody>> checkIfRepoIsStarred(@Header("Authorization") String authorization, @Path("owner") String owner, @Path("repo") String repo);

    @DELETE("/user/starred/{owner}/{repo}")
    Observable<Response<ResponseBody>> unstarRepo(@Header("Authorization") String authorization, @Path("owner") String owner, @Path("repo") String repo);

    @GET("/repos/{owner}/{repo}/contents")
    Observable<ArrayList<Content>> contents(@Header("Authorization") String authorization, @Path("owner") String owner, @Path("repo") String repo);

    @GET("/repos/{owner}/{repo}/contents")
    Observable<ArrayList<Content>> contentsByRef(@Header("Authorization") String authorization, @Path("owner") String owner, @Path("repo") String repo,
                                                 @Query("ref") String ref);

    @GET("/repos/{owner}/{repo}/contents/{path}")
    Observable<ArrayList<Content>> contentsWithPath(@Header("Authorization") String authorization, @Path("owner") String owner, @Path("repo") String repo,
                                                    @Path("path") String path);

    @GET("/repos/{owner}/{repo}/contents/{path}")
    Observable<ArrayList<Content>> contentsWithPathByRef(@Header("Authorization") String authorization, @Path("owner") String owner, @Path("repo") String repo,
                                                         @Path("path") String path, @Query("ref") String ref);

    @GET("/repos/{owner}/{repo}/contents/{path}")
    Observable<Content> contentDetail(@Header("Authorization") String authorization, @Path("owner") String owner, @Path("repo") String repo,
                                      @Path("path") String path);

    @GET("/repos/{owner}/{repo}/contents/{path}")
    Observable<Content> contentDetailByRef(@Header("Authorization") String authorization, @Path("owner") String owner, @Path("repo") String repo,
                                           @Path("path") String path, @Query("ref") String ref);

    @GET("/users/{user}/following")
    Observable<ArrayList<UserModel>> getUserFollowing(@Header("Authorization") String authorization, @Path("user") String user);

    @GET("/user/following")
    Observable<ArrayList<UserModel>> getMyFollowing(@Header("Authorization") String authorization);

    @GET("/users/{user}/followers")
    Observable<ArrayList<UserModel>> getUserFollowers(@Header("Authorization") String authorization, @Path("user") String user);

    @GET("/user/followers")
    Observable<ArrayList<UserModel>> getMyFollowers(@Header("Authorization") String authorization);
}
