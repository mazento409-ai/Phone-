package com.glass.dialer

import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContentView(R.layout.activity_main)

        val root = findViewById<View>(R.id.root)
        ViewCompat.setOnApplyWindowInsetsListener(root) { v, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(0, bars.top, 0, bars.bottom)
            insets
        }

        val title = findViewById<TextView>(R.id.title)
        val tabs = listOf(
            findViewById<TextView>(R.id.tab_calls) to R.string.tab_calls,
            findViewById<TextView>(R.id.tab_contacts) to R.string.tab_contacts,
            findViewById<TextView>(R.id.tab_settings) to R.string.tab_settings
        )

        fun select(index: Int) {
            tabs.forEachIndexed { i, (view, _) ->
                val active = i == index
                val color = if (active) R.color.accent_blue else R.color.tab_inactive
                view.setTextColor(ContextCompat.getColor(this, color))
                view.setTypeface(null, if (active) Typeface.BOLD else Typeface.NORMAL)
            }
            title.setText(tabs[index].second)
        }

        tabs.forEachIndexed { i, (view, _) ->
            view.setOnClickListener { select(i) }
        }
        select(0)
    }
}
