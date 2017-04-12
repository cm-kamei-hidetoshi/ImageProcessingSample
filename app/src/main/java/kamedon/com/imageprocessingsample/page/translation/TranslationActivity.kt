package kamedon.com.imageprocessingsample.page.translation

import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.graphics.BitmapCompat
import android.view.MotionEvent
import android.view.SurfaceView
import kamedon.com.imageprocessingsample.R
import kamedon.com.imageprocessingsample.page.translation.ImageTranslationView

class TranslationActivity : AppCompatActivity() {

    val surfaceView by lazy {
        findViewById(R.id.surfaceView) as ImageTranslationView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_translation)
        surfaceView.setup(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher))
        surfaceView.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    surfaceView.translate(event.x, event.y)
                    return@setOnTouchListener true
                }
                MotionEvent.ACTION_MOVE -> {
                    surfaceView.translate(event.x, event.y)
                }
            }
            false

        }
    }
}
