package kamedon.com.imageprocessingsample.page.rotation.frame

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import kamedon.com.imageprocessingsample.R
import java.util.concurrent.TimeUnit
import kotlin.text.Typography.degree

class FrameRotationActivity : RxAppCompatActivity() {

    val surfaceView by lazy {
        findViewById(R.id.surfaceView) as FrameRotationView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frame_rotation)
        Single
                .create<Float> {
                    it.onSuccess(1f)
                }.delay(100, TimeUnit.MILLISECONDS)
                .repeat()
                .bindToLifecycle(this)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext {
                    degree ->
                    surfaceView.postDegree(degree)
                }
                .subscribe {
                    degree ->
                    Log.d("single", "repeat")
                    surfaceView.update()
                }

    }
}
