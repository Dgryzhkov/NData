package ru.netology.nmedia.ui

import android.os.Bundle
import android.transition.Scene
import android.transition.TransitionManager
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.R

class AppActivity : AppCompatActivity(R.layout.activity_app) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val root = findViewById<ViewGroup>(R.id.root)
        val scene = Scene.getSceneForLayout(root, R.layout.end_scene, this)
        findViewById<View>(R.id.goButton).setOnClickListener{
            TransitionManager.go(scene)
        }


    }
}