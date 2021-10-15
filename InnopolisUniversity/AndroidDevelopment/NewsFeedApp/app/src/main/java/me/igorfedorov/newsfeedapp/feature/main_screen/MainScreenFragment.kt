package me.igorfedorov.newsfeedapp.feature.main_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import me.igorfedorov.newsfeedapp.R
import me.igorfedorov.newsfeedapp.databinding.FragmentMainScreenBinding
import me.igorfedorov.newsfeedapp.feature.main_screen.di.MAIN_SCREEN_VIEW_MODEL
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class MainScreenFragment : Fragment(R.layout.fragment_main_screen) {

    private val viewModel: MainScreenViewModel by viewModel(qualifier = named(MAIN_SCREEN_VIEW_MODEL))

    private var _binding: FragmentMainScreenBinding? = null
    private val binding
        get() = _binding ?: throw IllegalStateException("Cannot access binding")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

}