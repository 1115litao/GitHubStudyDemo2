package http.log

import android.text.TextUtils
import android.util.Log
import okhttp3.*
import okio.Buffer
import java.io.IOException

/**
 * @description
 * @author      李涛
 * @version
 * @Date        2018/8/29.
 */

class LoggerInterceptor : Interceptor {
    companion object {
        val TAG: String = "OkHttpUtils"
    }

    private var tag: String = ""
    private var showResponse: Boolean = false

    constructor(tag: String, showResponse: Boolean) {
        if (TextUtils.isEmpty(tag)) {
            this.tag = TAG
        }

        this.showResponse = showResponse
        this.tag = tag
    }


    override fun intercept(chain: Interceptor.Chain?): Response {
        val request = chain!!.request()
        logForRequest(request)
        val response = chain.proceed(request)
        return logForResponse(response!!)
    }


    fun logForRequest(request: Request) {
        try {
            val url = request.url().toString()
            val headers = request.headers()
            Log.e(tag, "========request'log=======")
            Log.e(tag, "method : ${request.method()}")
            Log.e(tag, "url : $url")
            if (headers != null && headers.size() > 0) {
                Log.e(tag, "headers : ${headers.toString()}")
            }
            val requestBody = request.body()
            if (requestBody != null) {
                val mediaType = requestBody.contentType()
                if (mediaType != null) {
                    Log.e(tag, "requestBody's contentType : ${mediaType.toString()}")
                }
                if (isText(mediaType!!)) {
                    Log.e(tag, "requestBody's content : " + bodyToString(request))
                } else {
                    Log.e(tag, "requestBody's content : " + " maybe [file part] , too large too print , ignored!")
                }
            }
        } catch (e: IOException) {

        }
    }


    private fun isText(mediaType: MediaType): Boolean {
        if (mediaType.type() != null && mediaType.type() == "text") {
            return true
        }
        if (mediaType.subtype() != null) {
            if (mediaType.subtype() == "json" ||
                    mediaType.subtype() == "xml" ||
                    mediaType.subtype() == "html" ||
                    mediaType.subtype() == "webviewhtml") {
            }
            return true
        }
        return true
    }

    fun bodyToString(request: Request): String {
        try {
            val copy = request.newBuilder().build()
            val buffer: Buffer = Buffer()
            copy.body()?.writeTo(buffer)
            return buffer.readUtf8()
        } catch (e: IOException) {
            return "something error when show requestBody."
        }
    }


    fun logForResponse(response: Response): Response {
        try {
            val builder = response.newBuilder()
            val clone = builder.build()
            Log.e(tag, "url : " + clone.request().url())
            Log.e(tag, "code : " + clone.code())
            Log.e(tag, "protocol : " + clone.protocol())
            if (!TextUtils.isEmpty(clone.message())) {
                Log.e(tag, "message : " + clone.message())
            }

            if (showResponse) {
                var body = clone.body()
                if (body != null) {
                    val mediaType = body.contentType()
                    if (mediaType != null) {
                        Log.e(tag, "responseBody's contentType : " + mediaType.toString())
                        if (isText(mediaType)) {
                            val resp = body.toString()
                            Log.e(tag, "responseBody's content : $resp")
                            body = ResponseBody.create(mediaType, resp)
                            return response.newBuilder().body(body).build()
                        } else {
                            Log.e(tag, "responseBody's content : " + " maybe [file part] , too large too print , ignored!")
                        }
                    }
                }
            }
        } catch (e: IOException) {

        }
        return response
    }
}