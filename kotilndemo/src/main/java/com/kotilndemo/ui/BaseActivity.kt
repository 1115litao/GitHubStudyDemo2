package com.kotilndemo.ui

import android.app.Activity
import android.os.Bundle
import android.view.View

/**
 * @description
 * @author      李涛
 * @version
 * @Date        2018/4/27.
 */

abstract class BaseActivity : Activity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initListener()
        initData()
    }

    abstract  fun initView()
    abstract  fun initListener()
    abstract  fun initData()
}