package me.igorfedorov.newsfeedapp.feature.bookmarks_screen.di

import me.igorfedorov.newsfeedapp.feature.bookmarks_screen.data.local.BookmarksLocalSource
import me.igorfedorov.newsfeedapp.feature.bookmarks_screen.data.local.BookmarksRepository
import me.igorfedorov.newsfeedapp.feature.bookmarks_screen.data.local.BookmarksRepositoryImpl
import me.igorfedorov.newsfeedapp.feature.bookmarks_screen.data.local.dao.BookmarkDao
import me.igorfedorov.newsfeedapp.feature.bookmarks_screen.domain.BookmarksInteractor
import me.igorfedorov.newsfeedapp.feature.bookmarks_screen.ui.BookmarksScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val bookmarksModule = module {

    single<BookmarksLocalSource> {
        BookmarksLocalSource(bookmarkDao = get<BookmarkDao>())
    }

    single<BookmarksRepository> {
        BookmarksRepositoryImpl(source = get<BookmarksLocalSource>())
    }

    single<BookmarksInteractor> {
        BookmarksInteractor(bookmarksRepository = get<BookmarksRepository>())
    }

    viewModel<BookmarksScreenViewModel> {
        BookmarksScreenViewModel(get<BookmarksInteractor>())
    }

}