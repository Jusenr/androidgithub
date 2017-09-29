package com.jusenr.androidgithub.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jusenr.androidlibrary.base.BaseFragment;
import com.jusenr.toolslibrary.utils.EventBusUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by riven_chris on 2017/4/20.
 */

public abstract class PTFragment extends BaseFragment {

    protected PTApplication mApplication;
    private Unbinder unbinder;

//    protected LoadingHUD loading;
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
//        loading = LoadingHUD.build(getActivity());
//        loadState = (ILoadState) view.findViewById(R.id.load_state_view);
        if (useEventBus())
            EventBusUtils.register(this);
        onViewCreateFinish(view, savedInstanceState);
    }

    public abstract void onViewCreateFinish(View view, @Nullable Bundle savedInstanceState);

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if (useEventBus())
            EventBusUtils.unregister(this);

    }

//    public void showLoading() {
//        loading.show();
//    }
//
//    public void hideLoading() {
//        loading.dismiss();
//    }

    protected boolean useEventBus() {
        return false;
    }
}
