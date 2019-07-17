package com.example.wanandroidapp.module;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.ImageView;

import com.example.wanandroidapp.R;
import com.example.wanandroidapp.core.constant.Constants;
import com.example.wanandroidapp.module.home_pager.ui.ArticleHomeActivity;
import com.orhanobut.logger.Logger;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.functions.*;
import io.reactivex.schedulers.Schedulers;

import static com.example.wanandroidapp.core.constant.Constants.ANIM_TIME;
import static com.example.wanandroidapp.core.constant.Constants.SCALE_END;

public class WelcomeActivity extends AppCompatActivity {

    @BindView(R.id.iv_entry)
    ImageView ivEntry;
    private static final int[] ImgResource={
            R.drawable.welcomimg1,
            R.drawable.welcomimg2,
            R.drawable.welcoimg3
    };
    CompositeDisposable compositeDisposable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        startAction();
    }

    public void startAction(){
        //SystemClock.elapsedRealtime()是从开机到现在的毫秒数（手机睡眠(sleep)的时间也包括在内）
        Random random = new Random(SystemClock.elapsedRealtime());
        ivEntry.setImageResource(ImgResource[random.nextInt(ImgResource.length)]);
         compositeDisposable = new CompositeDisposable();
        Disposable disposable = Single.timer(1600, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(aLong -> {
                    Logger.d("动画启动");
                    startAnim();
                });
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }

    /**
     * Start anim.
     * 动画结束后启动主页面
     */
    public void startAnim() {

        ObjectAnimator animatorX = ObjectAnimator.ofFloat(ivEntry, "scaleX", 1f, SCALE_END);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(ivEntry, "scaleY", 1f, SCALE_END);

        AnimatorSet set = new AnimatorSet();
        set.setDuration(ANIM_TIME).play(animatorX).with(animatorY);
        set.start();
        set.addListener(new AnimatorListenerAdapter()
        {
            @Override
            public void onAnimationEnd(Animator animation)
            {
                startActivity(new Intent(WelcomeActivity.this, ArticleHomeActivity.class));
                WelcomeActivity.this.finish();
            }
        });
    }

    /**
     * 屏蔽物理返回按钮
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode== KeyEvent.KEYCODE_BACK){
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}




