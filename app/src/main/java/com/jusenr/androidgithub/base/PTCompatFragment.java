package com.jusenr.androidgithub.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jusenr.androidlibrary.base.BasePresenter;
import com.jusenr.androidlibrary.base.loading.LoadView;
import com.jusenr.androidlibrary.base.loading.LoadingView;
import com.jusenr.toolslibrary.utils.EventBusUtils;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 不继承rxfragment
 * Created by Penglingxiao on 2017/4/20.
 */

public abstract class PTCompatFragment<P extends BasePresenter> extends Fragment implements LoadView {
    @Inject
    protected P mPresenter;

    private Unbinder unbinder;

    protected PTApplication mApplication;

    private LoadingView mLoadingView;
//    protected PTLoading mPTLoading;
//    protected PTToast mPTToast;

    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
//        mApplication = (PTApplication) getActivity().getApplication();
        View view = LayoutInflater.from(getActivity()).inflate(getLayoutId(), null);
        unbinder = ButterKnife.bind(this, view);
        mLoadingView = new LoadingView(getActivity(), getLoadingMessage());

//        mPTLoading = new PTLoading.Builder(getActivity())
//                .setCanceledOnTouchOutside(false)
//                .setIcon(R.drawable.button_loading_icon)
//                .setMsg(getString(R.string.loading_data))
//                .build();
//        mPTToast = new PTToast.Builder(getActivity())
//                .setShowTime(1300)
//                .build();

        if (useEventBus())
            EventBusUtils.register(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
        injectComponent();
    }

    protected abstract int getLayoutId();

    public abstract String getLoadingMessage();

    protected abstract void injectComponent();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if (mPresenter != null) {
            mPresenter.onDestroy();
            mPresenter = null;
        }
        if (useEventBus())
            EventBusUtils.unregister(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mLoadingView != null)
            mLoadingView.dismiss();
//        if (mPTLoading != null)
//            mPTLoading.dismiss();
//        if (mPTToast != null)
//            mPTToast.dismiss();
    }

    protected boolean useEventBus() {
        return false;
    }

    public void showLoading() {
        mLoadingView.show();
    }

    public void hideLoading() {
        mLoadingView.dismiss();
    }
}
