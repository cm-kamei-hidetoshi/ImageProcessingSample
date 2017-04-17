package kamedon.com.imageprocessingsample.page.frame

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.RectF
import android.os.Bundle
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import kamedon.com.imageprocessingsample.R
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
                        canvas.drawColor(Color.BLACK)
                        it.onSuccess(bitmap)
                    } catch (e: Exception) {
                        it.onError(e)
                    }
                }
                .bindToLifecycle(this)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map {
                    //フレームデータを作成
                    FrameDrawer(Frame.define(it) {
                        add(FrameRectF(RectF(50f, 50f, 100f, 100f), 60f))
                    })
                }
                .subscribe { drawer -> surfaceView.setup(drawer) }
    }
}
