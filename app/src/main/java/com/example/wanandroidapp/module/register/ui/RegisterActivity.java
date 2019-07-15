package com.example.wanandroidapp.module.register.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wanandroidapp.R;
import com.example.wanandroidapp.base.presenter.IBasePresenter;
import com.example.wanandroidapp.base.view.BaseActivity;
import com.example.wanandroidapp.module.login.ui.LoginActivity;
import com.example.wanandroidapp.module.register.contract.Contract;
import com.example.wanandroidapp.module.register.presenter.Presenter;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity<P extends IBasePresenter> extends BaseActivity<Presenter> implements Contract.View {

    @BindView(R.id.tool_bar_register)
    Toolbar toolBarRegister;
    @BindView(R.id.et_register_username)
    EditText etRegisterUsername;
    @BindView(R.id.et_register_password)
    EditText etRegisterPassword;
    @BindView(R.id.et_register_confirm)
    EditText etRegisterConfirm;
    @BindView(R.id.bt_register)
    Button btRegister;
    @BindView(R.id.tv_login)
    TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @Override
    public Presenter onBindPresenter() {
        return new Presenter(this);
    }

    @Override
    protected void initView() {
        //内容和标记都可以更改的文本类实例，意在让文字可点击
        final SpannableStringBuilder style_mLoginTv = new SpannableStringBuilder();
        style_mLoginTv.append("已有账号？返回登录页面");
        //设置部分文字点击事件
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Intent intent = new Intent();
                intent.setClass(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        };
        style_mLoginTv.setSpan(clickableSpan, 5, 11, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置可点击文字的颜色
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.parseColor("#FF4081"));//red
        style_mLoginTv.setSpan(foregroundColorSpan,5,11, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //将Spannable配置给TextView
        tvLogin.setMovementMethod(LinkMovementMethod.getInstance());
        //给文本配置设定的内容
        tvLogin.setText(style_mLoginTv);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initToolbar() {
        setSupportActionBar(toolBarRegister);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.back_toolbar_32);
            actionBar.setTitle("注册");
        }
    }

    /**
     * 说明：用户点击注册键后，符合条件就发送POST请求申请注册
     * @param v 控件
     */
    public void register(View v){
        //获取点击控件的ID，并根据ID判断怎样处理
        int id = v.getId();
        switch (id) {
            case R.id.bt_register:
                //点击注册键隐藏键盘，让用户可以更清楚看到提醒
                hideKeyboard(etRegisterConfirm);
                //获取用户名
                final String username = etRegisterUsername.getText().toString();
                //获取用户密码和确认密码
                final String userpwd = etRegisterPassword.getText().toString();
                final String userpwd_confirm = etRegisterConfirm.getText().toString();
                Logger.d(username,userpwd,userpwd_confirm);
                if(TextUtils.isEmpty(username) || TextUtils.isEmpty(userpwd) || TextUtils.isEmpty(userpwd_confirm)){
                    Toast.makeText(this,"用户名或者密码不能为空",Toast.LENGTH_LONG).show();
                    //让空框获得焦点,让用户可以输入
                    if(!TextUtils.isEmpty(username)){
                        etRegisterUsername.requestFocus();
                    }else if(! TextUtils.isEmpty(userpwd)){
                        etRegisterPassword.requestFocus();
                    }else if(TextUtils.isEmpty(userpwd_confirm)){
                        etRegisterConfirm.requestFocus();
                    }
                } else if(! userpwd.contentEquals(userpwd_confirm)) {
                    //若两个密码框的内容不同
                    Toast.makeText(this,"密码与再次确定密码不一致！",Toast.LENGTH_LONG).show();
                    //让用户修改确认密码部分
                    etRegisterConfirm.requestFocus();
                } else {
                    //通知P层去 请求注册操作
                    getPresenter().toRegister(username,userpwd,userpwd_confirm);
                }
                break;
                default:
        }

    }

    @Override
    public void registerSuccess(String errorMsg) {
        Toast.makeText(this, "注册成功！", Toast.LENGTH_LONG).show();
    }

    @Override
    public void registerFailure(String errorMsg) {
        Toast.makeText(this, "注册失败，原因为 " + errorMsg, Toast.LENGTH_LONG).show();
    }

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

    /**
     * 隐藏软键盘
     * @param view ：一般是EditText使用
     */
    public void hideKeyboard(View view) {
        InputMethodManager manager = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (manager != null) {
            manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
