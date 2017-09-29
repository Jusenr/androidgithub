package com.jusenr.androidlibrary.commn.okhttp.interceptor;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * header信息拦截器
 * Created by luowentao on 2016/10/27.
 */
public class HeaderInfoInterceptor implements Interceptor {
    private static final String TAG = HeaderInfoInterceptor.class.getSimpleName();
    private String version;

    public HeaderInfoInterceptor(String version) {
        this.version = version;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request newHeaderRequest = request.newBuilder()
                .addHeader("app-version", version)
                .addHeader("Accept", getAcceptHeader())
                .addHeader("User-Agent", "AndroidGitHubApp")
                .build();

        long startNs = System.nanoTime();
        Response response;
        try {
            response = chain.proceed(newHeaderRequest);
        } catch (Exception e) {
            throw e;
        }
        long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);
        Log.e(TAG, "intercept: " + tookMs + "ms");

        return response;
    }

    private String getAcceptHeader() {
        return "application/vnd.github.v3.json";
    }
}
