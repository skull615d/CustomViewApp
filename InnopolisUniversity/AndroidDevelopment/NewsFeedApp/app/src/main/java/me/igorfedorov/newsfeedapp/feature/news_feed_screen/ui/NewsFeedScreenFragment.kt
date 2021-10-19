package me.igorfedorov.newsfeedapp.feature.news_feed_screen.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import me.igorfedorov.newsfeedapp.R
import me.igorfedorov.newsfeedapp.common.setAdapterAndCleanupOnDetachFromWindow
import me.igorfedorov.newsfeedapp.databinding.FragmentNewsFeedScreenBinding
import me.igorfedorov.newsfeedapp.feature.news_feed_screen.di.MAIN_SCREEN_VIEW_MODEL
import me.igorfedorov.newsfeedapp.feature.news_feed_screen.domain.model.Article
import me.igorfedorov.newsfeedapp.feature.news_feed_screen.ui.adapter.ArticlesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class NewsFeedScreenFragment : Fragment(R.layout.fragment_news_feed_screen) {

    private val viewModel: NewsFeedScreenViewModel by viewModel(
        qualifier = named(
            MAIN_SCREEN_VIEW_MODEL
        )
    )

    private val binding: FragmentNewsFeedScreenBinding by viewBinding(createMethod = CreateMethod.INFLATE)

    private val articlesAdapter: ArticlesAdapter by lazy {
        ArticlesAdapter(::openArticle)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()

        viewModel.viewState.observe(viewLifecycleOwner, ::render)

    }

    private fun render(viewState: ViewState) {

        updateAdapterItems(viewState)

        updateProgressBar(viewState)

        updateErrorText(viewState)

    }

    private fun updateErrorText(viewState: ViewState) {
        binding.errorTextView.apply {
            text = viewState.errorMessage
            isVisible = viewState.isInErrorState
        }
    }

    private fun updateProgressBar(viewState: ViewState) {
        binding.progressBar.isVisible = viewState.isLoading
    }

    private fun updateAdapterItems(viewState: ViewState) {
        articlesAdapter.items = viewState.articleList
    }

    private fun initAdapter() {
        binding.articlesRecyclerView.apply {
            setAdapterAndCleanupOnDetachFromWindow(articlesAdapter)
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
        }
    }

    private fun openArticle(article: Article) {

    }
}