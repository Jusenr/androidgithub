package com.jusenr.androidgithub.retrofit.api;

import android.support.annotation.IntDef;

import com.jusenr.androidgithub.home.model.model.Content;
import com.jusenr.androidgithub.home.model.model.IssuesModel;
import com.jusenr.androidgithub.home.model.model.Repo;
import com.jusenr.androidgithub.home.model.model.SearchResultResp;
import com.jusenr.androidgithub.user.model.model.OrganizationsModel;
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
    int TYPE_ANDROID = 1;
    int TYPE_IOS = 2;
    int TYPE_WEB = 3;
    int TYPE_PYTHON = 4;
    int TYPE_PHP = 5;

    @IntDef({
            TYPE_ANDROID,
            TYPE_IOS,
            TYPE_WEB,
            TYPE_PYTHON,
            TYPE_PHP
    })
    @interface MostStarsType {
    }

    int OWNER_REPOS = 1;
    int STARRED_REPOS = 2;
    int ORG_REPOS = 3;

    @IntDef({
            OWNER_REPOS,
            STARRED_REPOS,
            ORG_REPOS
    })
    @interface RepoType {
    }

    int FOLLOWING = 1;
    int FOLLOWER = 2;

    @IntDef({
            FOLLOWING,
            FOLLOWER
    })
    @interface UserType {
    }

    /**
     * 搜索项目
     *
     * @param authorization 授权账号信息
     * @param key           类型
     * @param sort          排序依据[stars]stars次数降序
     * @param order         [desc]
     * @param page          页数[1]
     * @param pageSize      每页显示数据条数[30]
     * @return
     */
    @Headers("Cache-Control: public, max-age=600")
    @GET("search/repositories")
    Observable<SearchResultResp> searchRepo(@Header("Authorization") String authorization,
                                            @Query("q") String key,
                                            @Query("sort") String sort,
                                            @Query("order") String order,
                                            @Query("page") int page,
                                            @Query("per_page") int pageSize);

    /**
     * @param authorization 授权账号信息
     * @param user          注册的用户名
     * @return
     */
    @GET("/users/{user}")
    Observable<UserModel> getSingleUser(@Header("Authorization") String authorization,
                                        @Path("user") String user);

    /**
     * 获取我的项目列表
     *
     * @param authorization 授权账号信息
     * @param sort          排序依据[updated]最后更新时间倒序
     * @param type          类型[all]所有仓库
     * @return
     */
    @GET("user/repos")
    Observable<ArrayList<Repo>> getMyRepos(@Header("Authorization") String authorization,
                                           @Query("sort") String sort,
                                           @Query("type") String type);

    /**
     * 获取用户/组织项目列表
     *
     * @param authorization 授权账号信息
     * @param user          注册的用户名
     * @param sort          排序依据[updated]最后更新时间倒序
     * @return
     */
    @GET("users/{name}/repos")
    Observable<ArrayList<Repo>> getUserRepos(@Header("Authorization") String authorization,
                                             @Path("name") String user,
                                             @Query("sort") String sort);

    /**
     * 获取我star的项目列表
     *
     * @param authorization 授权账号信息
     * @param sort          排序依据[updated]最后更新时间倒序
     * @return
     */
    @GET("user/starred")
    Observable<ArrayList<Repo>> getMyStarredRepos(@Header("Authorization") String authorization,
                                                  @Query("sort") String sort);

    /**
     * 获取用户star的项目列表
     *
     * @param authorization 授权账号信息
     * @param user          注册的用户名
     * @param sort          排序依据[updated]最后更新时间倒序
     * @return
     */
    @GET("users/{name}/starred")
    Observable<ArrayList<Repo>> getUserStarredRepos(@Header("Authorization") String authorization,
                                                    @Path("name") String user,
                                                    @Query("sort") String sort);

    /**
     * @param authorization 授权账号信息
     * @param owner
     * @param repo
     * @return
     */
    @GET("repos/{owner}/{name}")
    Observable<Repo> get(@Header("Authorization") String authorization,
                         @Path("owner") String owner,
                         @Path("name") String repo);

    /**
     * @param authorization 授权账号信息
     * @param owner
     * @param repo
     * @return
     */
    @GET("repos/{owner}/{name}/contributors")
    Observable<ArrayList<UserModel>> contributors(@Header("Authorization") String authorization,
                                                  @Path("owner") String owner,
                                                  @Path("name") String repo);

    /**
     * 获取Forks列表
     *
     * @param authorization 授权账号信息
     * @param owner
     * @param repo
     * @param sort          排序依据[newest]最新倒序
     * @return
     */
    @GET("repos/{owner}/{name}/forks")
    Observable<ArrayList<Repo>> listForks(@Header("Authorization") String authorization,
                                          @Path("owner") String owner,
                                          @Path("name") String repo,
                                          @Query("sort") String sort);

    /**
     * 获取README信息
     *
     * @param authorization 授权账号信息
     * @param owner
     * @param repo
     * @return
     */
    @GET("repos/{owner}/{name}/readme")
    Observable<Content> readme(@Header("Authorization") String authorization,
                               @Path("owner") String owner,
                               @Path("name") String repo);

    /**
     * 检测该项目是否star
     *
     * @param authorization 授权账号信息
     * @param owner
     * @param repo
     * @return
     */
    @GET("/user/starred/{owner}/{repo}")
    Observable<Response<ResponseBody>> checkIfRepoIsStarred(@Header("Authorization") String authorization,
                                                            @Path("owner") String owner,
                                                            @Path("repo") String repo);

    /**
     * 获取Following列表
     *
     * @param authorization 授权账号信息
     * @param user          注册的用户名
     * @return
     */
    @GET("/users/{user}/following")
    Observable<ArrayList<UserModel>> getUserFollowing(@Header("Authorization") String authorization,
                                                      @Path("user") String user);

    /**
     * 获取我的Following列表
     *
     * @param authorization 授权账号信息
     * @return
     */
    @GET("/user/following")
    Observable<ArrayList<UserModel>> getMyFollowing(@Header("Authorization") String authorization);

    /**
     * 获取Followers列表
     *
     * @param authorization 授权账号信息
     * @param user          注册的用户名
     * @return
     */
    @GET("/users/{user}/followers")
    Observable<ArrayList<UserModel>> getUserFollowers(@Header("Authorization") String authorization,
                                                      @Path("user") String user);

    /**
     * 获取组织信息
     *
     * @param authorization 授权账号信息
     * @param user          注册的用户名
     * @return
     */
    @GET("/users/{user}/orgs")
    Observable<ArrayList<OrganizationsModel>> organizations(@Header("Authorization") String authorization,
                                                            @Path("user") String user);

    /**
     * 获取我的Followers列表
     *
     * @param authorization 授权账号信息
     * @return
     */
    @GET("/user/followers")
    Observable<ArrayList<UserModel>> getMyFollowers(@Header("Authorization") String authorization);

    /**
     * star项目
     *
     * @param authorization 授权账号信息
     * @param owner
     * @param repo
     * @return
     */
    @Headers("Content-Length: 0")
    @PUT("/user/starred/{owner}/{repo}")
    Observable<Response<ResponseBody>> starRepo(@Header("Authorization") String authorization,
                                                @Path("owner") String owner,
                                                @Path("repo") String repo);

    /**
     * unstar项目
     *
     * @param authorization 授权账号信息
     * @param owner
     * @param repo
     * @return
     */
    @DELETE("/user/starred/{owner}/{repo}")
    Observable<Response<ResponseBody>> unstarRepo(@Header("Authorization") String authorization,
                                                  @Path("owner") String owner,
                                                  @Path("repo") String repo);

    /**
     * @param authorization 授权账号信息
     * @param owner
     * @param repo
     * @return
     */
    @GET("/repos/{owner}/{repo}/contents")
    Observable<ArrayList<Content>> contents(@Header("Authorization") String authorization,
                                            @Path("owner") String owner,
                                            @Path("repo") String repo);

    /**
     * @param authorization 授权账号信息
     * @param owner
     * @param repo
     * @param path
     * @return
     */
    @GET("/repos/{owner}/{repo}/contents/{path}")
    Observable<ArrayList<Content>> contentsWithPath(@Header("Authorization") String authorization,
                                                    @Path("owner") String owner,
                                                    @Path("repo") String repo,
                                                    @Path("path") String path);

    /**
     * @param authorization 授权账号信息
     * @param owner
     * @param repo
     * @param path
     * @return
     */
    @GET("/repos/{owner}/{repo}/contents/{path}")
    Observable<Content> contentDetail(@Header("Authorization") String authorization,
                                      @Path("owner") String owner,
                                      @Path("repo") String repo,
                                      @Path("path") String path);

    /**
     * @param authorization 授权账号信息
     * @param owner
     * @param repo
     * @param sort          排序依据[newest]最新倒序
     * @return
     */
    @GET("/repos/{owner}/{repo}/issues")
    Observable<ArrayList<IssuesModel>> issues(@Header("Authorization") String authorization,
                                              @Path("owner") String owner,
                                              @Path("repo") String repo,
                                              @Query("sort") String sort);

    /**
     * 获取组织项目仓库列表
     *
     * @param authorization
     * @param owner
     * @return
     */
    @GET("/orgs/{owner}/repos")
    Observable<ArrayList<Repo>> loadOrgsRepos(@Header("Authorization") String authorization,
                                              @Path("owner") String owner);


}
