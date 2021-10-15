package me.igorfedorov.newsfeedapp.feature.main_screen.di

import me.igorfedorov.newsfeedapp.feature.main_screen.MainScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val MAIN_SCREEN_VIEW_MODEL = "MAIN_SCREEN_VIEW_MODEL"
val mainScreenModule = module {

    viewModel(named(MAIN_SCREEN_VIEW_MODEL)) {
        MainScreenViewModel()
    }

}