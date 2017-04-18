package kamedon.com.imageprocessingsample.page.frame

import android.graphics.*
import android.os.Bundle
import android.view.MotionEvent
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kamedon.com.imageprocessingsample.R
import kamedon.com.imageprocessingsample.R.id.surfaceView
import java.lang.Exception

class FrameActivity : RxAppCompatActivity() {
    val surfaceView by lazy {
        findViewById(R.id.surfaceView) as FrameView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frame)
        Single
                .create<Bitmap> {
                    try {
                        //テスト用画像を作成
                        val bitmap = Bitmap.createBitmap(540, 960, Bitmap.Config.ARGB_8888)
                        val canvas = Canvas(bitmap)
                        canvas.drawColor(Color.GRAY)
                        //写真の位置を切り抜く
                        Bitmap.createBitmap(250, 250, Bitmap.Config.ARGB_8888).apply {
                            val paint = Paint()
                            paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
                            val matrix = Matrix()
                            matrix.postRotate(30f, width / 2f, height / 2f)
                            matrix.postTranslate(150f, 100f)
                            canvas.drawBitmap(this, matrix, paint)
                            recycle()
                        }
                        //写真の位置を切り抜く
                        Bitmap.createBitmap(250, 200, Bitmap.Config.ARGB_8888).apply {
                            val paint = Paint()
                            paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
                            val matrix = Matrix()
                            matrix.postRotate(355f, width / 2f, height / 2f)
                            matrix.postTranslate(150f, 550f)
                            canvas.drawBitmap(this, matrix, paint)
                            recycle()
                        }
                        it.onSuccess(bitmap)
                    } catch (e: Exception) {
                        it.onError(e)
                    }
                }
                .bindToLifecycle(this)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map {
                    val frame = Frame.define(it) {
                        //フレームで写真を配置する部分を記述
                        add(DrawRectF(RectF(150f, 100f, 250f + 150f, 250f + 100f), 30f))
                        add(DrawRectF(RectF(150f, 550f, 250f + 150f, 200f + 550f), 355f))
                    }

                    FrameDrawer.define(frame) {}
                }
                .subscribe { drawer -> surfaceView.setup(drawer) }

        surfaceView.setOnTouchListener { v, event ->
            event?.run {
                when (action) {
                    MotionEvent.ACTION_DOWN -> {
                        surfaceView.touch(event.x, event.y)
                        return@setOnTouchListener true
                    }
                    else -> return@setOnTouchListener false
                }
            }
            return@setOnTouchListener false
        }

        surfaceView.focusListener = { layer ->
            Single
                    .create<Bitmap> {
                        try {
                            it.onSuccess(BitmapFactory.decodeResource(resources, R.drawable.cat))
                        } catch (e: Exception) {
                            it.onError(e)
                        }
                    }
                    .bindToLifecycle(this)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        bitmap ->
                        layer.photo(bitmap)
                        surfaceView.update()

                    }
        }
    }

    override fun onDestroy() {
        surfaceView.destroy()
        super.onDestroy()
    }

}
