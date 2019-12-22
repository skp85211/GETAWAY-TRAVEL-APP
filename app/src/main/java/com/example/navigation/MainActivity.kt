package com.example.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    //private lateinit var mMainNav : BottomNavigationView
    //private lateinit var mMainFrame : FrameLayout



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true);
        getSupportActionBar()?.setLogo(R.mipmap.ic_launcher);
        getSupportActionBar()?.setDisplayUseLogoEnabled(true);
        var bottomNav:BottomNavigationView = findViewById<BottomNavigationView>(R.id.main_nav)
        bottomNav.setOnNavigationItemSelectedListener(navListener)

    }

    private var navListener = BottomNavigationView.OnNavigationItemSelectedListener {  item ->
        var selectedfragment:Fragment?=AccountFragment()
        when (item.itemId){
        R.id.nav_explore -> selectedfragment = ExploreFragment()
            R.id.nav_list -> selectedfragment = ListFragment()
            R.id.nav_trip -> selectedfragment = TripFragment()
            R.id.nav_account -> selectedfragment = AccountFragment()
        }

        supportFragmentManager.beginTransaction().replace(
            R.id.main_frame,
            selectedfragment!!
        ).commit()

        true
    }
    }





//mMainFrame = findViewById(R.id.main_frame)
//mMainNav = findViewById(R.id.main_nav)

/*mMainNav.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_explore ->{
                mMainNav.setBackgroundResource(R.color.colorPrimary)
                return true
            }

            R.id.nav_list ->{
                mMainNav.setBackgroundResource(R.color.colorAccent)
                return true
            }

            R.id.nav_trip ->{
                mMainNav.setBackgroundResource(R.color.design_default_color_primary)
                return true
            }

            R.id.nav_account ->{
                mMainNav.setBackgroundResource(R.color.colorPrimaryDark)
                return true
            }

            else->return false
            else


        }
    }
})*/