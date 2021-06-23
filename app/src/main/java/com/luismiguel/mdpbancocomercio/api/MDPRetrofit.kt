package com.luismiguel.mdpbancocomercio.api

import com.luismiguel.mdpbancocomercio.BuildConfig
import com.luismiguel.mdpbancocomercio.model.bean.Users
import retrofit2.Call
import retrofit2.http.GET

interface MDPRetrofit {
    @GET(BuildConfig.Usuarios)
    fun CONSULTAR_USUARIOS(): Call<Users>?
}
