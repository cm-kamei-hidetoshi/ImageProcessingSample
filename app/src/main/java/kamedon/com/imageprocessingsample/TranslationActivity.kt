package kamedon.com.imageprocessingsample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.SurfaceView

class TranslationActivity : AppCompatActivity() {

    val surfaceView by lazy {
        findViewById(R.id.surfaceView) as SurfaceView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_translation)
    }
}
