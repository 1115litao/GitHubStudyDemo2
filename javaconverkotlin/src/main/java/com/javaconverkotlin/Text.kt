package com.javaconverkotlin

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.os.Message

/**
 * @author 李涛
 * @description
 * @Date 2017/11/1.
 */


class Text : Activity() {

/*    internal var handler: Handler = object : Handler() {

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
        }
    }*/


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }


/*      private static class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    }*/

    class  myhandler : Handler(){
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
        }
    }
}
