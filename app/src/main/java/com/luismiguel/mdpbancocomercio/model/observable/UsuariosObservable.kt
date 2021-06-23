package com.luismiguel.mdpbancocomercio.model.observable
import androidx.databinding.BaseObservable
import androidx.lifecycle.MutableLiveData
import com.luismiguel.mdpbancocomercio.model.bean.Users
import com.luismiguel.mdpbancocomercio.model.impl.UsuariosImpl
import com.luismiguel.mdpbancocomercio.model.repository.UsuariosRepository

class UsuariosObservable: BaseObservable() {
    private var requerimientosRepository: UsuariosRepository = UsuariosImpl()
    fun callUsuarios(){
        requerimientosRepository.callUsuariosAPI()
    }

    fun getUsuarios(): MutableLiveData<Users>?{
        return requerimientosRepository.getUsuarios()
    }
}