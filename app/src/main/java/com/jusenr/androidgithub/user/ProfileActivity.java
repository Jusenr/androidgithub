package com.jusenr.androidgithub.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;

import com.jusenr.androidgithub.R;
import com.jusenr.androidgithub.base.PTActivity;

/**
 * Description:
 * Copyright  : Copyright (c) 2017
 * Email      : jusenr@163.com
 * Author     : Jusenr
 * Date       : 2017/09/30
 * Time       : 17:10
 * Project    ：androidgithub.
 */
public class ProfileActivity extends PTActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_profile;
    }

    @Override
    public String getLoadingMessage() {
        return null;
    }

    @Override
    protected void onViewCreated(@Nullable Bundle savedInstanceState) {
        setTitle(R.string.profile);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
