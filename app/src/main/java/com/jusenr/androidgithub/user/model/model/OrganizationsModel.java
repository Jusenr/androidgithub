package com.jusenr.androidgithub.user.model.model;

import java.io.Serializable;

/**
 * Description:
 * Copyright  : Copyright (c) 2017
 * Email      : jusenr@163.com
 * Author     : Jusenr
 * Date       : 2017/10/17
 * Time       : 11:53
 * Project    ：androidgithub.
 */
public class OrganizationsModel implements Serializable {

    /**
     * avatar_url : https://avatars2.githubusercontent.com/u/26195976?v=4
     * description : Snowflakes in winter.
     * events_url : https://api.github.com/orgs/Android-Development-Group/events
     * hooks_url : https://api.github.com/orgs/Android-Development-Group/hooks
     * id : 26195976
     * issues_url : https://api.github.com/orgs/Android-Development-Group/issues
     * login : Android-Development-Group
     * members_url : https://api.github.com/orgs/Android-Development-Group/members{/member}
     * public_members_url : https://api.github.com/orgs/Android-Development-Group/public_members{/member}
     * repos_url : https://api.github.com/orgs/Android-Development-Group/repos
     * url : https://api.github.com/orgs/Android-Development-Group
     */

    private String avatar_url;
    private String description;
    private String events_url;
    private String hooks_url;
    private int id;
    private String issues_url;
    private String login;
    private String members_url;
    private String public_members_url;
    private String repos_url;
    private String url;

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEvents_url() {
        return events_url;
    }

    public void setEvents_url(String events_url) {
        this.events_url = events_url;
    }

    public String getHooks_url() {
        return hooks_url;
    }

    public void setHooks_url(String hooks_url) {
        this.hooks_url = hooks_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIssues_url() {
        return issues_url;
    }

    public void setIssues_url(String issues_url) {
        this.issues_url = issues_url;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMembers_url() {
        return members_url;
    }

    public void setMembers_url(String members_url) {
        this.members_url = members_url;
    }

    public String getPublic_members_url() {
        return public_members_url;
    }

    public void setPublic_members_url(String public_members_url) {
        this.public_members_url = public_members_url;
    }

    public String getRepos_url() {
        return repos_url;
    }

    public void setRepos_url(String repos_url) {
        this.repos_url = repos_url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "OrganizationsModel{" +
                "avatar_url='" + avatar_url + '\'' +
                ", description='" + description + '\'' +
                ", events_url='" + events_url + '\'' +
                ", hooks_url='" + hooks_url + '\'' +
                ", id=" + id +
                ", issues_url='" + issues_url + '\'' +
                ", login='" + login + '\'' +
                ", members_url='" + members_url + '\'' +
                ", public_members_url='" + public_members_url + '\'' +
                ", repos_url='" + repos_url + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
