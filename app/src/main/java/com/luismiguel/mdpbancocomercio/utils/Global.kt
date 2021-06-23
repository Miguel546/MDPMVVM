package com.luismiguel.mdpbancocomercio.utils

import android.content.Context
import android.net.ConnectivityManager


class Global {

    fun isNetworkConnect(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isAvailable && networkInfo.isConnected
        }
        return false
    }
}