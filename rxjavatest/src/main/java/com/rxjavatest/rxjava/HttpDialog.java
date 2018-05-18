package com.rxjavatest.rxjava;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.Window;
import android.view.WindowManager;

import com.rxjavatest.R;

/**
 * @author 李涛
 * @description
 * @Date 2017/10/26.
 */


public class HttpDialog extends Dialog {

    public static int TRAN_STYLE = R.style.style_dialog;

    public HttpDialog(@NonNull Context context) {
        super(context,TRAN_STYLE);
        initView();
    }
    public HttpDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        initView();
    }

    private void initView() {
        setWindowLayoutStyleAttr();
        setContentView(R.layout.dialog_view);
    }

    /**
     * 设置dialog属性
     */
    public void setWindowLayoutStyleAttr() {
        setCanceledOnTouchOutside(false);
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.alpha = 0.7f;
        lp.dimAmount = 0f;
        window.setAttributes(lp);
    }


}
