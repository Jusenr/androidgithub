package com.jusenr.androidgithub.user.model.model;

import java.io.Serializable;

/**
 * Created by mingjun on 16/7/27.
 */
public class UserModel implements Serializable {
    /**
     * avatar_url : https://avatars2.githubusercontent.com/u/19364345?v=4
     * bio : http://blog.csdn.net/github_35033182
     * blog : https://github.com/Jusenr
     * collaborators : 0.0
     * created_at : 2016-05-14T17:49:22Z
     * disk_usage : 85459.0
     * email : jusenr@163.com
     * events_url : https://api.github.com/users/Jusenr/events{/privacy}
     * followers : 3.0
     * followers_url : https://api.github.com/users/Jusenr/followers
     * following : 0.0
     * following_url : https://api.github.com/users/Jusenr/following{/other_user}
     * gists_url : https://api.github.com/users/Jusenr/gists{/gist_id}
     * gravatar_id :
     * html_url : https://github.com/Jusenr
     * id : 19364345
     * login : Jusenr
     * name : Jusenr
     * organizations_url : https://api.github.com/users/Jusenr/orgs
     * owned_private_repos : 0.0
     * plan : {"collaborators":0,"name":"free","private_repos":0,"space":976562499}
     * private_gists : 0.0
     * public_gists : 0.0
     * public_repos : 22.0
     * received_events_url : https://api.github.com/users/Jusenr/received_events
     * repos_url : https://api.github.com/users/Jusenr/repos
     * site_admin : false
     * starred_url : https://api.github.com/users/Jusenr/starred{/owner}{/repo}
     * subscriptions_url : https://api.github.com/users/Jusenr/subscriptions
     * total_private_repos : 0.0
     * two_factor_authentication : false
     * type : UserModel
     * updated_at : 2017-09-29T02:10:45Z
     * url : https://api.github.com/users/Jusenr
     */

    private String avatar_url;
    private String bio;
    private String blog;
    private double collaborators;
    private String created_at;
    private double disk_usage;
    private String email;
    private String events_url;
    private double followers;
    private String followers_url;
    private double following;
    private String following_url;
    private String gists_url;
    private String gravatar_id;
    private String html_url;
    private int id;
    private String login;
    private String name;
    private String organizations_url;
    private double owned_private_repos;
    private PlanBean plan;
    private double private_gists;
    private double public_gists;
    private double public_repos;
    private String received_events_url;
    private String repos_url;
    private boolean site_admin;
    private String starred_url;
    private String subscriptions_url;
    private double total_private_repos;
    private boolean two_factor_authentication;
    private String type;
    private String updated_at;
    private String url;

    //add
    private String company;
    private String location;

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    public double getCollaborators() {
        return collaborators;
    }

