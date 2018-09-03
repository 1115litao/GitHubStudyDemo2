package http.request

import okhttp3.Request
import okhttp3.RequestBody

/**
 * @description
 * @author      李涛
 * @version
 * @Date        2018/8/24.
 */

class GetRequest(url: String, tag: Any, params: MutableMap<String, String>, headers: MutableMap<String, String>, id: Int) : OkHttpRequest(url,tag,params,headers,id) {


    override fun builderRequestBody(): RequestBody {
        return null!!
    }


    override fun builderRequest(requestBody: RequestBody): Request {
       return builder.get().build()
    }
}