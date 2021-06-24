package com.luismiguel.mdpbancocomercio.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.luismiguel.mdpbancocomercio.viewmodel.MainActivityViewModel
import com.luismiguel.mdpbancocomercio.BR
import com.luismiguel.mdpbancocomercio.databinding.ItemUsuarioBinding
import com.luismiguel.mdpbancocomercio.model.bean.UserItem

class UsuariosAdapter(var usuariosViewModel: MainActivityViewModel, var resource: Int): RecyclerView.Adapter<UsuariosAdapter.UsuariosHolder>() {
    var usuarios : List<UserItem>? = null

    fun setUsuariosList(solicitantes: List<UserItem>){
        this.usuarios = solicitantes
    }
    class UsuariosHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        private var binding: ViewDataBinding? = null
        init {
            this.binding = binding
        }

        fun setUsuario(usuariosViewModel: MainActivityViewModel, position: Int){
            binding?.setVariable(BR.model, usuariosViewModel)
            binding?.setVariable(BR.position, position)
            binding?.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuariosHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemUsuarioBinding = DataBindingUtil.inflate(layoutInflater, viewType, parent, false)
        return UsuariosHolder(binding)
    }

    override fun onBindViewHolder(holder: UsuariosHolder, position: Int) {
        holder.setUsuario(usuariosViewModel, position)
    }

    override fun getItemCount(): Int {
        return usuarios?.size ?: 0
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutIdForPosition(position)
    }

    fun getLayoutIdForPosition(position: Int): Int{
        return resource
    }
}