package com.example.mediaplayer

import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import java.io.IOException

class MainActivity : AppCompatActivity() {

     var myPlayer: MediaPlayer ?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mediaPlayer: MediaPlayer = MediaPlayer.create(applicationContext, R.raw.abc)

        val play: Button = findViewById(R.id.btn_play)
        val stop: Button = findViewById(R.id.btn_stop)
        val pause: Button = findViewById(R.id.btn_pause)
        val playaudio: Button = findViewById(R.id.AudioPlay)
        val pauseaudio: Button = findViewById(R.id.Audio_pause)

        play.setOnClickListener {
            mediaPlayer.start()
        }

        pause.setOnClickListener {
            mediaPlayer.pause()
        }

        stop.setOnClickListener {
            mediaPlayer.stop()

            mediaPlayer.prepareAsync()
        }


        playaudio.setOnClickListener(View.OnClickListener {
            playAudio()
        })

        pauseaudio.setOnClickListener(View.OnClickListener {
            if (myPlayer?.isPlaying == true) {
                myPlayer?.reset()

                myPlayer?.stop()
                myPlayer?.release()
                Toast.makeText(this@MainActivity, "Audio has been Paused", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(
                    this@MainActivity,
                    "Audio has not been played yet",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

    }

    private fun playAudio() {


        val uri :Uri =  Uri.parse( "https://gaana.com/song/dil-ne" )
        playContentUri(uri)
         /*myPlayer = MediaPlayer().apply {
             AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_MEDIA)
                 .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
            setDataSource(applicationContext,uri)
            prepare()
        }
        myPlayer?.start()
        Toast.makeText(this, "Audio Player is Playing...", Toast.LENGTH_SHORT).show()*/
    }

    fun playContentUri(uri: Uri) {
        var mMediaPlayer: MediaPlayer? = null
        try {
            mMediaPlayer = MediaPlayer().apply {
                setDataSource(application, uri)
                setAudioAttributes(AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build()
                )
                prepare()
                start()
            }
        } catch (exception: IOException) {
            mMediaPlayer?.release()
            mMediaPlayer = null
        }
    }
}