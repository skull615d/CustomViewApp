package me.igorfedorov.kinonline.base.cicerone_navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import me.igorfedorov.kinonline.feature.movies_screen.ui.MoviesListFragment

object Screens {

    fun moviesList() = FragmentScreen() {
        MoviesListFragment.newInstance()
    }

}