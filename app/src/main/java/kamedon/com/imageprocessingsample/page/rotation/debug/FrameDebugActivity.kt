package kamedon.com.imageprocessingsample.page.rotation.debug

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import kamedon.com.imageprocessingsample.R

class FrameDebugActivity : AppCompatActivity() {

    val surfaceView by lazy {
        findViewById(R.id.surfaceView) as FrameDebugView
    }

    val seekBar by lazy {
        findViewById(R.id.seekBar) as SeekBar
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frame_debug)

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                seekBar?.let {
                    surfaceView.postRotate(360 * it.progress / it.max.toFloat())
                }
            }

        })
    }
}
