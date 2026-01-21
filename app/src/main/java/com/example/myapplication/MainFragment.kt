package com.example.myapplication

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.FragmentMainBinding
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var player: ExoPlayer
    private var currentIndex = 0
    private val dummySongs = listOf(
        "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3",
        "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-2.mp3",
        "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-3.mp3"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // RecyclerView setup
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = SimpleAdapter(dummySongs.mapIndexed { i, _ -> "Song ${i+1}" })

        // ExoPlayer setup
        player = ExoPlayer.Builder(requireContext()).build()
        binding.playerView.player = player
        playSong(currentIndex)

        // Buttons
        binding.playPauseButton.setOnClickListener {
            if(player.isPlaying) player.pause() else player.play()
        }
        binding.prevButton.setOnClickListener {
            currentIndex = (currentIndex - 1 + dummySongs.size) % dummySongs.size
            playSong(currentIndex)
        }
        binding.nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % dummySongs.size
            playSong(currentIndex)
        }

        binding.fab.setOnClickListener {
            if(player.isPlaying) player.pause() else player.play()
        }
    }

    private fun playSong(index: Int) {
        player.setMediaItem(MediaItem.fromUri(Uri.parse(dummySongs[index])))
        player.prepare()
        player.play()
        binding.bottomSongTitle.text = "Song ${index + 1}"
        binding.bottomSongArtist.text = "Artist ${index + 1}"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        player.release()
        _binding = null
    }

    class SimpleAdapter(private val items: List<String>) :
        RecyclerView.Adapter<SimpleAdapter.ViewHolder>() {

        class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val textView = TextView(parent.context)
            textView.textSize = 18f
            textView.setPadding(16,16,16,16)
            return ViewHolder(textView)
        }

        override fun getItemCount() = items.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.textView.text = items[position]
        }
    }
}