    public void setCollaborators(double collaborators) {
        this.collaborators = collaborators;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public double getDisk_usage() {
        return disk_usage;
    }

    public void setDisk_usage(double disk_usage) {
        this.disk_usage = disk_usage;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEvents_url() {
        return events_url;
    }

    public void setEvents_url(String events_url) {
        this.events_url = events_url;
    }

    public double getFollowers() {
        return followers;
    }

    public void setFollowers(double followers) {
        this.followers = followers;
    }

    public String getFollowers_url() {
        return followers_url;
    }

    public void setFollowers_url(String followers_url) {
        this.followers_url = followers_url;
    }

    public double getFollowing() {
        return following;
    }

    public void setFollowing(double following) {
        this.following = following;
    }

    public String getFollowing_url() {
        return following_url;
    }

    public void setFollowing_url(String following_url) {
        this.following_url = following_url;
    }

    public String getGists_url() {
        return gists_url;
    }

    public void setGists_url(String gists_url) {
        this.gists_url = gists_url;
    }

    public String getGravatar_id() {
        return gravatar_id;
    }

    public void setGravatar_id(String gravatar_id) {
        this.gravatar_id = gravatar_id;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrganizations_url() {
        return organizations_url;
    }

    public void setOrganizations_url(String organizations_url) {
        this.organizations_url = organizations_url;
    }

    public double getOwned_private_repos() {
        return owned_private_repos;
    }

    public void setOwned_private_repos(double owned_private_repos) {
        this.owned_private_repos = owned_private_repos;
    }

    public PlanBean getPlan() {
        return plan;
    }

    public void setPlan(PlanBean plan) {
        this.plan = plan;
    }

    public double getPrivate_gists() {
        return private_gists;
    }

    public void setPrivate_gists(double private_gists) {
        this.private_gists = private_gists;
    }

    public double getPublic_gists() {
        return public_gists;
    }

    public void setPublic_gists(double public_gists) {
        this.public_gists = public_gists;
    }

    public double getPublic_repos() {
        return public_repos;
    }

    public void setPublic_repos(double public_repos) {
        this.public_repos = public_repos;
    }

    public String getReceived_events_url() {
        return received_events_url;
    }

    public void setReceived_events_url(String received_events_url) {
        this.received_events_url = received_events_url;
    }

    public String getRepos_url() {
        return repos_url;
    }

    public void setRepos_url(String repos_url) {
        this.repos_url = repos_url;
    }

    public boolean isSite_admin() {
        return site_admin;
    }

    public void setSite_admin(boolean site_admin) {
        this.site_admin = site_admin;
    }

    public String getStarred_url() {
        return starred_url;
    }

    public void setStarred_url(String starred_url) {
        this.starred_url = starred_url;
    }

    public String getSubscriptions_url() {
        return subscriptions_url;
    }

    public void setSubscriptions_url(String subscriptions_url) {
        this.subscriptions_url = subscriptions_url;
    }

    public double getTotal_private_repos() {
        return total_private_repos;
    }

    public void setTotal_private_repos(double total_private_repos) {
        this.total_private_repos = total_private_repos;
    }

    public boolean isTwo_factor_authentication() {
        return two_factor_authentication;
    }

    public void setTwo_factor_authentication(boolean two_factor_authentication) {
        this.two_factor_authentication = two_factor_authentication;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public static class PlanBean {
        /**
         * collaborators : 0.0
         * name : free
         * private_repos : 0.0
         * space : 976562499
         */

        private double collaborators;
        private String name;
        private double private_repos;
        private int space;

        public double getCollaborators() {
            return collaborators;
        }

        public void setCollaborators(double collaborators) {
            this.collaborators = collaborators;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getPrivate_repos() {
            return private_repos;
        }

        public void setPrivate_repos(double private_repos) {
            this.private_repos = private_repos;
        }

        public int getSpace() {
            return space;
        }

        public void setSpace(int space) {
            this.space = space;
        }
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "avatar_url='" + avatar_url + '\'' +
                ", bio='" + bio + '\'' +
                ", blog='" + blog + '\'' +
                ", collaborators=" + collaborators +
                ", created_at='" + created_at + '\'' +
                ", disk_usage=" + disk_usage +
                ", email='" + email + '\'' +
                ", events_url='" + events_url + '\'' +
                ", followers=" + followers +
                ", followers_url='" + followers_url + '\'' +
                ", following=" + following +
                ", following_url='" + following_url + '\'' +
                ", gists_url='" + gists_url + '\'' +
                ", gravatar_id='" + gravatar_id + '\'' +
                ", html_url='" + html_url + '\'' +
                ", id=" + id +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", organizations_url='" + organizations_url + '\'' +
                ", owned_private_repos=" + owned_private_repos +
                ", plan=" + plan +
                ", private_gists=" + private_gists +
                ", public_gists=" + public_gists +
                ", public_repos=" + public_repos +
                ", received_events_url='" + received_events_url + '\'' +
                ", repos_url='" + repos_url + '\'' +
                ", site_admin=" + site_admin +
                ", starred_url='" + starred_url + '\'' +
                ", subscriptions_url='" + subscriptions_url + '\'' +
                ", total_private_repos=" + total_private_repos +
                ", two_factor_authentication=" + two_factor_authentication +
                ", type='" + type + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", url='" + url + '\'' +
                ", company='" + company + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
