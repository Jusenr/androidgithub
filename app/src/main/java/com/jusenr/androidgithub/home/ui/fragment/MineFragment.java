package com.jusenr.androidgithub.home.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.jusenr.androidgithub.R;
import com.jusenr.androidgithub.base.PTFragment;

/**
 * Description:
 * Copyright  : Copyright (c) 2017
 * Email      : jusenr@163.com
 * Author     : Jusenr
 * Date       : 2017/09/30
 * Time       : 17:10
 * Project    ：androidgithub.
 */
public class MineFragment extends PTFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public String getLoadingMessage() {
        return null;
    }

    @Override
    public void onViewCreateFinish(View view, @Nullable Bundle savedInstanceState) {
    }
}
