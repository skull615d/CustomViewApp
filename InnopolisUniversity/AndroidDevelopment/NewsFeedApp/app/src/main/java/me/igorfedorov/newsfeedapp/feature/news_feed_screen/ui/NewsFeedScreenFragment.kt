package me.igorfedorov.newsfeedapp.feature.news_feed_screen.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import me.igorfedorov.newsfeedapp.R
import me.igorfedorov.newsfeedapp.common.autoCleared
import me.igorfedorov.newsfeedapp.common.setAdapterAndCleanupOnDetachFromWindow
import me.igorfedorov.newsfeedapp.databinding.FragmentNewsFeedScreenBinding
import me.igorfedorov.newsfeedapp.feature.news_feed_screen.di.MAIN_SCREEN_VIEW_MODEL
import me.igorfedorov.newsfeedapp.feature.news_feed_screen.ui.adapter.ArticlesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class NewsFeedScreenFragment : Fragment(R.layout.fragment_news_feed_screen) {

    private val viewModel: NewsFeedScreenViewModel by viewModel(
        qualifier = named(
            MAIN_SCREEN_VIEW_MODEL
        )
    )

    private var _binding: FragmentNewsFeedScreenBinding? = null
    private val binding
        get() = _binding ?: throw IllegalStateException("Cannot access binding")

    private var articlesAdapter: ArticlesAdapter by autoCleared()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsFeedScreenBinding.inflate(inflater, container, false)
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
        articlesAdapter = ArticlesAdapter()
        binding.articlesRecyclerView.apply {
            setAdapterAndCleanupOnDetachFromWindow(articlesAdapter)
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}