package ru.lorderi.shorter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.lorderi.shorter.ui.main.ShorterFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ShorterFragment.newInstance())
                .commitNow()
        }
    }
}