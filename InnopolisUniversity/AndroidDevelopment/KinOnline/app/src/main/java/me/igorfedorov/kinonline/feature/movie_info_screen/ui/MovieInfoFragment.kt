package me.igorfedorov.kinonline.feature.movie_info_screen.ui

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import me.igorfedorov.kinonline.R
import me.igorfedorov.kinonline.feature.movies_screen.domain.model.Movie

class MovieInfoFragment : Fragment(R.layout.fragment_movie_info) {

    companion object {
        private const val MOVIE_KEY = "MOVIE_KEY"
        fun newInstance(movie: Movie) = MovieInfoFragment().apply {
            bundleOf(Pair(MOVIE_KEY, movie))
        }
    }


}