package http.request

import http.OkHttpUtil
import http.callback.Callback
import okhttp3.MediaType
import okhttp3.Request
import okhttp3.RequestBody
import java.io.File

/**
 * @description
 * @author      李涛
 * @version
 * @Date        2018/8/29.
 */

class PostFileRequest :OkHttpRequest {
    private var file:File? = null
    private var mediaType:MediaType? = null
    companion object {
        val MEDIA_TYPE_STREAM :MediaType = MediaType.parse("application/octet-stream")!!
    }
    override fun builderRequestBody(): RequestBody {
         return RequestBody.create(mediaType,file)
    }

    override fun builderRequest(requestBody: RequestBody): Request {
         return builder.post(requestBody).build()
    }

    constructor(url:String,tag:Any?,params:MutableMap<String,String>?,headers:MutableMap<String,String>?,file: File?,mediaType: MediaType?,id:Int?) :
            super(url,tag,params,headers,id){
        this.file = file
        this.mediaType = mediaType
        if(mediaType==null){
            this.mediaType = MEDIA_TYPE_STREAM
        }
    }

    override fun <T> wrapRequestBody(requestBody: RequestBody, callback: Callback<T>): RequestBody {
        if(callback==null){
            return requestBody
        }
        val cointingRequestBody = CountingRequestBody(requestBody,object :CountingRequestBody.Listener{
            override fun onRequestProgress(bytesWritten: Long, contentLength: Long) {
                OkHttpUtil.getExecutor().execute {
                    callback.inProgress(bytesWritten * 1.0f / contentLength,contentLength,id)
                }
            }

        })
        return cointingRequestBody

    }



}