package com.luismiguel.mdpbancocomercio.model.impl

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.luismiguel.mdpbancocomercio.api.MDPClient
import com.luismiguel.mdpbancocomercio.api.MDPRetrofit
import com.luismiguel.mdpbancocomercio.model.bean.Users
import com.luismiguel.mdpbancocomercio.model.repository.UsuariosRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsuariosImpl(): UsuariosRepository {
    private lateinit var usuarios : MutableLiveData<Users>
    private var usuariosCall: Call<Users>? = null
    private var movilesRetrofit: MDPRetrofit ? = null

    init {
        movilesRetrofit = MDPClient?.getInstance()
        usuarios = MutableLiveData<Users>()
    }

    override fun getUsuarios(): MutableLiveData<Users>? {
        return usuarios
    }

    override fun callUsuariosAPI() {

        usuariosCall = movilesRetrofit?.CONSULTAR_USUARIOS()

        usuariosCall?.enqueue(object : Callback<Users>{
            override fun onResponse(call: Call<Users>, response: Response<Users>) {
                if(response.isSuccessful){
                    Log.i("successful", "successful")
                    val baseResponse: Users?
                    baseResponse = response.body()
                    if(baseResponse == null){
                        usuarios?.value = null
                    }else{
                        usuarios?.value = baseResponse
                    }
                }
            }

            override fun onFailure(call: Call<Users>, t: Throwable) {
                t.stackTrace
                usuarios?.value = null
            }

        })

    }
}