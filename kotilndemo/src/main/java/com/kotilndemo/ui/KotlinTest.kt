package com.kotilndemo.ui

/**
 * @description
 * @author      李涛
 * @version
 * @Date        2018/9/6.
 */

typealias  mListener = (String) -> Unit
typealias  mListen2 = (str: String, age: Int) -> Unit
typealias  mListen4 = (mListener) -> Unit
typealias  mListen5 = (mListen2) -> Unit
typealias  mListen3 = (mListen4, mListen5) -> Unit

interface  callBack {
    fun onSuccess(str:String)
    fun onFaile(str:String)
}

class KotlinTest {
    private lateinit var listtener: mListener
    private lateinit var listener2: mListen2
    private lateinit var listtener3: mListen3


    var callback:callBack? = null
    fun setListener(listener: mListener) {
        this.listtener = listener
    }

    fun setListener2(listener: mListen3) {
        this.listtener3 = listener
    }

    fun setCallBack(callback:callBack){
        this.callback = callback
    }





}


