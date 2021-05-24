package com.udacity.shoestore

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.udacity.shoestore.databinding.ActivityMainBinding
import com.udacity.shoestore.utils.SharedPreferencesManager
import com.udacity.shoestore.viewmodels.MainVM
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private lateinit var mainVM: MainVM

    companion object{
        val LOGGEDIN = "LoggedIn"
        val EMAIL = "email"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        Timber.plant(Timber.DebugTree())


        binding.lifecycleOwner = this
        mainVM = ViewModelProvider(this).get(MainVM::class.java)


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)

       // binding.toolbar.setupWithNavController(navController, appBarConfiguration)
        setSupportActionBar(binding.toolbar)
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)

        SharedPreferencesManager.init(applicationContext);

        val loggedIn = SharedPreferencesManager.read(LOGGEDIN)
        if(loggedIn) {
            navController.navigate(R.id.action_loginFragment_to_welcomeFragment)
           // supportActionBar?.setDisplayHomeAsUpEnabled(false)
           // supportActionBar?.setDisplayShowTitleEnabled(false)
        }


    }

    override fun onStart() {
        super.onStart()
        navController.apply {
            addOnDestinationChangedListener(destinationListener)
        }


        mainVM.toolbarState.observe(this) { state ->
            if(state){
                binding.toolbar.visibility = View.VISIBLE
            } else {
                binding.toolbar.visibility = View.GONE
            }
        }

        mainVM.homeAsUpButton.observe(this) { state ->
            supportActionBar?.setDisplayHomeAsUpEnabled(state)
        }

    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.overflow_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.loginFragment){
            SharedPreferencesManager.delete(LOGGEDIN)
            SharedPreferencesManager.delete(EMAIL)
        }
        return NavigationUI.onNavDestinationSelected(
            item, navController)
                || super.onOptionsItemSelected(item)
    }

    private val destinationListener = NavController.OnDestinationChangedListener { _, destination, _ ->
        run {
            if(destination.id == R.id.loginFragment){
                mainVM.updateToolbar(false)
                mainVM.updateHomeAsUpDisplay(false)
            } else {
                mainVM.updateHomeAsUpDisplay(destination.label != "Welcome")
                mainVM.updateToolbar(true)
            }
        }
    }
}
