package ru.megboyzz.app

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    val LOG_TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textView = findViewById<TextView>(R.id.textView)
        textView.text += " " + Build.VERSION.RELEASE
    }

    override fun onResume() {
        super.onResume()
        Log.i(LOG_TAG, "onResume")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i(LOG_TAG, "onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(LOG_TAG, "onDestroy")
    }

    override fun onStart() {
        super.onStart()
        Log.i(LOG_TAG, "onStart")
    }

    override fun onPause() {
        super.onPause()
        Log.i(LOG_TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i(LOG_TAG, "onStop")
    }
}


private operator fun CharSequence.plus(s: String): CharSequence? {
    return this.toString() + s
}
