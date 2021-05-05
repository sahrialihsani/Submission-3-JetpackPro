package com.sahrial.submission_3_jetpackpro.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sahrial.submission_3_jetpackpro.R
import com.sahrial.submission_3_jetpackpro.viewmodel.FactoryViewModel
import com.sahrial.submission_3_jetpackpro.viewmodel.MainviewModel
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    private lateinit var configureViewmodel: MainviewModel

    @Inject
    lateinit var factory: FactoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configureViewmodel()
        configureNavigation()
    }

    private fun configureViewmodel() {
        configureViewmodel = ViewModelProvider(
                this@MainActivity,
                factory
        )[MainviewModel::class.java]
    }
    private fun configureNavigation() {
        val navView: BottomNavigationView = findViewById(R.id.bottom_navbar)
        val navController = findNavController(R.id.fragment)

        val appBarConfiguration = AppBarConfiguration.Builder(
                R.id.nav_movie,
                R.id.nav_tv,
                R.id.nav_favorite
        ).build()

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.about_developer, menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menuabout->{
                val intent= Intent(this, AboutActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return false
    }
}
