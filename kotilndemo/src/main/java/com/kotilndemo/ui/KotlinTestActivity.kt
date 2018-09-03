package com.kotilndemo.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.kotilndemo.R
import kotlinx.android.synthetic.main.activity_kotlin_test.*

class KotlinTestActivity : AppCompatActivity() {

    private   var  nickName : String? = "lalallala"
    private var  name : String? = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_test)
        //一个简单的判空
        name = nickName?:"litao"

        text_View.text = name


    }

}
