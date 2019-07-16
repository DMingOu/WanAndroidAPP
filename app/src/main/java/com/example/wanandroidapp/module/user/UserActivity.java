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
import android.widget.TextView;

import com.example.wanandroidapp.R;
import com.example.wanandroidapp.base.presenter.IBasePresenter;
import com.example.wanandroidapp.base.view.BaseActivity;
import com.example.wanandroidapp.module.login.ui.LoginActivity;
import com.example.wanandroidapp.module.register.ui.RegisterActivity;
import com.orhanobut.logger.Logger;

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
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.back_toolbar_32);
            actionBar.setTitle("Me");
        }
    }

    protected void initView() {
        btLogin.setVisibility(View.VISIBLE);
        btnRegister.setVisibility(View.VISIBLE);
        btnLogout.setVisibility(View.INVISIBLE);
    }
    /**
     * 用户页面的登录按钮的点击事件--跳转去登录页面
     * @param v
     */
    public void toLoginUser(View v){
        switch (v.getId()){
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
     * @param v
     */
    public void toRegisterUser(View v){
        switch (v.getId()){
            case R.id.bt_register:
                Logger.d("用户界面，注册按钮被触发");
                Intent intent = new Intent();
                intent.setClass(this, RegisterActivity.class);
                startActivity(intent);
                break;
                default:
        }
    }

//    /**
//     * 用户页面的退出登录按钮的点击事件--显示和隐藏按钮控件，清除本地密码
//     * @param v
//     */
//    public void toLogout_User(View v) {
//        switch (v.getId()){
//            case R.id.bt_logout:
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        SharedPreferencesUtil.saveLoginSharedPreferences(UserActivity.this,"","");//将存储的用户名和密码清除
//                        mLoginBt.setVisibility(View.VISIBLE);
//                        mRegisterBt.setVisibility(View.VISIBLE);
//                        mLogoutBt.setVisibility(View.INVISIBLE);
//                        mUserNameTv.setText("尚未登录");
//                    }
//                });
//                break;
//        }
//    }

    /**
     * 点击返回键做了处理
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            default:
        }
        return true;
    }



}
