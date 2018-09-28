package com.kotilndemo.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.kotilndemo.R
import kotlinx.android.synthetic.main.activity_kotlin_test.*

class KotlinTestActivity : AppCompatActivity() {

    private var nickName: String? = "lalallala"
    private var name: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_test)
        //一个简单的判空
        name = nickName ?: "litao"
        text_View.text = name
        interfaceTest()
    }

    private fun interfaceTest() {
        var kotlinTest = KotlinTest()
        kotlinTest.setListener2 { fun0, fun2 ->
            fun0 {

            }
            fun2 { str, int ->

            }
        }

        kotlinTest.apply {
            setCallBack(object : callBack {
                override fun onSuccess(str: String) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
                override fun onFaile(str: String) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
            })
        }
    }


}
