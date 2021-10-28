package me.igorfedorov.kinonline.base.cicerone_navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import me.igorfedorov.kinonline.feature.movie_info_screen.ui.MovieInfoFragment
import me.igorfedorov.kinonline.feature.movies_screen.domain.model.Movie
import me.igorfedorov.kinonline.feature.movies_screen.ui.MoviesListFragment

object Screens {

    fun moviesList() = FragmentScreen() {
        MoviesListFragment.newInstance()
    }

    fun movieInfo(movie: Movie) = FragmentScreen {
        MovieInfoFragment.newInstance(movie)
    }


}