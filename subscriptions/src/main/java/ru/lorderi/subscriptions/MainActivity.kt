package ru.lorderi.subscriptions

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.lorderi.subscriptions.ui.main.SubscriptionsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SubscriptionsFragment.newInstance())
                .commitNow()
        }
    }
}