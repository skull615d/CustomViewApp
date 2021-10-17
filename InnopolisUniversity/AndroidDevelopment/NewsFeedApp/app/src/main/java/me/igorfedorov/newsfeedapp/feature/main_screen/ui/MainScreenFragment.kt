package me.igorfedorov.newsfeedapp.feature.main_screen.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.igorfedorov.newsfeedapp.R
import me.igorfedorov.newsfeedapp.common.exception.CustomError
import me.igorfedorov.newsfeedapp.common.setAdapterAndCleanupOnDetachFromWindow
import me.igorfedorov.newsfeedapp.databinding.FragmentMainScreenBinding
import me.igorfedorov.newsfeedapp.feature.main_screen.di.MAIN_SCREEN_VIEW_MODEL
import me.igorfedorov.newsfeedapp.feature.main_screen.domain.model.Article
import me.igorfedorov.newsfeedapp.feature.main_screen.ui.adapter.ArticlesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class MainScreenFragment : Fragment(R.layout.fragment_main_screen) {

    private val viewModel: MainScreenViewModel by viewModel(qualifier = named(MAIN_SCREEN_VIEW_MODEL))

    private val binding: FragmentMainScreenBinding by viewBinding(createMethod = CreateMethod.INFLATE)

    private var articlesAdapter: ArticlesAdapter? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()

        observeViewModel()

    }

    private fun initAdapter() {
        articlesAdapter = ArticlesAdapter()
        binding.articlesRecyclerView.apply {
            articlesAdapter?.let { setAdapterAndCleanupOnDetachFromWindow(it) }
            layoutManager = LinearLayoutManager(requireContext())
        }
        articlesAdapter?.items = viewModel.articles.value
    }

    private fun observeViewModel() {
        observeSuccess()
        observeFailure()
        observeIsFetching()
    }

    private fun observeSuccess() {
        viewModel.articles.observe(viewLifecycleOwner, ::updateAdapter)
    }

    private fun observeFailure() {
        viewModel.failure.observe(viewLifecycleOwner, ::updateFailureText)
    }

    private fun observeIsFetching() {
        viewModel.isFetching.observe(viewLifecycleOwner, ::updateProgressBar)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateAdapter(articles: List<Article>?) {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            articlesAdapter?.items = articles
            articlesAdapter?.notifyDataSetChanged()
        }
    }

    private fun updateFailureText(customError: CustomError?) {
        when (customError) {
            is CustomError.ServerError -> {
            }
            is CustomError.NetworkConnection -> {
            }
            else -> {
            }
        }
    }

    private fun updateProgressBar(isFetching: Boolean) {
        binding.progressBar.isVisible = isFetching
    }

}