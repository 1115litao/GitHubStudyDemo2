package http.builder

import http.request.PostStringRequest
import http.request.RequestCall
import okhttp3.MediaType

/**
 * @description
 * @author      李涛
 * @version
 * @Date        2018/8/24.
 */

class PostStringBuilder :OkHttpRequestBuilder<PostStringBuilder>() {
    private var content :String  = ""
    private  var mediaType :MediaType? =  null
    override fun build(): RequestCall {
         return PostStringRequest(url, tag!!, params!!, headers!!, content, mediaType!!,id).build()
    }
    fun content (content : String):PostStringBuilder{
        this.content = content
        return this
    }

    fun mediaType(mediaType: MediaType):PostStringBuilder{
        this.mediaType = mediaType
        return this
    }

}