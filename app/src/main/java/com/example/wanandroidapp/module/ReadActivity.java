package com.example.wanandroidapp.module;

import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.wanandroidapp.R;
import com.example.wanandroidapp.base.view.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.KeyEvent.KEYCODE_BACK;

public class ReadActivity extends AppCompatActivity {

    @BindView(R.id.tool_bar_web)
    Toolbar toolBarWeb;
    @BindView(R.id.pb_web_loading)
    ProgressBar pbWebLoading;
    @BindView(R.id.wv_content)
    WebView wvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webcontent);
        ButterKnife.bind(this);
        initViews();
        initToolBar();
        String url = getIntent().getStringExtra("url");
        wvContent.loadUrl(url);
    }


    protected void initToolBar(){
        setSupportActionBar(toolBarWeb);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            //使左上角图标是否显示，如果设成false，则没有程序图标，仅仅就个标题，否则，显示应用程序图标，对应id为android.R.id.home，对应ActionBar.DISPLAY_SHOW_HOME
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.back_toolbar_32);
            String title = getIntent().getStringExtra("title");
            //由搜索界面点进来的网页，传进来的标题是带有html语言的
            actionBar.setTitle(Html.fromHtml(title));
        }
    }


    protected  void initViews(){
        pbWebLoading.setVisibility(View.VISIBLE);
        wvContent.getSettings().setJavaScriptEnabled(true);
        wvContent.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();    //表示等待证书响应
                // handler.cancel();      //表示挂起连接，为默认方式
                // handler.handleMessage(null);    //可做其他处理
            }
        });
        // 5.1以上默认禁止了https和http混用，下面是开启方法
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            wvContent.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        wvContent.setWebChromeClient(new WebChromeClient() {
            @Override
            //重写WebChromeClient监听网页加载的进度,从而实现进度条
            public void onProgressChanged(WebView view, int newProgress) {
                //显示进度条
                pbWebLoading.setProgress(newProgress);
                pbWebLoading.setVisibility(View.VISIBLE);
                if (newProgress == 100) {
                    //加载完毕隐藏进度条
                    pbWebLoading.setVisibility(View.GONE);
                    wvContent.setVisibility(View.VISIBLE);
                }
                super.onProgressChanged(view, newProgress);
            }
        });




    }
    @Override
    //处理 back 事件，网页回退，取代直接关闭浏览器
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KEYCODE_BACK) && wvContent.canGoBack()) {
            wvContent.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 点击导航栏返回键做了处理
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


