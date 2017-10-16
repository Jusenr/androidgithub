package com.jusenr.androidgithub.home.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jusenr.androidgithub.R;
import com.jusenr.androidgithub.base.PTActivity;
import com.jusenr.androidgithub.home.model.model.Content;
import com.jusenr.androidgithub.utils.Constants;
import com.jusenr.toolslibrary.utils.StringUtils;
import com.mukesh.MarkdownView;

import butterknife.BindView;

public class ReadmeActivity extends PTActivity {

    @BindView(R.id.mv_readme_content)
    MarkdownView mMvReadmeContent;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_readme;
    }

    @Override
    protected void onViewCreated(@Nullable Bundle savedInstanceState) {
        Content readmeContent = (Content) getIntent().getSerializableExtra(Constants.BundleKey.BUNDLE_README);
        if (readmeContent != null && !StringUtils.isEmpty(readmeContent.content)) {
            mMvReadmeContent.setMarkDownText(readmeContent.content);
        }
    }
}
