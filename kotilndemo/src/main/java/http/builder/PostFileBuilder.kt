package http.builder

import http.request.PostFileRequest
import http.request.RequestCall
import okhttp3.MediaType
import java.io.File

/**
 * @description
 * @author      李涛
 * @version
 * @Date        2018/8/28.
 */

class PostFileBuilder :OkHttpRequestBuilder<PostFileBuilder>() {
    private var file:File? = null
    private var mediaType:MediaType? = null

    fun file(file: File):OkHttpRequestBuilder<*>{
        this.file  = file
        return this
    }

    fun mediaType(mediaType: MediaType):OkHttpRequestBuilder<*>{
        this.mediaType = mediaType
        return this
    }

    override fun build(): RequestCall {
        return PostFileRequest(url, tag, params, headers, file, mediaType,id).build()
    }
}