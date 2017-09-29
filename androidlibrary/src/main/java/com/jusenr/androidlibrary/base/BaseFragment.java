package com.jusenr.androidlibrary.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle.components.RxFragment;

/**
 * Created by riven_chris on 2017/4/13.
 */

public abstract class BaseFragment extends RxFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(getLayoutId(), null);
        return view;
    }

    protected abstract int getLayoutId();
}
