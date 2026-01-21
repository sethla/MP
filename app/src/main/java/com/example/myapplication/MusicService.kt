package com.example.mymusicapp

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem

class MusicService : Service() {

    private lateinit var player: ExoPlayer

    override fun onCreate() {
        super.onCreate()
        player = ExoPlayer.Builder(this).build()
        player.playWhenReady = true
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.getStringExtra("uri")?.let {
            val mediaItem = MediaItem.fromUri(it)
            player.setMediaItem(mediaItem)
            player.prepare()
            player.play()
        }
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
