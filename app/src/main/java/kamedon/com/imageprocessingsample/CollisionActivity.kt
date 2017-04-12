package kamedon.com.imageprocessingsample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import kamedon.com.imageprocessingsample.view.ImageCollisionView

class CollisionActivity : AppCompatActivity() {

    val surfaceView by lazy {
        findViewById(R.id.surfaceView) as ImageCollisionView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collision)
        surfaceView.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    surfaceView.collide(event.x, event.y)
                    return@setOnTouchListener true
                }
                MotionEvent.ACTION_MOVE -> {
                    surfaceView.collide(event.x, event.y)
                }
            }
            false

        }
    }
}
