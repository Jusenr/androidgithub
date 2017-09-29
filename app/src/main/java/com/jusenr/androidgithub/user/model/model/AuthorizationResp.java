package com.jusenr.androidgithub.user.model.model;

import java.io.Serializable;

/**
 * Created by mingjun on 16/7/27.
 */
public class AuthorizationResp implements Serializable {

    /**
     * id : 42390878
     * url : https://api.github.com/authorizations/42390878
     * app : {"name":"GithubApp","url":"https://github.com/mingjunli/GithubApp","client_id":"5b074b14a3c166278774"}
     * token : 70f28edae0a1f38a82c74548e46b70e1af1a653f
     * hashed_token : eecf10631f409697fd7d8844f18f36195d087048e9f6194e285a1c228af9c056
     * token_last_eight : af1a653f
     * note : test
     * note_url : null
     * created_at : 2016-07-27T08:19:02Z
     * updated_at : 2016-07-27T08:19:02Z
     * scopes : []
     * fingerprint : null
     */

    private int id;
    private String url;
    /**
     * name : GithubApp
     * url : https://github.com/mingjunli/GithubApp
     * client_id : 5b074b14a3c166278774
     */
    private AppBean app;
    private String token;
    private String hashed_token;
    private String token_last_eight;
    private String note;
    private String note_url;
    private String created_at;
    private String updated_at;
    private String fingerprint;
    private String[] scopes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public AppBean getApp() {
        return app;
    }

    public void setApp(AppBean app) {
        this.app = app;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getHashed_token() {
        return hashed_token;
    }

    public void setHashed_token(String hashed_token) {
        this.hashed_token = hashed_token;
    }

    public String getToken_last_eight() {
        return token_last_eight;
    }

    public void setToken_last_eight(String token_last_eight) {
        this.token_last_eight = token_last_eight;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNote_url() {
        return note_url;
    }

    public void setNote_url(String note_url) {
        this.note_url = note_url;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public String[] getScopes() {
        return scopes;
    }

    public void setScopes(String[] scopes) {
        this.scopes = scopes;
    }

    public static class AppBean implements Serializable {
        private String name;
        private String url;
        private String client_id;

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

        public String getClient_id() {
            return client_id;
        }

        public void setClient_id(String client_id) {
            this.client_id = client_id;
        }

        public AppBean() {
        }
    }

    public AuthorizationResp() {
    }
}
