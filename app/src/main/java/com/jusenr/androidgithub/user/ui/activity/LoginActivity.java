package com.jusenr.androidgithub.user.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;

import com.jusenr.androidgithub.R;
import com.jusenr.androidgithub.base.PTMVPActivity;
import com.jusenr.androidgithub.user.contract.LoginContract;
import com.jusenr.androidgithub.user.di.component.DaggerLoginComponent;
import com.jusenr.androidgithub.user.di.module.LoginModule;
import com.jusenr.androidgithub.user.model.model.UserModel;
import com.jusenr.androidgithub.user.presenter.LoginPresenter;
import com.jusenr.androidgithub.utils.InputMethodUtils;
import com.jusenr.toolslibrary.log.logger.Logger;
import com.jusenr.toolslibrary.utils.StringUtils;
import com.jusenr.toolslibrary.utils.ToastUtils;
import com.rey.material.widget.Button;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends PTMVPActivity<LoginPresenter> implements LoginContract.View {

    @BindView(R.id.icon)
    ImageView mIcon;
    @BindView(R.id.et_username)
    EditText mEtUsername;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.login_btn)
    Button mLoginBtn;

    @Override
    protected void injectComponent() {
        DaggerLoginComponent.builder()
                .appComponent(mApplication.getAppComponent())
                .loginModule(new LoginModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public String getLoadingMessage() {
        return null;
    }

    @Override
    protected void onViewCreated(@Nullable Bundle savedInstanceState) {
        super.onViewCreated(savedInstanceState);

        setTitle(R.string.sign_in);

    }

    @Override
    public void loginResult(UserModel bean) {
        Logger.i(bean.toString());
        finish();
    }

    @Override
    public void loginFailed(int code, String msg) {
        if (!StringUtils.isEmpty(msg))
            ToastUtils.show(this, msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @OnClick(R.id.login_btn)
    public void onViewClicked() {
        String username = mEtUsername.getText().toString();
        String password = mEtPassword.getText().toString();

        username = "jusenr@163.com";
        password = "1229zxr?.";

        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
            InputMethodUtils.hideSoftInput(this);
            mPresenter.onLogin(username, password);
        }
    }
}
