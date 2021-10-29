package me.igorfedorov.kinonline.feature.video_player_screen.ui

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

class VideoPlayerFragment : Fragment() {

    companion object {

        private const val URL_KEY = "URL_KEY"

        fun newInstance(url: String) = VideoPlayerFragment().apply {
            arguments = bundleOf(Pair(URL_KEY, url))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}