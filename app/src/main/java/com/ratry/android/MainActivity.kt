package com.ratry.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.ratry.android.ui.base.BaseNavigation
import com.ratry.android.ui.main.MainFragment
import io.textile.textile.BaseTextileEventListener
import io.textile.textile.Textile
import java.io.File

class MainActivity : AppCompatActivity(), BaseNavigation {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
    }

    override fun onSupportNavigateUp() = navController.navigateUp()

    override fun navigateToDirection(direction: NavDirections) {
        navController.navigate(direction)
    }

    override fun navigateToDirection(direction: Int) {
        navController.navigate(direction)
    }

    override fun navigateToBack() {
        navController.popBackStack()
    }

    override fun onDestroy() {
        super.onDestroy()
        //Textile.instance().destroy()
    }
}
