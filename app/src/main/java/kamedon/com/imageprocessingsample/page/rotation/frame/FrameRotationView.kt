package kamedon.com.imageprocessingsample.page.rotation.frame

import android.content.Context
import android.graphics.*
import android.provider.SyncStateContract.Helpers.update
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView
import kamedon.com.imageprocessingsample.page.frame.PhotoDrawLayer
import kamedon.com.imageprocessingsample.util.useCanvas

/**
 * Created by kamei.hidetoshi on 2017/04/20.
 */
class FrameRotationView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0) : SurfaceView(context, attrs, defStyleAttr), SurfaceHolder.Callback {

    val baseBitmap by lazy {
        Bitmap.createBitmap(width / 4, height / 4, Bitmap.Config.ARGB_8888).apply {
            Canvas(this).run {
                drawColor(Color.BLACK)
            }
        }
    }

    val rotationBitmap by lazy {
        Bitmap.createBitmap(width / 4, height / 4, Bitmap.Config.ARGB_8888).apply {
            Canvas(this).run {
                drawColor(Color.YELLOW)
            }
        }
    }
    val baseMatrix: Matrix = Matrix()

    val rotationMatrix: Matrix = Matrix()

    var degree = 0f

    val centerY by lazy {
        height / 2f
    }
    val centerX by lazy {
        width / 2f
    }

    val paint by lazy {
        Paint().apply {
            alpha = 128
        }
    }

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
    }

    override fun surfaceCreated(holder: SurfaceHolder?) {
        useCanvas {
            baseMatrix.reset()
            rotationMatrix.reset()
            baseMatrix.postTranslate((width - baseBitmap.width) / 2f, (height - baseBitmap.height) / 2f)
            rotationMatrix.postTranslate((width - baseBitmap.width) / 2f, (height - baseBitmap.height) / 2f)
            update(this)
        }
    }

    fun update(canvas: Canvas) {
        canvas.drawColor(Color.GRAY)
        canvas.drawBitmap(baseBitmap, baseMatrix, Paint())
        canvas.drawBitmap(rotationBitmap, rotationMatrix, paint)
    }


    init {
        holder.addCallback(this)
    }

    fun postDegree(degree: Float) {
        this.degree = (this.degree + degree) % 360f
        rotationMatrix.reset()
        rotationMatrix.postRotate(this.degree, rotationBitmap.width / 2f, rotationBitmap.height / 2f)
        rotationMatrix.postTranslate((width - rotationBitmap.width) / 2f, (height - rotationBitmap.height) / 2f)
        useCanvas {
            update(this)
        }
    }
}
