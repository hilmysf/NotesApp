package hilmysf.customnotesapp.ui.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import hilmysf.customnotesapp.R
import hilmysf.customnotesapp.databinding.ActivityMainBinding
import hilmysf.customnotesapp.ui.ui.add.AddActivity
import hilmysf.customnotesapp.ui.ui.home.HomeFragment
import hilmysf.customnotesapp.ui.ui.label.LabelFragment

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        supportActionBar?.hide()
        activityMainBinding.bottomNavigationView.background = null
        activityMainBinding.bottomNavigationView.menu.getItem(1).isEnabled = false

        val homeFragment = HomeFragment()
        val labelFragment = LabelFragment()
        setCurrentFragment(homeFragment)
        activityMainBinding.fab.setOnClickListener {
            val intent = Intent(applicationContext, AddActivity::class.java)
            startActivity(intent)
        }
        activityMainBinding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.btn_home -> setCurrentFragment(homeFragment)
                R.id.btn_label -> setCurrentFragment(labelFragment)
            }
            true
        }

    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }
}