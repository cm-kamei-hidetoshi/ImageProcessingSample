package kamedon.com.imageprocessingsample.page

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kamedon.com.imageprocessingsample.R
import kamedon.com.imageprocessingsample.page.collision.CollisionActivity
import kamedon.com.imageprocessingsample.page.rotation.RotationActivity
import kamedon.com.imageprocessingsample.page.translation.TranslationActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById(R.id.btnRotation).setOnClickListener {
            startActivity(Intent(applicationContext, RotationActivity::class.java))
        }
        findViewById(R.id.btnTranslation).setOnClickListener {
            startActivity(Intent(applicationContext, TranslationActivity::class.java))
        }
        findViewById(R.id.btnCollision).setOnClickListener {
            startActivity(Intent(applicationContext, CollisionActivity::class.java))
        }
    }
}
