package com.example.wanandroidapp.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

/**
 * @author: ODM
 * @date: 2019/7/16
 */

    public class GlideUtil {
        //load方法，通过参数和RequestOptions对象实现加载图片
        public static void load(Context context,
                                String url,
                                ImageView imageView,
                                RequestOptions options) {
            Glide.with(context)
                    .load(url)
                    .apply(options)
                    .into(imageView);
        }


}
