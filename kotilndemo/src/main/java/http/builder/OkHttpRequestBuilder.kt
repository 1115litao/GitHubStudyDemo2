package http.builder

import http.request.RequestCall


/**
 * @description
 * @author      李涛
 * @version
 * @Date        2018/8/23.
 */

abstract class OkHttpRequestBuilder<T : OkHttpRequestBuilder<T>> {

    protected var url :String = ""
    protected var tag: Any? = null
    protected var headers: MutableMap<String, String>? = null
    protected var params: MutableMap<String, String>? = null
    protected var id: Int = 0

    abstract  fun build(): RequestCall

    fun id(id: Int): T {
        this.id = id
        return this as T
    }

    fun url(uri :String):T{
        this.url = uri
        return this as T
    }

    fun tag(tag :Any):T{
        this.tag = tag
        return this as T
    }

    fun headers(headers:MutableMap<String,String>):T{
        this.headers = headers
        return this as T
    }

    fun  addHeader(key:String,value :String):T{
        if(this.headers == null){
            headers = LinkedHashMap<String,String>()
        }
        headers!!.put(key,value)
        return this as T
    }


}