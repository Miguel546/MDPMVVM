package com.luismiguel.mdpbancocomercio.model.repository

import androidx.lifecycle.MutableLiveData
import com.luismiguel.mdpbancocomercio.model.bean.Users

interface UsuariosRepository {
    fun getUsuarios(): MutableLiveData<Users>?
    fun callUsuariosAPI()
}