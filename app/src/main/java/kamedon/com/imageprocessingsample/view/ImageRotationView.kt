package kamedon.com.imageprocessingsample.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.ViewGroup
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.graphics.PorterDuff
import android.util.Log
import android.view.MotionEvent
import kamedon.com.imageprocessingsample.util.useCanvas


/**
 * Created by kamei.hidetoshi on 2017/04/11.
 */

class ImageRotationView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0) : SurfaceView(context, attrs, defStyleAttr), SurfaceHolder.Callback {

    init {
        holder.addCallback(this)
    }

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
    }

    override fun surfaceCreated(holder: SurfaceHolder?) {
        val paint = Paint(ANTI_ALIAS_FLAG)
        paint.color = Color.BLUE
        paint.style = Paint.Style.FILL

        useCanvas {
            it.drawColor(Color.WHITE)
            it.drawCircle((-50f + width) / 2, (-50f + height) / 2, 50f, paint)
        }
    }

    fun start(event: MotionEvent) {
        val paint = Paint(ANTI_ALIAS_FLAG)
        paint.color = Color.YELLOW
        paint.style = Paint.Style.FILL
        useCanvas {
            drawBg(it)
            initDraw(it)
            it.drawCircle(event.x, event.y, 50f, paint)
        }
    }

    fun drawBg(canvas: Canvas) {
        canvas.drawColor(Color.WHITE)
    }

    fun initDraw(canvas: Canvas) {
        val paint = Paint(ANTI_ALIAS_FLAG)
        paint.color = Color.BLUE
        paint.style = Paint.Style.FILL
        canvas.drawCircle((-50f + width) / 2, (-50f + height) / 2, 50f, paint)
    }

}

