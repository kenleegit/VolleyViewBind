package net.agnusvox.volleyviewbind
import android.app.Application
import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class AppController : Application() {

    private var mRequestQueue: RequestQueue? = null
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    private val requestQueue: RequestQueue
        get() {
            if (mRequestQueue == null) {
                mRequestQueue = Volley.newRequestQueue(applicationContext)
            }
            return this.mRequestQueue!!
        }

    fun  addToRequestQueue(req: Request<Any>, tag: String) {
        requestQueue.add(req)
    }

    fun  addToRequestQueue(req: Request<Any>) {
        requestQueue.add(req)
    }

    fun cancelPendingRequests(tag: Any) {
        if (mRequestQueue != null) {
            mRequestQueue!!.cancelAll(tag)
        }
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
    }

    companion object {
        var instance: AppController? = null
            private set
    }
}