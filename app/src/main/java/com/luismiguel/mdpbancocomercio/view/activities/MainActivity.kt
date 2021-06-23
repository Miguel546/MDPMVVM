package com.luismiguel.mdpbancocomercio.view.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.luismiguel.mdpbancocomercio.R
import com.luismiguel.mdpbancocomercio.calendar.CalendarUtil
import com.luismiguel.mdpbancocomercio.databinding.ActivityMainBinding
import com.luismiguel.mdpbancocomercio.model.bean.UserItem
import com.luismiguel.mdpbancocomercio.utils.Global
import com.luismiguel.mdpbancocomercio.viewmodel.MainActivityViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var mainActivityViewModel: MainActivityViewModel? = null
    private lateinit var btnConsultar: Button
    private lateinit var rvUsuarios: RecyclerView
    //Progress
    private lateinit var rlProgress: RelativeLayout
    private lateinit var ivCloud: ImageView
    private lateinit var btnReload: Button
    private lateinit var tvMessage: TextView
    private lateinit var progressBar: ProgressBar
    //Progress
    private lateinit var textView: TextView
    private lateinit var tvlastupdate: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        rlProgress = binding.rlProgress
        ivCloud = binding.ivCloud
        btnReload = binding.btnReload
        tvMessage = binding.tvMessage
        progressBar = binding.progressBar
        tvlastupdate = binding.tvLastUpdate
        btnConsultar = binding.btnTraerLista
        rvUsuarios = binding.rvUsuarios
        textView = binding.textView

        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        binding.model = mainActivityViewModel

        btnConsultar.setOnClickListener{
            lifecycleScope.launch {
                if(Global().isNetworkConnect(applicationContext)) {
                    llamarUsuarios()
                }else{
                    showError()
                }
            }
        }

        setUpListClick()
    }

    private fun setUpListClick(){
        mainActivityViewModel?.getUsuariosSelected()?.observe(this, { usuario : UserItem ->
            val intent = Intent(this, DetailActivity::class.java)
            val usuarioJson = Gson().toJson(usuario)
            intent.putExtra("usuario", usuarioJson)
            startActivity(intent)
        })
    }

    private suspend fun llamarUsuarios(){

        btnConsultar.isEnabled = false
        showProgress()
        mainActivityViewModel?.callUsuarios()
        delay(1000)
        mainActivityViewModel?.getUsuarios().let {
            Log.i("let", "let")
            if(it?.value == null){
                Log.i("let", "null")
                hideProgress(1)
            }else{
                Log.i("let", "no es null")
                mainActivityViewModel?.setUsuariosInRecyclerAdapter(it.value)
                hideProgress(2)
            }

        }
        btnConsultar.isEnabled = true
    }

    private fun showProgress() {
        rlProgress.visibility = VISIBLE
        progressBar.visibility = VISIBLE
        tvMessage.visibility = GONE
        btnReload.visibility = GONE
        tvMessage.visibility = GONE
        ivCloud.visibility = GONE
        //
        textView.visibility = GONE
        rvUsuarios.visibility = GONE
        tvlastupdate.visibility = GONE

    }

    private fun hideProgress(numero: Int) {
        rlProgress.visibility = GONE
        if(numero == 1){
            rvUsuarios.visibility = GONE
            tvlastupdate.visibility = GONE
            textView.visibility = VISIBLE
        }else if(numero == 2){
            textView.visibility = VISIBLE
            tvlastupdate.visibility = VISIBLE
            rvUsuarios.visibility = VISIBLE
            textView.visibility = GONE
            tvlastupdate.text =
                java.lang.String.format(
                    "Última actualización: %s",
                    CalendarUtil().getDateTimeNow()
                )
        }

    }

    private fun showError(){
        rvUsuarios.visibility = GONE
        tvlastupdate.visibility = GONE
        textView.visibility = VISIBLE
        textView.setText(R.string.error_network_connect)
    }

    override fun onBackPressed() {
        val a = Intent(Intent.ACTION_MAIN)
        a.addCategory(Intent.CATEGORY_HOME)
        a.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(a)
    }
}