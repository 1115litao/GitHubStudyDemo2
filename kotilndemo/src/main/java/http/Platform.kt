package http

import android.os.Build
import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * @description
 * @author      李涛
 * @version
 * @Date        2018/8/27.
 */

open class Platform {
    companion object {
        private val PLATEFORM: Platform = findPlatform()
        private fun findPlatform(): Platform {
            try {
                Class.forName("android.os.Build")
                if (Build.VERSION.SDK_INT != 0) {
                    return Andorid()
                }
            } catch (ignored: ClassNotFoundException) {
            }
            return Platform()
        }


        fun get(): Platform {
            return PLATEFORM
        }
    }

    open fun defaultCallbackExecutor(): Executor {
        return Executors.newCachedThreadPool()
    }


    fun execute(runnable: Runnable) {
        defaultCallbackExecutor().execute(runnable)
    }

    internal   class Andorid : Platform() {

        override fun defaultCallbackExecutor(): Executor {
            return MainThreadExecutor
        }

        object MainThreadExecutor : Executor {
            private val handler: Handler = Handler(Looper.getMainLooper())
            override fun execute(command: Runnable?) {
                handler.post(command)
            }
        }
    }

}
