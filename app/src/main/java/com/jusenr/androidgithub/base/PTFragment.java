package com.jusenr.androidgithub.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jusenr.androidlibrary.base.BaseFragment;
import com.jusenr.androidlibrary.base.loading.LoadView;
import com.jusenr.androidlibrary.base.loading.LoadingView;
import com.jusenr.toolslibrary.utils.EventBusUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by riven_chris on 2017/4/20.
 */

public abstract class PTFragment extends BaseFragment implements LoadView {

    protected PTApplication mApplication;
    private Unbinder unbinder;

    private LoadingView mLoadingView;
//    protected ILoadState loadState;

    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public final void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mApplication = (PTApplication) getActivity().getApplication();
        unbinder = ButterKnife.bind(this, view);
        mLoadingView = new LoadingView(getActivity(), getLoadingMessage());
//        loadState = (ILoadState) view.findViewById(R.id.load_state_view);
        if (useEventBus())
            EventBusUtils.register(this);
        onViewCreateFinish(view, savedInstanceState);
    }

    public abstract String getLoadingMessage();

    public abstract void onViewCreateFinish(View view, @Nullable Bundle savedInstanceState);

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if (useEventBus())
            EventBusUtils.unregister(this);

    }

    @Override
    public void onStop() {
        super.onStop();
        if (mLoadingView != null)
            mLoadingView.dismiss();
    }

    public void showLoading() {
        mLoadingView.show();
    }

    public void dismissLoading() {
        mLoadingView.dismiss();
    }

    protected boolean useEventBus() {
        return false;
    }
}
