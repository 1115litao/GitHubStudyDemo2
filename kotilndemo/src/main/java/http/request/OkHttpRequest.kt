package http.request

import android.util.Log
import http.callback.Callback
import okhttp3.Headers
import okhttp3.Request
import okhttp3.RequestBody

/**
 * @description
 * @author      李涛
 * @version
 * @Date        2018/8/24.
 */

abstract class OkHttpRequest {
    protected var url: String = ""
    protected var tag: Any? = null
    protected var params: MutableMap<String, String>? = null
    protected var headers: MutableMap<String, String>? = null
    protected var id: Int = 0
    protected var builder = Request.Builder();

    protected constructor(url: String, tag: Any?, params: MutableMap<String, String>?, headers: MutableMap<String, String>?, id: Int?) {
        this.url = url
        this.tag = tag
        this.params = params
        this.headers = headers
        this.id = id!!
        if (url == null) {
            Log.e("http", "url can not be null.");
        }
        initBuilder()
    }


    abstract fun builderRequestBody(): RequestBody

    protected open fun <T> wrapRequestBody(requestBody: RequestBody, callback: Callback<T>?): RequestBody {
        return requestBody
    }

    protected abstract fun builderRequest(requestBody: RequestBody): Request

    fun build(): RequestCall {
        return RequestCall(this)
    }

    fun <T> generateRequest(callback: Callback<T>?): Request {
        val requestBody = builderRequestBody()
        val wrappedRequestBody = wrapRequestBody(requestBody, callback)
        val request = builderRequest(wrappedRequestBody)
        return request
    }


    private fun initBuilder() {
        builder.url(url).tag(tag)
        appendHeaders()
        appendParams()
    }

    /**
     * 添加公共参数
     */
    private fun appendParams() {

        if (null == params) {
            params = LinkedHashMap<String, String>()
        }
        params!!.put("token", "")
        params!!.put("doctorId", "")
        params!!.put("userId", "")
    }

    /**
     * 目前这种添加Head的方式有一个bug，就是同一个key无法对应多个Value，但是OkHttp是允许这种情况的
     */
    private fun appendHeaders() {
        val headerBuilder = Headers.Builder()
        headerBuilder.add("_p", "0")//平台 0、Android 1、IOS 2、PC
        headerBuilder.add("_o", "101")//应用来源(101:医生APP,102:患者微信小程序)
        headerBuilder.add("_n", "1")//0、非原生(h5)  1、原生
//        headerBuilder.add("_v", SystemUtils.getVersionName(LWHospitalApplication.base_context))//原生客户端版本号 从医生端搬过来的
//        headerBuilder.add("_m", SystemUtils.getPhoneBrand() + " " + SystemUtils.getModel())//  手机型号
//        headerBuilder.add("_nv", CommonSPUtils.getHtml5Version())//  h5页面版本号
//        headerBuilder.add("_h", AppConfig.HEADER_H) //医院id
        if (headers != null) {
            for (key in headers!!.keys) {
                headerBuilder.add(key, headers!![key])
            }
        }
        builder.headers(headerBuilder.build())
    }

    fun getIds(): Int {
        return id
    }


}