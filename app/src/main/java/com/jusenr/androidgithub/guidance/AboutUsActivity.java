package com.jusenr.androidgithub.guidance;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AboutUsActivity extends PTActivity {

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

    @OnClick(R.id.tv_source_link)
    public void onViewClicked() {
    }

    /**
     * 生成孩子的二维码
     */
    private void createQRCode() {
        final String code = "https://github.com/Jusenr/androidgithubapp";
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
                            mIvIcon.setImageRes(R.mipmap.ic_launcher_round);
                        }
                    }
                });


    }
}