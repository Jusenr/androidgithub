package com.jusenr.androidgithub.home.model.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by mingjun on 16/7/18.
 */
public class SearchResultResp implements Serializable {

    private long total_count;
    private boolean incomplete_results;
    private ArrayList<Repo> items;

    public long getTotal_count() {
        return total_count;
    }

    public void setTotal_count(long total_count) {
        this.total_count = total_count;
    }

    public boolean isIncomplete_results() {
        return incomplete_results;
    }

    public void setIncomplete_results(boolean incomplete_results) {
        this.incomplete_results = incomplete_results;
    }

    public ArrayList<Repo> getItems() {
        return items;
    }

    public void setItems(ArrayList<Repo> items) {
        this.items = items;
    }

}
