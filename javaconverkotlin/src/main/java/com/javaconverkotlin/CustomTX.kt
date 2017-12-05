package com.javaconverkotlin

import android.content.Context
import android.support.v7.widget.AppCompatAutoCompleteTextView
import android.util.AttributeSet

/**
 * @author 李涛
 * @description
 * @Date 2017/11/1.
 */


class CustomTX : AppCompatAutoCompleteTextView {
    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}
}
