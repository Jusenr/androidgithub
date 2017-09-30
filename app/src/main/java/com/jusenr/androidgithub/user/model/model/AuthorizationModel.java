package com.jusenr.androidgithub.user.model.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mingjun on 16/7/27.
 */
public class AuthorizationModel implements Serializable {

    /**
     * app : {"client_id":"83b7d6586738c4a360db","name":"AndroidGitHubApp","url":"https://github.com/Jusenr/androidgithubapp"}
     * created_at : 2017-09-30T03:04:35Z
     * hashed_token : 198325f8d20be0bebbfd2c727668b9e640a4b296b069f7f6365a9a2259154f43
     * id : 132041168
     * note : AndroidGitHubApp
     * scopes : ["user","repo","notifications","gist","admin:org"]
     * token : 9231f1c6e7254a41dd8964b8ffeb59a501297707
     * token_last_eight : 01297707
     * updated_at : 2017-09-30T03:04:35Z
     * url : https://api.github.com/authorizations/132041168
     */

    private AppBean app;
    private String created_at;
    private String hashed_token;
    private long id;
    private String note;
    private String token;
    private String token_last_eight;
    private String updated_at;
    private String url;
    private List<String> scopes;

    public AppBean getApp() {
        return app;
    }

    public void setApp(AppBean app) {
        this.app = app;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getHashed_token() {
        return hashed_token;
    }

    public void setHashed_token(String hashed_token) {
        this.hashed_token = hashed_token;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken_last_eight() {
        return token_last_eight;
    }

    public void setToken_last_eight(String token_last_eight) {
        this.token_last_eight = token_last_eight;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getScopes() {
        return scopes;
    }

    public void setScopes(List<String> scopes) {
        this.scopes = scopes;
    }

    public static class AppBean {
        /**
         * client_id : 83b7d6586738c4a360db
         * name : AndroidGitHubApp
         * url : https://github.com/Jusenr/androidgithubapp
         */

        private String client_id;
        private String name;
        private String url;

        public String getClient_id() {
            return client_id;
        }

        public void setClient_id(String client_id) {
            this.client_id = client_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
