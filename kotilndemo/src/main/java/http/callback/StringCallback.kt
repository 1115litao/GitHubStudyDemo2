package http.callback

import okhttp3.Response

/**
 * @description
 * @author      李涛
 * @version
 * @Date        2018/8/29.
 */

abstract class StringCallback  :Callback<String>(){

    override fun parseNetworkResponse(response: Response, id: Int): String {

        return response.body()?.string()?:""
    }

}