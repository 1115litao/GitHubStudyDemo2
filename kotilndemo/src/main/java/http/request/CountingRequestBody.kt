package http.request

import okhttp3.MediaType
import okhttp3.RequestBody
import okio.*
import java.io.IOException

/**
 * @description
 * @author      李涛
 * @version
 * @Date        2018/8/24.
 */

class CountingRequestBody : RequestBody {
    protected var delegate: RequestBody? = null
    protected var listener: Listener? = null
    protected var countingSink: CountingSink? = null

    constructor(delegate: RequestBody, listener: Listener) {
        this.delegate = delegate
        this.listener = listener
    }

    override fun contentType(): MediaType? {
        return delegate!!.contentType()
    }

    override fun contentLength(): Long {
        try {
            return delegate!!.contentLength()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return -1
    }

    override fun writeTo(sink: BufferedSink?) {
        countingSink = CountingSink(sink!!)
        var bufferedSink: BufferedSink = Okio.buffer(countingSink)
        delegate!!.writeTo(bufferedSink)
        bufferedSink.flush()
    }


    protected inner class CountingSink : ForwardingSink {
        private var bytesWritten: Long = 0

        constructor(delegate: Sink) : super(delegate)

        override fun write(source: Buffer?, byteCount: Long) {
            super.write(source, byteCount)
            bytesWritten += byteCount
            listener!!.onRequestProgress(bytesWritten, contentLength())
        }
    }


    interface Listener {
        fun onRequestProgress(bytesWritten: Long, contentLength: Long)
    }
}