package me.igorfedorov.newsfeedapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import me.igorfedorov.newsfeedapp.databinding.FragmentBottomNavigationBinding

class BottomNavigationFragment : Fragment(R.layout.fragment_bottom_navigation) {

    private var _binding: FragmentBottomNavigationBinding? = null
    private val binding
        get() = _binding ?: throw IllegalStateException("cannot access binding")

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBottomNavigationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.news_feed_menu_item -> {
                    when (menuItem.isChecked) {
                        false -> {
                            menuItem.isEnabled = true
                            binding.bottomNavigationFragmentContainerView.findNavController()
                                .navigate(R.id.news_feed_nav_graph)
                            true
                        }
                        true -> {
                            menuItem.isEnabled = false
                            false
                        }
                    }
                }
                R.id.news_bookmarks_menu_item -> {
                    false
                }
                R.id.news_search_menu_item -> {
                    false
                }
                else -> throw IllegalStateException("Unexpected menu item")
            }
        }
    }
}