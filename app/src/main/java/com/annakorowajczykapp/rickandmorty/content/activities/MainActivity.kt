package com.annakorowajczykapp.rickandmorty.content.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.annakorowajczykapp.rickandmorty.R
import com.annakorowajczykapp.rickandmorty.content.base.MainActivityInteractions

class MainActivity : AppCompatActivity(), MainActivityInteractions {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       // supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
    }

    override fun getNavigationController() =
        Navigation.findNavController(this, R.id.nav_host_fragment)

}