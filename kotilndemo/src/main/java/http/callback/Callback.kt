package http.callback

import okhttp3.Call
import okhttp3.Request
import okhttp3.Response

/**
 * @description
 * @author      李涛
 * @version
 * @Date        2018/8/27.
 */

abstract class Callback<T> {
    var requestSuccess: Boolean = false//网络数据返回结果是否正确是否请求成功
    /**
     * UI Thread
     * 网络请求前
     * @param request
     */
    fun onBefore(request: Request, id: Int) {}

    /**
     * UI Thread
     * 网络请求后
     * @param
     */
    fun onAfter(id: Int?) {}

    /**
     * UI Thread
     * 进度条
     * @param progress 0.0表示没有开始 1.0表示下载完成
     * @param total 总进度
     */
    fun inProgress(progress: Float, total: Long, id: Int) {}

    /**
     * 如果你在parsenetworkresponse()解析响应代码，你应该把这个方法返回true。
     * 该方法主要是判断返回的状态码是否是成功的状态码，200~300范围内都算成功
     * @param response
     * @return
     */
    fun validateResponse(response: Response, id: Int): Boolean {
        return response.isSuccessful
    }

    /**
     * Thread Pool Thread
     * 解析Jason
     * @param response
     */
    @Throws(Exception::class)
    abstract fun  parseNetworkResponse(response: Response, id: Int): T

    abstract fun onError(call: Call?, e: Exception?, id: Int?)

    abstract fun onResponse(response: T?, id: Int?)

}