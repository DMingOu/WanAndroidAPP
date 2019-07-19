package com.example.wanandroidapp.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.wanandroidapp.R;
import com.orhanobut.logger.Logger;
import com.youth.banner.loader.ImageLoader;
import com.yyydjk.library.BannerLayout;

/**
 * @author: ODM
 * @date: 2019/7/16
 */
public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.loading);
        GlideUtil.loadImgUrl(context,(String)path,imageView,options);
    }
}