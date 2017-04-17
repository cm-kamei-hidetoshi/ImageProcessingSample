package kamedon.com.imageprocessingsample.page.frame

import android.graphics.Bitmap
import android.graphics.RectF

/**
 * Created by kamei.hidetoshi on 2017/04/16.
 */

class Frame(val bitmap: Bitmap, val rectFs: List<FrameRectF>) {

    companion object {
        fun define(bitmap: Bitmap, init: (FrameDrawerBuilder.() -> Unit)): Frame {
            val builder = FrameDrawerBuilder(bitmap)
            builder.init()
            return builder.build()
        }
    }

}

class FrameDrawerBuilder(val bitmap: Bitmap) {
    private val rectFs = mutableListOf<FrameRectF>()

    fun add(rect: FrameRectF) {
        rectFs.add(rect)
    }

    fun build(): Frame {
        return Frame(bitmap, rectFs)
    }
}

data class FrameRectF(val rectF: RectF, val degree: Float)
