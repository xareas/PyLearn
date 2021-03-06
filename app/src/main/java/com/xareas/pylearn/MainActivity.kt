package com.xareas.pylearn

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.xareas.pylearn.data.repository.LessonRepositoryImpl
import com.xareas.pylearn.data.repository.QuizRepositoryImpl
import com.xareas.pylearn.databinding.ActivityMainBinding
import com.xareas.pylearn.ui.AboutActivity
import com.xareas.pylearn.ui.LoginActivity
import com.xareas.pylearn.utils.getViewModel
import com.xareas.pylearn.viewmodel.MainViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by lazy { getViewModel { MainViewModel(LessonRepositoryImpl(),QuizRepositoryImpl()) } }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            if(!viewModel.existData()){
                viewModel.initData()
            }
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

       //Configuraciones destinos
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_favorites,
                R.id.navigation_dictionary
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


    }

    private fun initData() = viewModel.initData()
    private fun clearData() = viewModel.clearData()

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.top_menu_info ->
            {
                startActivity(Intent(binding.root.context, AboutActivity::class.java))
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            }
            R.id.top_menu_logout ->
            {
                startActivity(Intent(binding.root.context, LoginActivity::class.java))
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            }

            R.id.initdata ->
            {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("PyLearn Base de datos")
                builder.setMessage("Crear la Base de Datos")
                builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                    Toast.makeText(applicationContext,
                        R.string.aceptar, Toast.LENGTH_SHORT).show()
                    initData()
                }
                builder.setNegativeButton(android.R.string.no) { dialog, which ->
                    Toast.makeText(applicationContext,
                        R.string.cancelar, Toast.LENGTH_SHORT).show()
                }
                builder.show()

            }

            R.id.cleardata ->
            {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("PyLearn Base de datos")
                builder.setMessage("Limpiar la Base de Datos")
                builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                    Toast.makeText(applicationContext,
                        R.string.aceptar, Toast.LENGTH_SHORT).show()
                   clearData()
                }
                builder.setNegativeButton(android.R.string.no) { dialog, which ->
                    Toast.makeText(applicationContext,
                        R.string.cancelar, Toast.LENGTH_SHORT).show()
                }
                builder.show()
            }


        }
        return true
    }





}