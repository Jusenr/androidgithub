package com.jusenr.androidgithub.home.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jusenr.androidgithub.R;
import com.jusenr.androidgithub.home.model.model.Repo;
import com.jusenr.androidgithub.utils.StringUtil;

import java.util.List;

/**
 * Created by mingjun on 16/7/18.
 */
public class RepoListRecyclerAdapter extends BaseQuickAdapter<Repo> {

    public RepoListRecyclerAdapter(List<Repo> data) {
        super(R.layout.layout_item_repo, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, Repo repo) {
        holder.setText(R.id.name, StringUtil.replaceAllBlank(repo.getName()));
        holder.setText(R.id.desc, StringUtil.trimNewLine(repo.getDescription()));
    }
}
