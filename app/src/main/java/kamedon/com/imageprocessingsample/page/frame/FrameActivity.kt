package kamedon.com.imageprocessingsample.page.frame

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import kamedon.com.imageprocessingsample.R

class FrameActivity : AppCompatActivity() {
    val surfaceView by lazy {
        findViewById(R.id.surfaceView) as FrameView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frame)
    }
}
