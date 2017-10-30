package com.jusenr.androidgithub.user.ui.adapter;

import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jusenr.androidgithub.R;
import com.jusenr.androidgithub.user.model.model.UserModel;
import com.jusenr.androidlibrary.widgets.fresco.FrescoImageView;

import java.util.List;

/**
 * Created by mingjun on 16/7/18.
 */
public class UserListRecyclerAdapter extends BaseQuickAdapter<UserModel, BaseViewHolder> {

    public UserListRecyclerAdapter(List<UserModel> data) {
        super(R.layout.layout_item_user_list, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, UserModel user) {
        if (user != null) {
            if (!TextUtils.isEmpty(user.getAvatar_url())) {
                ((FrescoImageView) holder.getView(R.id.user_icon)).setImageURL(user.getAvatar_url());
            }
            if (!TextUtils.isEmpty(user.getLogin())) {
                holder.setText(R.id.username, user.getLogin());
            }
        }
    }
}
