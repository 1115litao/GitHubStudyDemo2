package http.callback

import okhttp3.Call
import okhttp3.Response

/**
 * @description
 * @author      李涛
 * @version
 * @Date        2018/8/30.
 */

class DefaultCallback :Callback<String>() {
    override fun parseNetworkResponse(response: Response, id: Int): String {
        return ""
    }

    override fun onError(call: Call?, e: Exception?, id: Int?) {

    }

    override fun onResponse(response: String?, id: Int?) {

    }



}