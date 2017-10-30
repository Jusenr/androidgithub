package com.jusenr.androidgithub.home.ui.adapter;

import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jusenr.androidgithub.R;
import com.jusenr.androidgithub.home.model.model.Repo;
import com.jusenr.androidlibrary.widgets.fresco.FrescoImageView;

import java.util.List;

/**
 * Created by mingjun on 16/7/29.
 */
public class ForkUserListAdapter extends BaseQuickAdapter<Repo, BaseViewHolder> {

    public ForkUserListAdapter(List<Repo> data) {
        super(R.layout.layout_item_user, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, Repo repo) {
        if (repo != null && !TextUtils.isEmpty(repo.getOwner().getAvatar_url())) {
            ((FrescoImageView) holder.getView(R.id.user_icon)).setImageURL(repo.getOwner().getAvatar_url());
        }
    }
}
