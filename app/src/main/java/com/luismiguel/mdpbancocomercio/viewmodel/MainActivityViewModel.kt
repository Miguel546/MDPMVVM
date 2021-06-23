package com.luismiguel.mdpbancocomercio.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.luismiguel.mdpbancocomercio.R
import com.luismiguel.mdpbancocomercio.model.bean.UserItem
import com.luismiguel.mdpbancocomercio.model.bean.Users
import com.luismiguel.mdpbancocomercio.model.observable.UsuariosObservable
import com.luismiguel.mdpbancocomercio.view.adapter.UsuariosAdapter

class MainActivityViewModel: ViewModel() {
    private var usuariosObserable: UsuariosObservable = UsuariosObservable()
    private var recyclerUsuariosAdapter: UsuariosAdapter? = null
    var selected: MutableLiveData<UserItem> = MutableLiveData<UserItem>()
    fun callUsuarios(){
        usuariosObserable.callUsuarios()

    }

    fun getUsuarios(): MutableLiveData<Users>? {
        return usuariosObserable.getUsuarios()
    }

    fun setUsuariosInRecyclerAdapter(solicitantes: List<UserItem>?){
        if (solicitantes != null) {
            recyclerUsuariosAdapter?.setUsuariosList(solicitantes)
        }
        recyclerUsuariosAdapter?.notifyDataSetChanged()
    }

    fun getRecyclerUsuariosAdapter(): UsuariosAdapter?{
        recyclerUsuariosAdapter = UsuariosAdapter(this, R.layout.item_usuario)
        return recyclerUsuariosAdapter
    }

    fun getUsuariosAt(position: Int): UserItem?{
        var solicitantes: List<UserItem>? = usuariosObserable.getUsuarios()?.value
        return solicitantes?.get(position)
    }

    fun getUsuariosSelected(): MutableLiveData<UserItem>{
        return selected
    }
    fun onItemClick(index: Int){
        val solicitante = getUsuariosAt(index)
        selected.value = solicitante
    }
}