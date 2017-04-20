package kamedon.com.imageprocessingsample.page

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kamedon.com.imageprocessingsample.R
import kamedon.com.imageprocessingsample.page.collision.circle.CollisionCircleActivity
import kamedon.com.imageprocessingsample.page.collision.square.CollisionSquareActivity
import kamedon.com.imageprocessingsample.page.edit.EditActivity
import kamedon.com.imageprocessingsample.page.frame.FrameActivity
import kamedon.com.imageprocessingsample.page.rotation.nomal.RotationActivity
import kamedon.com.imageprocessingsample.page.translation.TranslationActivity
import kotlin.reflect.KClass

class MainActivity : AppCompatActivity() {

    val recyclerView by lazy {
        findViewById(R.id.recyclerView) as RecyclerView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.adapter = PageAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)

//        findViewById(R.id.btnRotation).setOnClickListener {
//            startActivity(Intent(applicationContext, RotationActivity::class.java))
//        }
//        findViewById(R.id.btnTranslation).setOnClickListener {
//            startActivity(Intent(applicationContext, TranslationActivity::class.java))
//        }
//        findViewById(R.id.btnCollisionCircle).setOnClickListener {
//            startActivity(Intent(applicationContext, CollisionCircleActivity::class.java))
//        }
//        findViewById(R.id.btnCollisionSquare).setOnClickListener {
//            startActivity(Intent(applicationContext, CollisionSquareActivity::class.java))
//        }
//        findViewById(R.id.btnFrame).setOnClickListener {
//            startActivity(Intent(applicationContext, FrameActivity::class.java))
//        }
//        findViewById(R.id.btnEdit).setOnClickListener {
//        startActivity(Intent(applicationContext, EditActivity::class.java))
//        }
    }
}



