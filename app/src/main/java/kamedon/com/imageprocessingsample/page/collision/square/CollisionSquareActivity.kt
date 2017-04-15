package kamedon.com.imageprocessingsample.page.collision.square

import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import kamedon.com.imageprocessingsample.R
import kamedon.com.imageprocessingsample.page.collision.ImageCollisionSquareView

class CollisionSquareActivity : AppCompatActivity() {
    val surfaceView by lazy {
        findViewById(R.id.surfaceView) as ImageCollisionSquareView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collision_square)
        surfaceView.setup(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher))
    }
}
