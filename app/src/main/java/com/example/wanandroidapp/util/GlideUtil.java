package com.example.wanandroidapp.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.orhanobut.logger.Logger;

/**
 * @author: ODM
 * @date: 2019/7/16
 */

    public class GlideUtil {
        //load方法，通过参数和RequestOptions对象实现加载图片
        public static void loadImgUrl(Context context,
                                Object url,
                                ImageView imageView,
                                RequestOptions options) {
            Logger.d("Glide加载图片url" + url);
            Glide.with(context)
                    .load((String) url)
                    .apply(options)
                    .into(imageView);
        }
    public static void loadResPath(Context context,
                                  Object url,
                                  ImageView imageView,
                                  RequestOptions options) {
        Logger.d("Glide加载图片url" + url);
        Glide.with(context)
                .load((Integer) url)
                .apply(options)
                .into(imageView);
    }


}
