package me.igorfedorov.kinonline.feature.movies_screen.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import me.igorfedorov.kinonline.R
import me.igorfedorov.kinonline.databinding.FragmentMoviesListBinding
import me.igorfedorov.kinonline.feature.movies_screen.ui.adapter.MoviesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesListFragment : Fragment(R.layout.fragment_movies_list) {

    private val binding: FragmentMoviesListBinding by viewBinding(FragmentMoviesListBinding::bind)

    private val viewModel: MoviesListViewModel by viewModel()

    private val moviesAdapter: MoviesAdapter by lazy {
        MoviesAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()

        viewModel.viewState.observe(viewLifecycleOwner, ::render)
    }

    private fun initAdapter() {
        binding.moviesListRecyclerView.apply {
            adapter = moviesAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
        moviesAdapter.items = viewModel.viewState.value?.movies
    }

    private fun render(viewState: ViewState) {
        moviesAdapter.items = viewState.movies
        moviesAdapter.notifyDataSetChanged()
        binding.progressBar.isVisible = viewState.isLoading
    }

}