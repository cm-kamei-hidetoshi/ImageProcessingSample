package kamedon.com.imageprocessingsample.page.frame

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView
import kamedon.com.imageprocessingsample.util.useCanvas

/**
 * Created by kamei.hidetoshi on 2017/04/15.
 */
class FrameView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0) : SurfaceView(context, attrs, defStyleAttr), SurfaceHolder.Callback {

    val bitmapMatrix = Matrix()

    init {
        holder.addCallback(this)
    }

    var bitmap: Bitmap? = null

    fun setup(bitmap: Bitmap) {
        this.bitmap = bitmap
        invalidate()
    }

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
    }

    override fun surfaceCreated(holder: SurfaceHolder?) {
        useCanvas {
        }
    }

    fun drawBg(canvas: Canvas) {
        canvas.drawColor(Color.WHITE)
    }


}
