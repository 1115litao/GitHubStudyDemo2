package http.request

import http.OkHttpUtil
import http.callback.Callback
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.util.concurrent.TimeUnit

/**
 * @description
 * @author      李涛
 * @version
 * @Date        2018/8/23.
 */

class RequestCall {
    private var okHttpResquest :OkHttpRequest? = null
    private var  request:Request? = null
    private var call :Call? = null
    private var readTimeOut :Long = 0
    private var writeTimeOut :Long = 0
    private var connTimeOut :Long = 0
    private var clone : OkHttpClient?=  null;

    constructor(request: OkHttpRequest){
        this.okHttpResquest = request
    }
    /**
     * 设置读取超时
     * @param readTimeOut
     * @return
     */
    fun readTimeOut(readTimeOut :Long):RequestCall{
        this.readTimeOut = readTimeOut
        return this
    }
    /**
     * 设置写入超时
     * @param writeTimeOut
     * @return
     */
    fun writeTimeOut(writeTimeOut:Long):RequestCall{
        this.writeTimeOut = writeTimeOut
        return this
    }

    /**
     * 设置连接超时
     * @param connTimeOut
     * @return
     */
    fun connTimeOut(connTimeOut: Long): RequestCall {
        this.connTimeOut = connTimeOut
        return this
    }

    /**
     * 该线程并不会请求网路数据,不要使用这个方法进行数据返回操作
     * @param callback
     * @return
     */
    private fun  buildCall(callback:Callback<*>?):Call{
            request = generateRequest(callback)
        if(readTimeOut>0||writeTimeOut>0||connTimeOut>0){
            readTimeOut = if (readTimeOut>0) readTimeOut else OkHttpUtil.DEFAULT_MILLISECONDS
            writeTimeOut = if (writeTimeOut>0) writeTimeOut else OkHttpUtil.DEFAULT_MILLISECONDS
            connTimeOut = if (connTimeOut>0) connTimeOut else OkHttpUtil.DEFAULT_MILLISECONDS

            clone = OkHttpUtil.getInstance().getOkHttpClient().newBuilder()
                    .readTimeout(readTimeOut,TimeUnit.MILLISECONDS)
                    .writeTimeout(writeTimeOut,TimeUnit.MILLISECONDS)
                    .connectTimeout(connTimeOut,TimeUnit.MILLISECONDS)
                    .build()

            call = clone!!.newCall(request)
        }else{
            call = OkHttpUtil.getInstance().getOkHttpClient().newCall(request)
        }
        return call!!
    }

    fun<T> generateRequest(callback: Callback<T>?):Request{
        return okHttpResquest!!.generateRequest(callback)
    }

    fun <T>execute(callback: Callback<T>){
        buildCall(callback)
        callback.onBefore(request!!,okHttpResquest!!.getIds())
        OkHttpUtil.getInstance().execute(this,callback)
    }

    fun getCall():Call{
        return call!!
    }

    fun getRequest():Request{
        return request!!
    }
    fun getOkHttpRequest():OkHttpRequest{
        return okHttpResquest!!
    }
    fun execute(): Response? {
        buildCall(null)
        return call?.execute()
    }

    fun cancle(){
        call?.cancel()
    }


}