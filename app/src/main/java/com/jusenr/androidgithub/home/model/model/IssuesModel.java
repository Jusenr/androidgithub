package com.jusenr.androidgithub.home.model.model;

import com.jusenr.androidgithub.user.model.model.UserModel;

import java.io.Serializable;
import java.util.List;

/**
 * Description:
 * Copyright  : Copyright (c) 2017
 * Email      : jusenr@163.com
 * Author     : Jusenr
 * Date       : 2017/10/17
 * Time       : 15:16
 * Project    ：androidgithub.
 */
public class IssuesModel implements Serializable {


    /**
     * assignees : []
     * author_association : NONE
     * body : Add a [fruit](https://github.com/ghuiii/Fruit) converter.
     * comments : 0
     * comments_url : https://api.github.com/repos/square/retrofit/issues/2527/comments
     * created_at : 2017-10-17T05:51:52Z
     * events_url : https://api.github.com/repos/square/retrofit/issues/2527/events
     * html_url : https://github.com/square/retrofit/pull/2527
     * id : 266000741
     * labels : []
     * labels_url : https://api.github.com/repos/square/retrofit/issues/2527/labels{/name}
     * locked : false
     * number : 2527
     * pull_request : {"diff_url":"https://github.com/square/retrofit/pull/2527.diff","html_url":"https://github.com/square/retrofit/pull/2527","patch_url":"https://github.com/square/retrofit/pull/2527.patch","url":"https://api.github.com/repos/square/retrofit/pulls/2527"}
     * repository_url : https://api.github.com/repos/square/retrofit
     * state : open
     * title : Add a fruit converter.
     * updated_at : 2017-10-17T06:47:16Z
     * url : https://api.github.com/repos/square/retrofit/issues/2527
     * user : {"avatar_url":"https://avatars0.githubusercontent.com/u/5203798?v=4","events_url":"https://api.github.com/users/ghuiii/events{/privacy}","followers_url":"https://api.github.com/users/ghuiii/followers","following_url":"https://api.github.com/users/ghuiii/following{/other_user}","gists_url":"https://api.github.com/users/ghuiii/gists{/gist_id}","gravatar_id":"","html_url":"https://github.com/ghuiii","id":5203798,"login":"ghuiii","organizations_url":"https://api.github.com/users/ghuiii/orgs","received_events_url":"https://api.github.com/users/ghuiii/received_events","repos_url":"https://api.github.com/users/ghuiii/repos","site_admin":false,"starred_url":"https://api.github.com/users/ghuiii/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/ghuiii/subscriptions","type":"User","url":"https://api.github.com/users/ghuiii"}
     */

    private String author_association;
    private String body;
    private int comments;
    private String comments_url;
    private String created_at;
    private String events_url;
    private String html_url;
    private int id;
    private String labels_url;
    private boolean locked;
    private int number;
    private PullRequestBean pull_request;
    private String repository_url;
    private String state;
    private String title;
    private String updated_at;
    private String url;
    private UserModel user;
    private List<?> assignees;
    private List<?> labels;

    public String getAuthor_association() {
        return author_association;
    }

    public void setAuthor_association(String author_association) {
        this.author_association = author_association;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public String getComments_url() {
        return comments_url;
    }

    public void setComments_url(String comments_url) {
        this.comments_url = comments_url;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getEvents_url() {
        return events_url;
    }

    public void setEvents_url(String events_url) {
        this.events_url = events_url;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabels_url() {
        return labels_url;
    }

    public void setLabels_url(String labels_url) {
        this.labels_url = labels_url;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public PullRequestBean getPull_request() {
        return pull_request;
    }

    public void setPull_request(PullRequestBean pull_request) {
        this.pull_request = pull_request;
    }

    public String getRepository_url() {
        return repository_url;
    }

    public void setRepository_url(String repository_url) {
        this.repository_url = repository_url;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public List<?> getAssignees() {
        return assignees;
    }

    public void setAssignees(List<?> assignees) {
        this.assignees = assignees;
    }

    public List<?> getLabels() {
        return labels;
    }

    public void setLabels(List<?> labels) {
        this.labels = labels;
    }

    public static class PullRequestBean {
        /**
         * diff_url : https://github.com/square/retrofit/pull/2527.diff
         * html_url : https://github.com/square/retrofit/pull/2527
         * patch_url : https://github.com/square/retrofit/pull/2527.patch
         * url : https://api.github.com/repos/square/retrofit/pulls/2527
         */

        private String diff_url;
        private String html_url;
        private String patch_url;
        private String url;

        public String getDiff_url() {
            return diff_url;
        }

        public void setDiff_url(String diff_url) {
            this.diff_url = diff_url;
        }

        public String getHtml_url() {
            return html_url;
        }

        public void setHtml_url(String html_url) {
            this.html_url = html_url;
        }

        public String getPatch_url() {
            return patch_url;
        }

        public void setPatch_url(String patch_url) {
            this.patch_url = patch_url;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    @Override
    public String toString() {
        return "IssuesModel{" +
                "author_association='" + author_association + '\'' +
                ", body='" + body + '\'' +
                ", comments=" + comments +
                ", comments_url='" + comments_url + '\'' +
                ", created_at='" + created_at + '\'' +
                ", events_url='" + events_url + '\'' +
                ", html_url='" + html_url + '\'' +
                ", id=" + id +
                ", labels_url='" + labels_url + '\'' +
                ", locked=" + locked +
                ", number=" + number +
                ", pull_request=" + pull_request +
                ", repository_url='" + repository_url + '\'' +
                ", state='" + state + '\'' +
                ", title='" + title + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", url='" + url + '\'' +
                ", user=" + user +
                ", assignees=" + assignees +
                ", labels=" + labels +
                '}';
    }
}
