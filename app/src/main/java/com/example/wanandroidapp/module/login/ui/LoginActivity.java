package com.example.wanandroidapp.module.login.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wanandroidapp.R;
import com.example.wanandroidapp.base.presenter.IBasePresenter;
import com.example.wanandroidapp.base.view.BaseActivity;
import com.example.wanandroidapp.module.article_home.ui.ArticleHomeActivity;
import com.example.wanandroidapp.module.login.contract.Contract;
import com.example.wanandroidapp.module.login.presenter.Presenter;
import com.example.wanandroidapp.module.register.ui.RegisterActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity <P extends IBasePresenter> extends BaseActivity<Presenter>implements Contract.View {

    @BindView(R.id.tool_bar_login)
    Toolbar toolBarLogin;
    @BindView(R.id.et_login_username)
    EditText etLoginUsername;
    @BindView(R.id.et_login_password)
    EditText etLoginPassword;
    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.tv_register)
    TextView tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

    }

    @Override
    public Presenter onBindPresenter() {
        return  new Presenter(this);
    }

    @Override
    protected void initView() {
        //SpannableStringBuilder内容和标记都可以更改的文本类实例，意在让文字可点击
        final SpannableStringBuilder style_mRegisterTv = new SpannableStringBuilder();
        style_mRegisterTv.append("还没有账号？快来注册一个吧！");
        //设置部分文字点击事件
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        };
        style_mRegisterTv.setSpan(clickableSpan, 6, 14, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置可点击文字的颜色
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.parseColor("#FF4081"));
        style_mRegisterTv.setSpan(foregroundColorSpan,6,14, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //将Spannable配置给TextView
        tvRegister.setMovementMethod(LinkMovementMethod.getInstance());
        //给文本配置设定的内容
        tvRegister.setText(style_mRegisterTv);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user;
    }

    @Override
    protected void initToolbar() {
        setSupportActionBar(toolBarLogin);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.back_toolbar_32);
            actionBar.setTitle("登录");
        }
    }

    /**
     *  登录按钮的点击事件，发送登录的POST请求
     * @param v 控件
     */
    public void login(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.bt_login:
                final String userName = etLoginUsername.getText().toString();
                final String userPwd = etLoginPassword.getText().toString();
                if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(userPwd)) {
                    Toast.makeText(LoginActivity.this, "用户名或密码不能为空", Toast.LENGTH_LONG).show();
                    if (TextUtils.isEmpty(userName)) {
                        etLoginUsername.requestFocus();
                    }
                    if (TextUtils.isEmpty(userPwd)) {
                        etLoginPassword.requestFocus();
                    }
                } else {
                    getPresenter().toLogin(userName,userPwd);

                }
                break;

                default:
        }
    }
    /**
     * 对登录成功后进行处理--提醒用户,跳转界面
     *
     */
    @Override
    public void loginSuccess(String errorMsg) {
        Toast.makeText(this,"登录成功,Welcome",Toast.LENGTH_LONG).show();
        Intent intent = new Intent();
        intent.setClass(LoginActivity.this, ArticleHomeActivity.class);
        startActivity(intent);
    }
    /**
     * 登录失败--提醒用户原因
     *
     */

    @Override
    public void loginFailure(String errorMsg) {
        Toast.makeText(this,"登录失败   "+errorMsg,Toast.LENGTH_LONG).show();
    }

    /**
     * 对标题栏返回键做了处理
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

