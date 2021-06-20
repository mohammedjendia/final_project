package com.example.jerusalem

import android.app.Application
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class mySingleton :Application() {

    //volley
//to return only one queue
    val TAG = "jendia"
    private var mRequestQueue : RequestQueue? =null

    companion object{
        private var mInstance : mySingleton? = null

        fun getInstance() : mySingleton?{
            return mInstance
        }
    }
    override fun onCreate() {
        super.onCreate()
        mInstance =this
    }
    private fun getRequestQueue(): RequestQueue?{

        if (mRequestQueue==null){
            mRequestQueue = Volley.newRequestQueue(this)
        }
        return mRequestQueue
    }

    fun <T> addRequestQueue(r: Request<T>){
        r.tag = TAG
        getRequestQueue()!!.add(r)
    }
}