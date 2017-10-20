package com.jusenr.androidgithub.user.ui.adapter;

import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jusenr.androidgithub.R;
import com.jusenr.androidgithub.user.model.model.OrganizationsModel;
import com.jusenr.androidlibrary.widgets.fresco.FrescoImageView;

import java.util.List;

/**
 * Created by mingjun on 16/7/18.
 */
public class OrgsListRecyclerAdapter extends BaseQuickAdapter<OrganizationsModel> {

    public OrgsListRecyclerAdapter(List<OrganizationsModel> data) {
        super(R.layout.layout_item_orgs_list, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, OrganizationsModel model) {
        if (model != null) {
            if (!TextUtils.isEmpty(model.getAvatar_url())) {
                ((FrescoImageView) holder.getView(R.id.fiv_orgs_icon)).setImageURL(model.getAvatar_url());
            }
            if (!TextUtils.isEmpty(model.getLogin())) {
                holder.setText(R.id.tv_orgs_name, model.getLogin());
            }
        }
    }
}
