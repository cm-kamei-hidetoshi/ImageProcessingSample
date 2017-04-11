package kamedon.com.imageprocessingsample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import kamedon.com.imageprocessingsample.view.ImageRotationView

class RotationActivity : AppCompatActivity() {

    val surfaceView by lazy {
        findViewById(R.id.surfaceView) as ImageRotationView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rotation)
        surfaceView.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    surfaceView.start(event)
                    true
                }
                MotionEvent.ACTION_MOVE -> false
                MotionEvent.ACTION_UP -> false
            }
            false

        }
    }
}
