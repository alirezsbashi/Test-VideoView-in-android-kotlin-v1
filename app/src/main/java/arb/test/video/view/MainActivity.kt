package arb.test.video.view

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mediaController: MediaController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mediaController = MediaController(this)

        try {
            videoView.setMediaController(mediaController)
            videoView.setVideoURI(Uri.parse("android.resource://$packageName/${R.raw.video}"))
        }catch (ex :Exception){
            ex.printStackTrace()
        }

        videoView.setOnPreparedListener {
            videoView.start()
        }

    }
}
