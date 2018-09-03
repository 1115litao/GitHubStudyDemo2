package http.log

import android.util.Log
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.Request
import okhttp3.Response
import okio.Buffer
import java.io.IOException
import java.nio.charset.Charset

/**
 * @description
 * @author      李涛
 * @version
 * @Date        2018/8/23.
 */

class SimpleInterceptor :Interceptor {
    override fun intercept(chain: Interceptor.Chain?): Response {
        val request = chain!!.request()//获取请求
        var requestResult = logRequest(request)
        val response = chain.proceed(request)//获取最终响应,如果链接出现重定向，则可能出现响应链接和请求链接不一致的情况
        val responseBody = response.body()
        val headers = request.headers()
        val sb = StringBuffer()
        sb.append("\"\\nmethod : \" + request.method()+\"\\n\"")
        if(headers !=null && headers.size()>0) {sb.append("headers : " + headers.toString())}
        if(isText(responseBody!!.contentType()!!)){
            var charset =  Charset.forName("UTF-8")
            val source = responseBody.source()
            source.request(Long.MAX_VALUE)
            val buffer  = source.buffer()
            val contentType = responseBody.contentType()
            charset = contentType!!.charset(Charset.defaultCharset())
            val responseResult = buffer.clone().readString(charset)
            Log.i("该次响应的链接", "" + response.request().url() + "\n=====>类型为:" + responseBody.contentType() + "\n=====>(请求的Headers)" + sb.toString() + "=====>(请求的参数)" + requestResult + "\n=====>(返回的结果)" + responseResult)
            return response.newBuilder().build()
        }else{
            Log.i("该次响应的链接",""+response.request().url()+"\n=====>(请求的Headers)"+sb.toString()+"=====>(请求的参数)"+requestResult+"=====>(返回的结果不为文本类型)类型为:"+responseBody.contentType());
        }
        return  response
    }


    private  fun isText(mediaType: MediaType) : Boolean{
        Log.i("http","文件类型${mediaType.type()}")
        if(mediaType != null && mediaType.type().equals("text")) {  return true }
        if(mediaType.subtype() !=null ){
            if (mediaType.subtype() == "json" ||
                    mediaType.subtype() == "xml" ||
                    mediaType.subtype() == "html" ||
                    mediaType.subtype() == "webviewhtml")
                return true
        }
        return  false
    }


    private fun  logRequest(request: Request) : String{
        val requestBody = request.body()
        val  resultString ="无法获取请求的内容"
        if(requestBody !=null){
            val mediaType = requestBody.contentType()
            if(mediaType!=null){
                return bodyToString(request)
            }
        }
        return  resultString

    }

    private  fun bodyToString(request: Request) : String{
        try {
            val copy = request.newBuilder().build()
            val buffer = Buffer()
            copy.body()?.writeTo(buffer)
            return buffer.readUtf8()
        }catch (e : IOException){
            return "something error when show requestBody."
        }

    }


}