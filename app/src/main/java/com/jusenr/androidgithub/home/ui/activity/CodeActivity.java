package com.jusenr.androidgithub.home.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jusenr.androidgithub.R;
import com.jusenr.androidgithub.base.PTMVPActivity;
import com.jusenr.androidgithub.home.contract.CodeContract;
import com.jusenr.androidgithub.home.di.component.DaggerCodeComponent;
import com.jusenr.androidgithub.home.di.module.CodeModule;
import com.jusenr.androidgithub.home.model.model.Content;
import com.jusenr.androidgithub.home.presenter.CodePresenter;
import com.jusenr.androidgithub.utils.Constants;
import com.jusenr.androidgithub.utils.Utils;
import com.jusenr.toolslibrary.utils.StringUtils;
import com.jusenr.toolslibrary.utils.ToastUtils;
import com.pddstudio.highlightjs.HighlightJsView;
import com.pddstudio.highlightjs.models.Language;
import com.pddstudio.highlightjs.models.Theme;

import butterknife.BindView;

public class CodeActivity extends PTMVPActivity<CodePresenter> implements CodeContract.View {

    @BindView(R.id.hjv_code_view)
    HighlightJsView mCodeView;

    @Override
    protected void injectComponent() {
        DaggerCodeComponent.builder()
                .appComponent(mApplication.getAppComponent())
                .codeModule(new CodeModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_code;
    }

    @Override
    protected void onViewCreated(@Nullable Bundle savedInstanceState) {
        super.onViewCreated(savedInstanceState);

        mCodeView.setTheme(Theme.ANDROID_STUDIO);
        mCodeView.setHighlightLanguage(Language.AUTO_DETECT);

        String owner = getIntent().getStringExtra(Constants.BundleKey.BUNDLE_OWNER);
        String repo = getIntent().getStringExtra(Constants.BundleKey.BUNDLE_REPO_NAME);
        String path = getIntent().getStringExtra(Constants.BundleKey.BUNDLE_CODE);

        mPresenter.onContentDetail(owner, repo, path);
    }

    @Override
    public void contentDetailResult(Content data) {
        if (data != null) {
            mCodeView.setSource(Utils.base64Decode(data.content));
        }
    }

    @Override
    public void contentDetailFailed(int code, String msg) {
        if (!StringUtils.isEmpty(msg))
            ToastUtils.show(this, msg);
    }
}
