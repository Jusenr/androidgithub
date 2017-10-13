package com.jusenr.androidgithub.guidance;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.jusenr.androidgithub.R;
import com.jusenr.androidgithub.base.PTActivity;
import com.jusenr.androidlibrary.widgets.fresco.FrescoImageView;
import com.jusenr.toolslibrary.utils.DensityUtils;
import com.jusenr.toolslibrary.utils.QRCodeUtils;
import com.jusenr.toolslibrary.utils.ToastUtils;

import butterknife.BindView;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AboutUsActivity extends PTActivity {

    public static final String code = "https://github.com/Jusenr/androidgithub";

    @BindView(R.id.tv_api)
    TextView mTvApi;
    @BindView(R.id.iv_qrcode)
    FrescoImageView mIvQrcode;
    @BindView(R.id.iv_icon)
    FrescoImageView mIvIcon;
    @BindView(R.id.tv_source_link)
    TextView mTvSourceLink;

    private Activity mActivity;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about_us;
    }

    @Override
    public String getLoadingMessage() {
        return null;
    }

    @Override
    protected void onViewCreated(@Nullable Bundle savedInstanceState) {
        mActivity = this;

        setTitle(R.string.about_us);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //超链接相关字符识别
        mTvApi.setAutoLinkMask(Linkify.ALL);
        mTvApi.setMovementMethod(LinkMovementMethod.getInstance());
        mTvSourceLink.setAutoLinkMask(Linkify.ALL);
        mTvSourceLink.setMovementMethod(LinkMovementMethod.getInstance());

        createQRCode();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 生成孩子的二维码
     */
    private void createQRCode() {

        mLoadingView.show();
        Log.i("#####", "createQRCode: code=" + code);
        Observable.create(new Observable.OnSubscribe<Bitmap>() {
            @Override
            public void call(Subscriber<? super Bitmap> subscriber) {
                Bitmap bitmapQRCode = QRCodeUtils.createQRCode(code, DensityUtils.dp2px(mActivity, 180), true);
                subscriber.onNext(bitmapQRCode);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Bitmap>() {
                    @Override
                    public void onCompleted() {
                        mLoadingView.dismiss();
                        //执行完所有任务后回调
                    }

                    @Override
                    public void onError(Throwable e) {
                        mLoadingView.dismiss();
                        ToastUtils.show(mActivity, mActivity.getString(R.string.about_us_qrcode_create_failed));
                        //发生错误时回调
                    }

                    @Override
                    public void onNext(Bitmap bitmap) {
                        mLoadingView.dismiss();
                        if (bitmap != null) {
                            mIvQrcode.setImageBitmap(bitmap);
                            mIvIcon.setVisibility(View.VISIBLE);
                            mIvIcon.setDefaultImage(getResources().getDrawable(R.drawable.logo_splash));
                        }
                    }
                });


    }
}