package ru.lorderi.hotels

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.lorderi.hotels.ui.main.HotelsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, HotelsFragment.newInstance())
                .commitNow()
        }
    }
}