package com.jusenr.androidgithub.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jusenr.androidgithub.R;
import com.jusenr.androidlibrary.base.BaseActivity;
import com.jusenr.toolslibrary.utils.EventBusUtils;
import com.jusenr.toolslibrary.utils.ToastUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by riven_chris on 2017/4/20.
 */

public abstract class PTActivity extends BaseActivity {

    protected PTApplication mApplication;
    protected boolean isResume;
    private long exitTime = 0;

    private Unbinder unbinder;

//    protected LoadingHUD loading;
//    protected ILoadState loadState;
//    protected PTLoading mPTLoading;
//    protected PTToast mPTToast;
//    protected SelectDialog mOfflineDialog;

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApplication = (PTApplication) getApplication();
        unbinder = ButterKnife.bind(this);
//        loading = LoadingHUD.build(this);
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
//        if (loading != null)
//            loading.dismiss();
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
