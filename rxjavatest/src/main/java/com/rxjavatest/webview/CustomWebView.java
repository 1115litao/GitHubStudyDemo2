package com.rxjavatest.webview;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import com.rxjavatest.R;
import com.rxjavatest.base.LT_BaseActivity;
import com.rxjavatest.rxjava.HttpDialog;

/*
* http://blog.csdn.net/chenzhi0712/article/details/53666976
*https://www.jianshu.com/p/5a54aa3ef80a
* https://www.jianshu.com/p/76d625ece9de
* */
public class CustomWebView extends LT_BaseActivity implements View.OnTouchListener {

    private TextView title_back;
    private TextView title_name;
    private WebView custom_web;
    private Intent intent;
    private String webUrl;
    private WebSettings settings;
    private HttpDialog httpDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_custom_web_view);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initData() {
        intent = getIntent();
        webUrl = intent.getStringExtra("weburl");
    }

    @Override
    public void initView() {
        httpDialog = new HttpDialog(this);
        title_back = findViewById(R.id.title_back);
        title_name = findViewById(R.id.title_name);
        custom_web = findViewById(R.id.custom_web);
        settings = custom_web.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);//将图片调整到适合Web View的大小
        settings.setLoadWithOverviewMode(true);//缩放至屏幕的大小
        settings.setDisplayZoomControls(false);//隐藏原生的缩放控件
        if(!TextUtils.isEmpty(webUrl)){
            custom_web.loadUrl(webUrl);
        }
    }

    @Override
    public void initListener() {

        title_back.setOnTouchListener(this);

        //重写此方法表面点击跳转的链接还是在当前WebView里
            custom_web.setWebViewClient(new WebViewClient(){
                //该方法是在API21后出现的
   /*             @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    //   view.loadUrl(request.getUrl().toString());
                    return shouldOverrideUrlLoading(view,request.getUrl().toString());
                }*/

                //该方法是原来的方法  API21后不推荐使用了
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }
                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);
                    if(httpDialog!=null&&!httpDialog.isShowing()){
                        httpDialog.show();
                    }
                }
                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    if(httpDialog!=null&&httpDialog.isShowing()){
                        httpDialog.dismiss();
                    }
                }
            });

        //返回到当前WebView
        custom_web.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_DOWN){
                    if(keyCode == KeyEvent.KEYCODE_BACK&&custom_web.canGoBack()){
                        custom_web.goBack();
                        return true;
                    }
                }
                return false;
            }
        });
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()){
            case R.id.title_back:
            finish();
            break;
        }
        return false;
    }
}
