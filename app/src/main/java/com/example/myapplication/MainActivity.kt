package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.common.MediaItem
import androidx.media3.ui.PlayerView


class MainActivity : AppCompatActivity() {

    // UI
    private lateinit var recyclerView: RecyclerView
    private lateinit var prevButton: Button
    private lateinit var playPauseButton: Button
    private lateinit var nextButton: Button
    private lateinit var bottomSongTitle: TextView
    private lateinit var bottomSongArtist: TextView
    private lateinit var fab: FloatingActionButton
    private lateinit var playerView: PlayerView

    // Music player
    private lateinit var player: ExoPlayer
    private val songs = listOf(
        "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3",
        "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-2.mp3",
        "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-3.mp3"
    )
    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Bind UI
        recyclerView = findViewById(R.id.recyclerView)
        prevButton = findViewById(R.id.prevButton)
        playPauseButton = findViewById(R.id.playPauseButton)
        nextButton = findViewById(R.id.nextButton)
        bottomSongTitle = findViewById(R.id.bottomSongTitle)
        bottomSongArtist = findViewById(R.id.bottomSongArtist)
        fab = findViewById(R.id.fab)
        playerView = findViewById(R.id.playerView)

        // Setup RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = SimpleAdapter(generateDummySongs())

        // Setup ExoPlayer
        player = ExoPlayer.Builder(this).build()
        playerView.player = player
        playSong(currentIndex)

        // Button logic
        playPauseButton.setOnClickListener {
            if (player.isPlaying) {
                player.pause()
            } else {
                player.play()
            }
        }

        nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % songs.size
            playSong(currentIndex)
        }

        prevButton.setOnClickListener {
            currentIndex = if (currentIndex - 1 < 0) songs.size - 1 else currentIndex - 1
            playSong(currentIndex)
        }

        // FAB opens music folder
        fab.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            val musicUri = Uri.parse("content://media/external/audio/media")
            intent.setDataAndType(musicUri, "audio/*")
            startActivity(intent)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun playSong(index: Int) {
        val uri = songs[index]
        player.setMediaItem(MediaItem.fromUri(uri))
        player.prepare()
        player.play()

        // Update bottom bar text
        bottomSongTitle.text = "Song ${index + 1}"
        bottomSongArtist.text = "Artist ${index + 1}"
    }

    private fun generateDummySongs(): List<String> {
        return List(20) { "Song ${it + 1}" }
    }

    // RecyclerView Adapter
    class SimpleAdapter(private val items: List<String>) :
        RecyclerView.Adapter<SimpleAdapter.ViewHolder>() {

        class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val textView = TextView(parent.context)
            textView.textSize = 18f
            textView.setPadding(16, 16, 16, 16)
            return ViewHolder(textView)
        }

        override fun getItemCount() = items.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.textView.text = items[position]
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()  // important to free resources
    }
}
