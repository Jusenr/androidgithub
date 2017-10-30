package com.jusenr.androidgithub.home.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jusenr.androidgithub.R;
import com.jusenr.androidgithub.home.model.model.Content;
import com.mikepenz.iconics.view.IconicsImageView;
import com.mikepenz.octicons_typeface_library.Octicons;

import java.util.List;

/**
 * Created by mingjun on 16/7/18.
 */
public class RepoContentAdapter extends BaseQuickAdapter<Content, BaseViewHolder> {

    public RepoContentAdapter(List<Content> data) {
        super(R.layout.layout_item_content, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, Content content) {
        if (content != null) {
            IconicsImageView typeIndicator = holder.getView(R.id.type);
            if (content.isDir()) {
                typeIndicator.setIcon(Octicons.Icon.oct_file_directory);
            } else if (content.isFile()) {
                typeIndicator.setIcon(Octicons.Icon.oct_file_binary);
            } else if (content.isSubmodule()) {
                typeIndicator.setIcon(Octicons.Icon.oct_file_submodule);
            } else {
                typeIndicator.setIcon(Octicons.Icon.oct_file_symlink_file);
            }
            holder.setText(R.id.file_name, content.name);
        }
    }
}
