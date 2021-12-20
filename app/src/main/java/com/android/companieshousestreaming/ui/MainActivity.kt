package com.android.companieshousestreaming.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.companieshousestreaming.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val streamFragment = StreamFragment()
        fragmentTransaction.add(R.id.fragment_container, streamFragment)
        fragmentTransaction.commit()
    }
}