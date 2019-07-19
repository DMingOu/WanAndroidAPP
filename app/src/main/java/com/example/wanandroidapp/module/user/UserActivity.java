package com.example.wanandroidapp.module.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.bumptech.glide.request.RequestOptions;
import com.example.wanandroidapp.R;
import com.example.wanandroidapp.app.WanAndroidApp;
import com.example.wanandroidapp.module.login.ui.LoginActivity;
import com.example.wanandroidapp.module.register.ui.RegisterActivity;
import com.example.wanandroidapp.module.user.history.ui.HistoryActivity;
import com.example.wanandroidapp.util.GlideUtil;
import com.example.wanandroidapp.util.SharedPreferencesUtil;
import com.orhanobut.logger.Logger;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserActivity extends AppCompatActivity {


    @BindView(R.id.tool_bar_User)
    Toolbar toolBarUser;
    @BindView(R.id.iv_user)
    ImageView ivUser;
    @BindView(R.id.tv_login_name)
    TextView tvLoginName;
    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.bt_register)
    Button btnRegister;
    @BindView(R.id.bt_logout)
    Button btnLogout;
    @BindView(R.id.activity_user)
    RelativeLayout activityUser;
    @BindView(R.id.more_page_row0)
    TableRow tableRow;
    @BindView(R.id.tv_history)
    TextView tvHistory;
    @BindView(R.id.iv_history_enter)
    ImageView ivHistoryEnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);
        initView();
        initToolbar();
    }

    protected int getLayoutId() {
        return R.layout.activity_user;
    }


    protected void initToolbar() {
        setSupportActionBar(toolBarUser);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.back_toolbar_32);
            actionBar.setTitle("我");
        }
    }

    protected void initView() {
        HashMap<String,String> userInfo = SharedPreferencesUtil.getLoginSharedPreferences(this);
        String userName = userInfo.get("loginUserName");
        RequestOptions options = new RequestOptions()
                .override(200, 200);

        //如果尚未登录就显示登录与注册按钮
        if("".equals(userName)){
            btLogin.setVisibility(View.VISIBLE);
            btnRegister.setVisibility(View.VISIBLE);
            btnLogout.setVisibility(View.INVISIBLE);
            GlideUtil.loadResPath(WanAndroidApp.getContext(),R.mipmap.notlogin,ivUser,options);
        } else {
            btLogin.setVisibility(View.GONE);
            btnRegister.setVisibility(View.GONE);
            btnLogout.setVisibility(View.VISIBLE);
            tvLoginName.setText("欢迎来到玩安卓 " +"\n"+ userName);
            GlideUtil.loadResPath(WanAndroidApp.getContext(),R.mipmap.android_useractivity_128,ivUser,options);

        }

        ivHistoryEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(UserActivity.this, HistoryActivity.class);
                startActivity(intent);
            }
        });

    }

    /**
     * 用户页面的登录按钮的点击事件--跳转去登录页面
     *
     * @param v
     */
    public void toLoginUser(View v) {
        switch (v.getId()) {
            case R.id.bt_login:
                Logger.d("用户界面，登录按钮被触发");
                Intent intent = new Intent();
                intent.setClass(this, LoginActivity.class);
                startActivity(intent);
                break;
            default:
        }
    }

    /**
     * 用户页面的注册按钮的点击事件--跳转去注册页面
     *
     * @param v
     */
    public void toRegisterUser(View v) {
        switch (v.getId()) {
            case R.id.bt_register:
                Logger.d("用户界面，注册按钮被触发");
                Intent intent = new Intent();
                intent.setClass(this, RegisterActivity.class);
                startActivity(intent);
                break;
            default:
        }
    }

    /**
     * 用户页面的退出登录按钮的点击事件--显示和隐藏按钮控件，清除本地密码
     * @param v
     */
    public void toLogoutUser(View v) {
        switch (v.getId()) {
            case R.id.bt_logout:
                //将存储的用户名和密码清除
                SharedPreferencesUtil.saveLoginSharedPreferences(UserActivity.this, "", "");
                btLogin.setVisibility(View.VISIBLE);
                btnRegister.setVisibility(View.VISIBLE);
                btnLogout.setVisibility(View.INVISIBLE);
                tvLoginName.setText("尚未登录");
                break;
            default:
        }
    }



    /**
     * 点击返回键做了处理
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
        }
        return true;
    }


}
