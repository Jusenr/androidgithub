package com.jusenr.androidgithub.home.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jusenr.androidgithub.R;
import com.jusenr.androidgithub.home.model.model.IssuesModel;
import com.jusenr.androidgithub.utils.Utils;

import java.util.List;

/**
 * Created by mingjun on 16/7/18.
 */
public class IssuesListRecyclerAdapter extends BaseQuickAdapter<IssuesModel> {

    public IssuesListRecyclerAdapter(List<IssuesModel> data) {
        super(R.layout.layout_item_repo, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, IssuesModel issuesModel) {
        holder.setText(R.id.name, issuesModel.getTitle() + "#" + issuesModel.getNumber());
        holder.setText(R.id.desc, Utils.trimNewLine(issuesModel.getBody()));
    }
}
