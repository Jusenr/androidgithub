package com.jusenr.androidgithub.home.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jusenr.androidgithub.R;
import com.jusenr.androidgithub.home.model.model.Repo;
import com.jusenr.androidgithub.utils.Utils;

import java.util.List;

/**
 * Created by mingjun on 16/7/18.
 */
public class RepoListRecyclerAdapter extends BaseQuickAdapter<Repo, BaseViewHolder> {

    public RepoListRecyclerAdapter(List<Repo> data) {
        super(R.layout.layout_item_repo, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, Repo repo) {
        holder.setText(R.id.name, Utils.replaceAllBlank(repo.getName()));
        holder.setText(R.id.desc, Utils.trimNewLine(repo.getDescription()));
    }
}
