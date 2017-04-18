package kamedon.com.imageprocessingsample.page.frame

import android.graphics.*

/**
 * Created by kamei.hidetoshi on 2017/04/18.
 */
class FrameDrawLayer(rectF: DrawRectF, bitmap: Bitmap) : DrawLayer(rectF, bitmap) {
    fun setup(screenWidth: Int, screenHeight: Int) {
        scale = Math.min(screenWidth / width.toFloat(), screenHeight / height.toFloat())
        offsetX = (width * scale - screenWidth) / 2f
        offsetY = (height * scale - screenHeight) / 2f
        update()
    }
}

class PhotoDrawLayer(rectF: DrawRectF, bitmap: Bitmap) : DrawLayer(rectF, bitmap) {
    init {
        canvas.drawColor(Color.BLUE)
    }

    fun touch(x: Float, y: Float) {

    }

    fun setup(rate: Float, offsetX: Float, offsetY: Float) {
        scale = rate
        this.offsetX = offsetX
        this.offsetY = offsetY
        update()
    }

}


open class DrawLayer(val rectF: DrawRectF, val bitmap: Bitmap) {
    var paint = Paint()

    var matrix = Matrix()

    val canvas = Canvas(bitmap)

    var offsetX = 0f
    var offsetY = 0f

    var scale = 1f

    val centerX by lazy {
        width / 2f
    }

    val centerY by lazy {
        height / 2f
    }

    val width by lazy {
        bitmap.width
    }

    val height by lazy {
        bitmap.height
    }

    fun destroy() {
        bitmap.recycle()
    }


    fun clear() {
        val canvas = Canvas(bitmap)
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR)
    }

    open fun update() {
        matrix.reset()
        matrix.postRotate(rectF.degree, centerX, centerY)
        matrix.postScale(scale, scale)
        matrix.postTranslate(rectF.rectF.left * scale - offsetX, rectF.rectF.top * scale - offsetY)
    }

    fun draw(canvas: Canvas) {
        canvas.drawBitmap(bitmap, matrix, paint)
    }

}
