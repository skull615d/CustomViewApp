package me.igorfedorov.newsfeedapp.feature.bookmarks_screen.ui

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import me.igorfedorov.newsfeedapp.R
import me.igorfedorov.newsfeedapp.base.utils.setAdapterAndCleanupOnDetachFromWindow
import me.igorfedorov.newsfeedapp.base.utils.setData
import me.igorfedorov.newsfeedapp.databinding.FragmentBookmarksScreenBinding
import me.igorfedorov.newsfeedapp.feature.news_feed_screen.domain.model.Article
import me.igorfedorov.newsfeedapp.feature.news_feed_screen.ui.adapter.ArticlesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class BookmarksScreenFragment : Fragment(R.layout.fragment_bookmarks_screen) {

    private val viewModel: BookmarksScreenViewModel by viewModel()

    private val binding: FragmentBookmarksScreenBinding by viewBinding(
        FragmentBookmarksScreenBinding::bind
    )

    private val articlesAdapter: ArticlesAdapter by lazy {
        ArticlesAdapter(
            onItemClickListener = ::showCustomTab,
            onBookmarkClick = viewModel::deleteFromBookmarks
        )
    }

    private fun showCustomTab(it: Article) {
        CustomTabsIntent.Builder()
            .build().apply {
                launchUrl(requireContext(), Uri.parse(it.url))
            }
    }

    override fun onResume() {
        super.onResume()
        viewModel.updateUi()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()

        viewModel.viewState.observe(viewLifecycleOwner, ::render)
    }

    private fun render(viewState: ViewState) {
        articlesAdapter.setData(viewState.articles)
    }

    private fun initAdapter() {
        binding.rvBookmarks.apply {
            setAdapterAndCleanupOnDetachFromWindow(articlesAdapter)
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
        }
        articlesAdapter.items = viewModel.viewState.value?.articles
    }
}