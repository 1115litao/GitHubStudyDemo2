package http.builder

/**
 * @description
 * @author      李涛
 * @version
 * @Date        2018/8/23.
 */

interface HasParamsable {

    fun params(params: HashMap<String, String>): OkHttpRequestBuilder<*>

    fun addParams(key: String, `val`: String): OkHttpRequestBuilder<*>
}