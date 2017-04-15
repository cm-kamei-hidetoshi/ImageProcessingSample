package kamedon.com.imageprocessingsample.page.edit

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

import kamedon.com.imageprocessingsample.R

class EditActivity : AppCompatActivity() {
    val image by lazy {
        findViewById(R.id.image) as ImageView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        var bitmap = BitmapFactory.decodeResource(resources, R.drawable.cat)
        image.setImageBitmap(bitmap)
    }

}
