package me.igorfedorov.newsfeedapp.feature.main_screen.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.igorfedorov.newsfeedapp.R
import me.igorfedorov.newsfeedapp.common.autoCleared
import me.igorfedorov.newsfeedapp.common.exception.Failure
import me.igorfedorov.newsfeedapp.databinding.FragmentMainScreenBinding
import me.igorfedorov.newsfeedapp.feature.main_screen.di.MAIN_SCREEN_VIEW_MODEL
import me.igorfedorov.newsfeedapp.feature.main_screen.domain.model.Article
import me.igorfedorov.newsfeedapp.feature.main_screen.ui.adapter.ArticleAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class MainScreenFragment : Fragment(R.layout.fragment_main_screen) {

    private val viewModel: MainScreenViewModel by viewModel(qualifier = named(MAIN_SCREEN_VIEW_MODEL))

    private var _binding: FragmentMainScreenBinding? = null
    private val binding
        get() = _binding ?: throw IllegalStateException("Cannot access binding")

    private var articleAdapter: ArticleAdapter by autoCleared()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()

        observeViewModel()

    }

    private fun initAdapter() {
        articleAdapter = ArticleAdapter()
        binding.articlesRecyclerView.apply {
            adapter = articleAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        articleAdapter.items = viewModel.articles.value
    }

    private fun observeViewModel() {
        observeSuccess()
        observeFailure()
    }

    private fun observeSuccess() {
        viewModel.articles.observe(viewLifecycleOwner, ::updateAdapter)
    }

    private fun observeFailure() {
        viewModel.failure.observe(viewLifecycleOwner, ::updateFailureText)
    }

    private fun updateFailureText(failure: Failure?) {
        when (failure) {
            is Failure.ServerError -> {
            }
            is Failure.NetworkConnection -> {
            }
            else -> {
            }
        }
    }

    //    @SuppressLint("NotifyDataSetChanged")
    private fun updateAdapter(articles: List<Article>?) {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            articleAdapter.items = articles
            articleAdapter.notifyDataSetChanged()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}