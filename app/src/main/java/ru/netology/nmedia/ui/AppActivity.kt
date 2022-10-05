package ru.netology.nmedia.ui

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.R

class AppActivity : AppCompatActivity(R.layout.activity_app) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


       val root= findViewById<ViewGroup>(R.id.root)

        findViewById<View>(R.id.buttonGo).setOnClickListener{
            layoutInflater.inflate(R.layout.stats_view, root,true)
        }
    }
}