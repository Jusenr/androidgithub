package com.jusenr.androidgithub.home.model.model;

import com.jusenr.androidgithub.user.model.model.UserModel;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by mingjun on 16/7/29.
 */
public class RepoDetail implements Serializable {

    private Repo baseRepo;
    private Content readme;
    private ArrayList<Repo> forks;
    private ArrayList<UserModel> contributors;

    public Repo getBaseRepo() {
        return baseRepo;
    }

    public void setBaseRepo(Repo baseRepo) {
        this.baseRepo = baseRepo;
    }

    public Content getReadme() {
        return readme;
    }

    public void setReadme(Content readme) {
        this.readme = readme;
    }

    public ArrayList<Repo> getForks() {
        return forks;
    }

    public void setForks(ArrayList<Repo> forks) {
        this.forks = forks;
    }

    public ArrayList<UserModel> getContributors() {
        return contributors;
    }

    public void setContributors(ArrayList<UserModel> contributors) {
        this.contributors = contributors;
    }

    @Override
    public String toString() {
        return "RepoDetail{" +
                "baseRepo=" + baseRepo +
                ", readme=" + readme +
                ", forks=" + forks +
                ", contributors=" + contributors +
                '}';
    }
}
