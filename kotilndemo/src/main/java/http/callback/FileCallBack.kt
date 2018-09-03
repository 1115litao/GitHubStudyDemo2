package http.callback

import okhttp3.Call
import okhttp3.Response
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream


/**
 * @description
 * @author      李涛
 * @version
 * @Date        2018/8/29.
 */

abstract class FileCallBack : Callback<File> {
    /**
     * 目标文件存储的文件夹路径
     */
    private var destFileDir: String = ""
    /**
     * 目标文件存储的文件名
     */
    private var destFileName: String = ""

    constructor(destFileDir: String, destFileName: String) {
        this.destFileDir = destFileDir
        this.destFileName = destFileName
    }

    override fun parseNetworkResponse(response: Response, id: Int): File {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun saveFile(response: Response, id: Int): File {
        var iss: InputStream? = null
        var buf: ByteArray = ByteArray(2048)
        var len: Int = 0
        var fos: FileOutputStream? = null

        try {
            iss = response.body()!!.byteStream()
            val total: Long = response.body()!!.contentLength()
            var sum: Long = 0
            var dir: File = File(destFileDir)
            if (!dir.exists()) {
                dir.mkdirs()
            }
            val file: File = File(dir, destFileName)
            fos = FileOutputStream(file)
            while ((iss.read(buf).also { len = it }) != -1) {
                sum += len.toLong()
                fos.write(buf, 0, len)
                val finalSum = sum
                inProgress(finalSum * 1.0f / total, total, id)
            }
            fos.flush()
            return file
        } finally {
            try {
                response.body()?.close()
                iss?.close()
            }catch (e: IOException) {

            }
            try {
                 fos?.close()
            }catch (e: IOException) {

            }
        }

    }

}