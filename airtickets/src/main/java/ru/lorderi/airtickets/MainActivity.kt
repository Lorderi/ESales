package ru.lorderi.airtickets

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.lorderi.airtickets.ui.fragments.AirlineTicketsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, AirlineTicketsFragment.newInstance())
                .commitNow()
        }
    }
}