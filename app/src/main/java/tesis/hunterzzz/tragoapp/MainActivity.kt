package tesis.hunterzzz.tragoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import dagger.hilt.android.AndroidEntryPoint
import tesis.hunterzzz.tragoapp.databinding.ActivityMainBinding
import tesis.hunterzzz.tragoapp.domain.TragosDao
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    @Inject
    lateinit var tragosDao: TragosDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
//        navController = findNavController(R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController)

        Log.d("tragosDaos","onCreate: ${tragosDao.hashCode()}")
    }

//    override fun onNavigateUp(): Boolean {
//        return navController.navigateUp()
//    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}