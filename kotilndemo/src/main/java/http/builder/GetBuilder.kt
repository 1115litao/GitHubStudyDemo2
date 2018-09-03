package http.builder

import android.net.Uri
import http.request.GetRequest
import http.request.RequestCall

/**
 * @description
 * @author      李涛
 * @version
 * @Date        2018/8/23.
 */

class GetBuilder :  OkHttpRequestBuilder<GetBuilder>() ,HasParamsable{
    override fun params(params: HashMap<String, String>): OkHttpRequestBuilder<*> {
                this.params = params

        return this
    }

    override fun addParams(key: String, `val`: String): OkHttpRequestBuilder<*> {
        if (this.params == null) {
            params = LinkedHashMap<String, String>()
        }
        params!!.put(key, `val`)
        return this
    }


    override fun build(): RequestCall {
      if(params !=null){
          url = appendParams(url,params!!)
      }

        return GetRequest(url,tag!!,params!!,headers!!,id).build()
    }


    protected fun appendParams(url :String ,params:Map<String,String>):String {
        if(url ==null || params == null || params!!.isEmpty()) return url
        val builder = Uri.parse(url).buildUpon()
        val keys = params.keys
        val iterator = keys.iterator()
        while (iterator.hasNext()){
            val key = iterator.next()
            builder.appendQueryParameter(key,params[key])
        }
        return builder.build().toString()
    }
}