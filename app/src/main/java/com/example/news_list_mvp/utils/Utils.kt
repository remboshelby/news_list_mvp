package com.example.news_list.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import javax.inject.Inject

class Utils @Inject constructor(private val context: Context){
    fun isConnectedToInternet(): Boolean{
        var connectivity = context.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        if (connectivity != null){
            val info = connectivity.allNetworkInfo
            if (info != null)
                for (i in info.indices)
                    if (info[i].state == NetworkInfo.State.CONNECTED){
                        return true
                    }
        }
        return false
    }

    companion object {
        const val PAGE_SIZE = 20;
    }
}