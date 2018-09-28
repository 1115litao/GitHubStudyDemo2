package http.request

import http.OkHttpUtil
import http.builder.PostFormBuilder
import http.callback.Callback
import okhttp3.*
import java.io.UnsupportedEncodingException
import java.net.FileNameMap
import java.net.URLConnection
import java.net.URLEncoder


/**
 * @description
 * @author      李涛
 * @version
 * @Date        2018/8/24.
 */

class PostFormRequest :OkHttpRequest {

    override fun <T> wrapRequestBody(requestBody: RequestBody, callback: Callback<T>?): RequestBody {
        if(callback ==null) return requestBody
        val  countingRequestBody = CountingRequestBody(requestBody,object :CountingRequestBody.Listener{
            override fun onRequestProgress(bytesWritten: Long, contentLength: Long) {
                OkHttpUtil.getExecutor().execute {
                    callback.inProgress(bytesWritten * 1.0f / contentLength, contentLength, id)
                }
            }
        })

        return countingRequestBody
    }
    override fun builderRequest(requestBody: RequestBody): Request {
        return builder.post(requestBody).build()
    }

    private var files:List<PostFormBuilder.FileInput>? = null

    constructor(url: String, tag: Any?, params: MutableMap<String, String>?, headers: MutableMap<String, String>?,  files:List<PostFormBuilder.FileInput>?, id: Int?) :
            super(url,tag,params,headers,id){
        this.files =files
    }

    override fun builderRequestBody(): RequestBody {
        if(files==null){
            val builder = FormBody.Builder()
            addParams(builder)
            var formBody = builder.build()
            return formBody
        }else{
            val builder = MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
            addParams(builder)

            for (fileInput in files!!){
                val fileBody = RequestBody.create(MediaType.parse( guessMimeType(fileInput.fileName)),fileInput.file)
                builder.addFormDataPart(fileInput.key,fileInput.fileName,fileBody)
            }
            return builder.build()
        }
    }





    private fun addParams(builder: MultipartBody.Builder){
        if(params !=null){
            for(key in params!!.keys){
                builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"$key\""),
                        RequestBody.create(null, params!![key]))
            }
        }
    }

    private fun addParams(builder: FormBody.Builder){
        if(params !=null){
            for(key in params!!.keys){
                builder.add(key,params!![key])
            }
        }
    }

    private fun guessMimeType(path :String):String{
        val fileNameMap: FileNameMap = URLConnection.getFileNameMap()
        var contentTypeFor:String? = null
        try {
            contentTypeFor  = fileNameMap.getContentTypeFor(URLEncoder.encode(path,"UTF-8"))
        }catch (e : UnsupportedEncodingException){
            e.printStackTrace()
        }
        if(contentTypeFor  == null){
            contentTypeFor = "application/octet-stream"
        }
        return contentTypeFor

    }
}

