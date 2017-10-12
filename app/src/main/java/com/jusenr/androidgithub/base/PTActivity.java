package com.jusenr.androidgithub.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.LayoutInflaterCompat;

import com.jusenr.androidgithub.R;
import com.jusenr.androidlibrary.base.BaseActivity;
import com.jusenr.androidlibrary.base.loading.LoadView;
import com.jusenr.androidlibrary.base.loading.LoadingView;
import com.jusenr.toolslibrary.utils.EventBusUtils;
import com.jusenr.toolslibrary.utils.ToastUtils;
import com.mikepenz.iconics.context.IconicsLayoutInflater;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by riven_chris on 2017/4/20.
 */

public abstract class PTActivity extends BaseActivity implements LoadView {

    protected PTApplication mApplication;
    protected boolean isResume;
    private long exitTime = 0;

    private Unbinder unbinder;

    public LoadingView mLoadingView;
//    protected ILoadState loadState;
//    protected PTLoading mPTLoading;
//    protected PTToast mPTToast;
//    protected SelectDialog mOfflineDialog;

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        // define the IconicsLayoutInflater
        // this is compatible with calligraphy and other libs which wrap the baseContext
        LayoutInflaterCompat.setFactory(getLayoutInflater(), new IconicsLayoutInflater(getDelegate()));
        super.onCreate(savedInstanceState);

        mApplication = (PTApplication) getApplication();
        unbinder = ButterKnife.bind(this);
        mLoadingView = new LoadingView(this, getLoadingMessage());
//        loadState = (ILoadState) findViewById(R.id.load_state_view);
//        mPTLoading = new PTLoading.Builder(this)
//                .setCanceledOnTouchOutside(false)
//                .setIcon(R.drawable.button_loading_icon)
//                .setMsg(getString(R.string.loading_data))
//                .build();
//        mPTToast = new PTToast.Builder(this)
//                .setShowTime(1300)
//                .build();
        if (useEventBus())
            EventBusUtils.register(this);
        onViewCreated(savedInstanceState);
    }

    public abstract String getLoadingMessage();

    protected abstract void onViewCreated(@Nullable Bundle savedInstanceState);

    @Override
    protected void onResume() {
        super.onResume();
        isResume = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isResume = false;
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mLoadingView != null)
            mLoadingView.dismiss();
//        if (mPTLoading != null)
//            mPTLoading.dismiss();
//        if (mPTToast != null)
//            mPTToast.dismiss();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        if (useEventBus())
            EventBusUtils.unregister(this);
    }

    @Override
    public void showLoading() {
        mLoadingView.show();
    }

    @Override
    public void dismissLoading() {
        mLoadingView.dismiss();
    }

    protected boolean useEventBus() {
        return false;
    }

    /**
     * 双击退出App
     *
     * @param exit 退出时间(毫秒数)
     */
    protected boolean exit(long exit) {
        if (System.currentTimeMillis() - exitTime > exit) {
            ToastUtils.show(this, getString(R.string.exit_again));
            exitTime = System.currentTimeMillis();
        } else {
            mApplication.getActivityManager().finishAll();
        }
        return true;
    }


//    public void showOfflineDialog() {
//        if (mOfflineDialog == null) {
//            mOfflineDialog = new SelectDialog.Builder(this)
//                    .setTitle(R.string.offline_desc)
//                    .setCanceledOnTouchOutside(false)
//                    .setPositiveButton(getString(R.string.main_dialog_know), new SelectDialog.OnPositiveClickListener() {
//                        @Override
//                        public void onClick(SelectDialog dialog, Button button) {
//                            mOfflineDialog.dismiss();
//                        }
//                    })
//                    .build();
//        }
//        mOfflineDialog.show();
//    }
}
