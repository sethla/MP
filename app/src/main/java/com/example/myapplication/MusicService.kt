package com.example.myapplication

import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.common.MediaItem


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
