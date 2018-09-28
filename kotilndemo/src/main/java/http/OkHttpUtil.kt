package http

import android.util.Log
import http.builder.GetBuilder
import http.builder.PostFormBuilder
import http.builder.PostStringBuilder
import http.callback.Callback
import http.callback.DefaultCallback
import http.log.SimpleInterceptor
import http.request.RequestCall
import okhttp3.*
import java.io.IOException
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

/**
 * @description
 * @author      李涛
 * @version 1.0
 * @Date        2018/8/23.
 */

class OkHttpUtil {
    private var mOkHttpClient: OkHttpClient? = null

    private constructor  (okHttpClient: OkHttpClient?) {
        mOkHttpClient = okHttpClient ?: getOkHtpClientBuilder().build()
        mPlatform = Platform.get()
    }

    /**
     * 对OkHttpClient进行常规配置,如连接超时、读取超时。
     * 当需要对某一请求进行设置改变是，可以使用OkHttpClient.clone()获取实例并进行改变
     * @return OkHttpClient的配置属性
     */
    private fun getOkHtpClientBuilder(): OkHttpClient.Builder {
        var builder = OkHttpClient.Builder()
        builder.connectTimeout(10, TimeUnit.SECONDS)//链接超时10s
        builder.writeTimeout(30, TimeUnit.SECONDS)//写入超时10s
        builder.readTimeout(30, TimeUnit.SECONDS)//读取超时10s
        builder.addInterceptor(SimpleInterceptor())
        return builder
    }

    /**
     * 获取Response以作调试使用
     * @return
     */
    fun getResponse(): Response? {

        if (null == response)
            Log.e("http", "response may be null")
        return response
    }

    fun getOkHttpClient(): OkHttpClient {
        return mOkHttpClient!!
    }

    companion object {
        private var mInstance: OkHttpUtil? = null
        const val DEFAULT_MILLISECONDS: Long = 10_000L//默认超时时间
        private var mPlatform: Platform? = null //切换到主线程的工具类
        private var response: Response? = null //网络请求的相应类

        fun getInstance(): OkHttpUtil {
            return initClient(null)
        }

        fun initClient(okHttpClient: OkHttpClient?): OkHttpUtil {
            if (mInstance == null) {
                synchronized(OkHttpUtil::class.java) {
                    if (mInstance == null) {
                        mInstance = OkHttpUtil(okHttpClient)
                    }
                }
            }

            return mInstance!!
        }

        /**
         * 返回get的配置
         * @return
         */
        fun get(): GetBuilder {

            return GetBuilder()
        }

        /**
         * 返回post的配置(可用来上传String字符串)
         * @return
         */
        fun postString(): PostStringBuilder {
            return PostStringBuilder()
        }

        /**
         * 返回post的配置(可用来上传Json字符串)
         * @return
         */
        fun postJson(): PostStringBuilder {
            return PostStringBuilder().mediaType(MediaType.parse("application/json; charset=utf-8")!!)
        }
        /**
         * 返回post的配置(可用来进行表单提交)
         * @return
         */
        fun post(): PostFormBuilder {
            return PostFormBuilder()
        }

        /**
         * 一个简单的get请求,该请求不支持属性配置
         * @param url 请求路径
         * @param callback 请求的数据回调
         */
        fun <T>simpleGet(url: String, callback: Callback<T>) {
            get().url(url).build().execute(callback)
        }
        /**
         * 一个简单的post请求,该请求不支持属性配置
         * @param url 请求路径
         * @param content 上传的字符串
         * @param callback 请求的数据回调
         */
        fun<T> simplePost(url: String, content:String,callback: Callback<T>){
            postString().content(content).url(url).build().execute(callback)
        }


        fun getExecutor(): Executor {
            return mPlatform!!.defaultCallbackExecutor()
        }
    }

    /**
     * 将消息发送到主线程
     * @param request
     * @param callback
     */
    private fun <T>execute(request: Request, callback: Callback<T>) {
        val id = 0
        getOkHttpClient().newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: Call, e: IOException) {
                sendFailResultCallback(call, e, callback, id)
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                sendSusccessResultCallback(response.body()!!.string(), callback!!, id)
            }
        })
    }



    fun <T>execute(requestCall: RequestCall, callback: Callback<T>?) {

        val finalCallback  = callback?: DefaultCallback()

        val id: Int = requestCall.getOkHttpRequest().getIds()

        requestCall.getCall().enqueue(object : okhttp3.Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                sendFailResultCallback(call, e, callback!!, id)
            }

            override fun onResponse(call: Call?, response: Response?) {
                OkHttpUtil.response = response
                try {
                    if (call!!.isCanceled) {
                        sendFailResultCallback(call, IOException("Canceled!"), finalCallback, id)
                    }
                    if (!finalCallback.validateResponse(response!!, id)) {
                        sendFailResultCallback(call, IOException("request failed , reponse's code is : " + response.code()), finalCallback, id)
                    }

                    val o = finalCallback.parseNetworkResponse(response, id)
                    sendSusccessResultCallback(o!!, finalCallback, id)
                } catch (e: Exception) {
                    sendFailResultCallback( call, e, finalCallback, id)
                } finally {
                    response!!.body()?.close()
                }
            }
        })
    }


    fun <T>sendFailResultCallback(call: Call?, e: Exception?, callback: Callback<T>, id: Int?) {
        mPlatform?.execute(Runnable {
            callback.onError(call, e, id)
            callback.onAfter(id)
        })
    }

    fun <T>sendSusccessResultCallback(any: Any?, callback: Callback<T>, id: Int?) {
        mPlatform?.execute(Runnable {
            callback.onResponse(any as T, id)
            callback.onAfter(id)
        })
    }

    /**
     * 取消网络请求，通过tag进行标记
     * @param tag 标记
     */
    fun cancelTag(tag: Any) {
        for (call in mOkHttpClient!!.dispatcher().queuedCalls()) {
            if (tag == call.request().tag()) {
                call.cancel()
            }
        }
        for (call in mOkHttpClient!!.dispatcher().runningCalls()) {
            if (tag == call.request().tag()) {
                call.cancel()
            }
        }
    }

}