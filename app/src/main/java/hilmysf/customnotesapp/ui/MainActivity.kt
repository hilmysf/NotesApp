package hilmysf.customnotesapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import hilmysf.customnotesapp.R
import hilmysf.customnotesapp.databinding.ActivityMainBinding
import hilmysf.customnotesapp.ui.add.AddActivity
import hilmysf.customnotesapp.ui.home.HomeFragment
import hilmysf.customnotesapp.ui.label.LabelFragment

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
        activityMainBinding.bottomNavigationView.menu.getItem(2).isEnabled = false
        activityMainBinding.bottomNavigationView.menu.getItem(4).isChecked = true
        setBottomNavIcon(true)

        val homeFragment = HomeFragment()
        val labelFragment = LabelFragment()
        setCurrentFragment(homeFragment)
        activityMainBinding.fab.setOnClickListener {
            val intent = Intent(applicationContext, AddActivity::class.java)
            startActivity(intent)
        }
        activityMainBinding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.btn_home -> {
                    setCurrentFragment(homeFragment)
                    setBottomNavIcon(true)
                }
                R.id.btn_label -> {
                    setCurrentFragment(labelFragment)
                    setBottomNavIcon(false)
                }
                R.id.btn_settings -> {
                    startActivity(Intent(applicationContext, SettingsActivity::class.java))
                }
            }
            true
        }
    }

    private fun setBottomNavIcon(isHome: Boolean) {
        if (isHome) {
            activityMainBinding.bottomNavigationView.menu.getItem(3).icon =
                ContextCompat.getDrawable(this, R.drawable.ic_home_fill)

            activityMainBinding.bottomNavigationView.menu.getItem(4).icon =
                ContextCompat.getDrawable(this, R.drawable.ic_label_white)
        } else {
            activityMainBinding.bottomNavigationView.menu.getItem(4).icon =
                ContextCompat.getDrawable(this, R.drawable.ic_labelled)
            activityMainBinding.bottomNavigationView.menu.getItem(3).icon =
                ContextCompat.getDrawable(this, R.drawable.ic_home)
        }

    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }
}