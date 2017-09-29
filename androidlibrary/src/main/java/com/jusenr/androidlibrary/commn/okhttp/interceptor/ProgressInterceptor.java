package com.jusenr.androidlibrary.commn.okhttp.interceptor;

import com.jusenr.androidlibrary.commn.okhttp.progress.ProgressResponseBody;
import com.jusenr.androidlibrary.commn.okhttp.progress.ProgressResponseListener;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * 进度拦截器
 * Created by guchenkai on 2015/10/26.
 */
public class ProgressInterceptor implements Interceptor {
    private ProgressResponseListener progressListener;

    public ProgressInterceptor(ProgressResponseListener progressListener) {
        this.progressListener = progressListener;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        return originalResponse.newBuilder().body(new ProgressResponseBody(originalResponse.body(), progressListener)).build();
    }
}
