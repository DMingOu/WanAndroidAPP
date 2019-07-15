package com.example.wanandroidapp.module.user;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wanandroidapp.R;
import com.example.wanandroidapp.base.presenter.IBasePresenter;
import com.example.wanandroidapp.base.view.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserActivity extends BaseActivity {
    @Override
    public IBasePresenter onBindPresenter() {
        return null;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user;
    }

    @Override
    protected void initToolbar() {
        setSupportActionBar(toolBarUser);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.back_toolbar_32);
            actionBar.setTitle("Me");
        }
    }

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
    }
}
