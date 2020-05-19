package arb.test.video.view

import android.content.Context
import android.net.ConnectivityManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var position = 0
    private lateinit var mediaController: MediaController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mediaController = MediaController(this)

        videoView.setOnPreparedListener {
            videoView.seekTo(position)
            if (position == 0)
                videoView.start()
            else
                videoView.pause()
        }

        videoView.setOnCompletionListener {
            Toast.makeText(this, "End", Toast.LENGTH_SHORT).show()
        }

        loadUri()

    }

    fun loadUri(){
        val conManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = conManager.activeNetworkInfo

        if (netInfo != null && netInfo.isConnected) {
            try {
                videoView.setMediaController(mediaController)
//                online
                videoView.setVideoURI(Uri.parse("http://s6.uplod.ir:182/d/2k23uy4t4hvhuf6tg2ojzl3zuwq7mcfpjj5rydcsxncgp4njb62tc2mmrjtj2iizs4qd2lyz/yastunesB-hl_p3FAng2279269988421601760.mp4"))
//                offline
//                videoView.setVideoURI(Uri.parse("android.resource://$packageName/${R.raw.video}"))
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }else{
            AlertDialog.Builder(this)
                .setTitle("No Internet")
                .setMessage("Internet access is not available")
                .setPositiveButton("ReLoad"){ _ , _ ->
                    loadUri()
                }
                .setNegativeButton("Cancel"){_ , _ ->

                }
                .create()
                .show()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt("position", videoView.currentPosition)
        videoView.pause()

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        position = savedInstanceState.getInt("position")
        videoView.seekTo(position)
    }
}
