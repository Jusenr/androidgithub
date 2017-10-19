package com.jusenr.androidgithub.guidance;

import android.content.Context;
import android.widget.ImageView;

import com.youth.banner.loader.ImageLoader;

/**
 * Description:
 * Copyright  : Copyright (c) 2017
 * Email      : jusenr@163.com
 * Author     : Jusenr
 * Date       : 2017/10/19
 * Time       : 10:45
 * Project    ：androidgithub.
 */
public class BannerImageLoader extends ImageLoader {

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        com.nostra13.universalimageloader.core.ImageLoader instance = com.nostra13.universalimageloader.core.ImageLoader.getInstance();
        instance.displayImage((String) path, imageView);
    }
}
