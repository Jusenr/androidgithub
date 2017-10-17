package com.jusenr.androidgithub.home.model.model;

import java.io.Serializable;

/**
 * Created by Bernat on 20/07/2014.
 */
public class Links implements Serializable {

    public String html;
    public String self;
    public String git;

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public String getGit() {
        return git;
    }

    public void setGit(String git) {
        this.git = git;
    }

    @Override
    public String toString() {
        return "Links{" +
                "html='" + html + '\'' +
                ", self='" + self + '\'' +
                ", git='" + git + '\'' +
                '}';
    }
}
