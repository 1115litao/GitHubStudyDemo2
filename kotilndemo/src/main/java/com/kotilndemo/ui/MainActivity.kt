package com.kotilndemo.ui

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.kotilndemo.R
import com.kotilndemo.bean.ShopData

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun initView() {

    }

    override fun initListener() {

    }

    override fun initData() {

        ShopData.Version
        var dataList = mutableListOf<ShopData>()

    }

    override fun onClick(v: View?) {

    }

fun Activity.showToast(msg:String){
    Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
}

}
