package http.request

import android.util.Log
import okhttp3.MediaType
import okhttp3.Request
import okhttp3.RequestBody

/**
 * @description
 * @author      李涛
 * @version
 * @Date        2018/8/24.
 */

class PostStringRequest : OkHttpRequest {
    private var content: String = ""
    private var mediaType: MediaType? = null
    private val MEDIA_TYPE_PLAIN = MediaType.parse("text/plain;charset=utf-8")
    constructor(url: String, tag: Any?, params: MutableMap<String, String>?, headers: MutableMap<String, String>?, content: String, mediaType: MediaType?, id: Int?) : super(url, tag, params, headers, id) {
        this.content = content
        this.mediaType = mediaType

        if(mediaType == null){
            this.mediaType = MEDIA_TYPE_PLAIN
        }
    }

    override fun builderRequestBody(): RequestBody {
                return RequestBody.create(mediaType,content)
    }

    override fun builderRequest(requestBody: RequestBody): Request {
         return builder.post(requestBody).build()
    }

}