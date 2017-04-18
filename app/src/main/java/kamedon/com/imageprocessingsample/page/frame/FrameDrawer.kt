package kamedon.com.imageprocessingsample.page.frame

import android.graphics.*
import android.util.Log
import kotlin.text.Typography.degree

/**
 * Created by kamei.hidetoshi on 2017/04/17.
 */
class FrameDrawer(val frameLayer: FrameDrawLayer, val photoLayer: List<PhotoDrawLayer>) {
    var drawRate = 0f

    companion object {
        fun define(frame: Frame, init: FrameDrawerBuilder.() -> Unit) = FrameDrawerBuilder(frame).apply {
            init()
        }.build()
    }

    fun draw(canvas: Canvas) {
        photoLayer.forEach {
            it.draw(canvas)
        }
        frameLayer.draw(canvas)
    }

    fun touch(x: Float, y: Float) {
        photoLayer.forEach {
            it.touch(x, y)
        }
    }

    fun destroy() {
        frameLayer.destroy()
        photoLayer.forEach(PhotoDrawLayer::destroy)
    }

    fun setup(width: Int, height: Int) {
        frameLayer.setup(width, height)
        photoLayer.forEach { it.setup(frameLayer.drawRate, frameLayer.offsetX, frameLayer.offsetY) }
    }

}

class FrameDrawerBuilder(var frame: Frame) {

    fun build() = FrameDrawer(
            frame.createFrameLayer(),
            frame.createPhotoDrawLayer())
}

class FrameDrawLayer(rectF: DrawRectF, bitmap: Bitmap) : DrawLayer(rectF, bitmap) {
    fun setup(screenWidth: Int, screenHeight: Int) {
        drawRate = Math.min(screenWidth / width.toFloat(), screenHeight / height.toFloat())
        offsetX = (width * drawRate - screenWidth) / 2f
        offsetY = (height * drawRate - screenHeight) / 2f
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
        drawRate = rate
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

    var drawRate = 1f

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
        matrix.postScale(drawRate, drawRate)
        matrix.postTranslate(rectF.rectF.left * drawRate - offsetX, rectF.rectF.top * drawRate - offsetY)
    }

    fun draw(canvas: Canvas) {
        canvas.drawBitmap(bitmap, matrix, paint)
    }

}
