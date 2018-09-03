package http.builder

import http.request.PostFormRequest
import http.request.RequestCall
import java.io.File

/**
 * @description 通过Post提交表单的辅助配置类
 * @author      李涛
 * @version
 * @Date        2018/8/24.
 */

class PostFormBuilder : OkHttpRequestBuilder<PostFormBuilder>(), HasParamsable {
    override fun params(params: HashMap<String, String>): OkHttpRequestBuilder<*> {
        this.params = params
        return this
    }

    override fun addParams(key: String, `val`: String): OkHttpRequestBuilder<*> {
        if (this.params == null) {
            params = LinkedHashMap<String, String>()
        }
        params!![key] = `val`
        return this
    }

    private var files: ArrayList<FileInput>? = ArrayList()


    override fun build(): RequestCall {
        return PostFormRequest(url, tag, params, headers, files, id).build()
    }

    fun files(key: String, files: MutableMap<String, File>): PostFormBuilder {
        for (fileName in files.keys) {
            this.files!!.add(FileInput(key, fileName, files[fileName]!!))
        }
        return this
    }

    fun addFile(name: String, fileName: String, file: File): PostFormBuilder {
        this.files!!.add(FileInput(name, fileName, file))
        return this
    }

    class FileInput {
        var key: String = ""
        var fileName: String = ""
        var file: File? = null

        constructor(name: String, fileName: String, file: File) {
            this.key = name
            this.file = file
            this.fileName = fileName
        }

        override fun toString(): String {
            return "FileInput(key='$key', fileName='$fileName', file=$file)"
        }
    }

}