package com.jusenr.androidgithub.guidance;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.widget.Button;
import android.widget.TextView;

import com.jusenr.androidgithub.R;
import com.jusenr.androidgithub.base.PTActivity;
import com.jusenr.androidgithub.home.ui.activity.MainActivity;
import com.jusenr.androidgithub.user.ui.activity.LoginActivity;
import com.jusenr.androidgithub.utils.AccountHelper;
import com.youth.banner.Banner;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.OnClick;

public class GuidanceActivity extends PTActivity {

    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.tv_text)
    TextView mTvText;
    @BindView(R.id.btn_start)
    Button mBtnStart;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_guidance;
    }

    @Override
    protected void onViewCreated(@Nullable Bundle savedInstanceState) {
        final String[] urls = {
                getString(R.string.guidance_image_1),
                getString(R.string.guidance_image_2),
                getString(R.string.guidance_image_3),
                getString(R.string.guidance_image_4),
                getString(R.string.guidance_image_5),
                getString(R.string.guidance_image_6)
        };
        final String[] texts = {
                getString(R.string.guidance_text_1),
                getString(R.string.guidance_text_2),
                getString(R.string.guidance_text_3),
                getString(R.string.guidance_text_4),
                getString(R.string.guidance_text_5),
                getString(R.string.guidance_text_6)
        };
        mBanner.setImageLoader(new BannerImageLoader());
        //设置图片集合
        mBanner.setImages(Arrays.asList(urls));
        //设置标题
        mBanner.setBannerTitles(Arrays.asList(texts));

        mBanner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
//                int i = (position - 1) % urls.length;
//                mTvText.setText(texts[i]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //启动轮播
        mBanner.start();
    }

    @OnClick(R.id.btn_start)
    public void onViewClicked() {
        if (AccountHelper.isLogin()) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        } else {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }
        finish();
    }
}
