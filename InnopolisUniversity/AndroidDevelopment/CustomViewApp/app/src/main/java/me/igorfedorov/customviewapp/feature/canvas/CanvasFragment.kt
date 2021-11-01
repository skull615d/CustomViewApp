package me.igorfedorov.customviewapp.feature.canvas

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import me.igorfedorov.customviewapp.R
import me.igorfedorov.customviewapp.ToolsLayout
import org.koin.androidx.viewmodel.ext.android.viewModel

class CanvasFragment : Fragment(R.layout.fragment_canvas) {

    companion object {

        private const val PALETTE = 0

        fun newInstance() = CanvasFragment()
    }

    private val viewModel: CanvasFragmentViewModel by viewModel()

    private val toolsLayouts: List<ToolsLayout> by lazy {
        listOf(requireActivity().findViewById(R.id.palette))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar()

        toolsLayouts[PALETTE].setOnClickListener {
            viewModel.processUiEvent(UIEvent.OnColorClicked(it))
        }
        viewModel.viewState.observe(viewLifecycleOwner, ::render)
    }

    private fun initToolbar() {
        requireActivity().findViewById<Toolbar>(R.id.toolbar).setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.show_palette -> {
                    viewModel.processUiEvent(UIEvent.OnShowPaletteClicked)
                    true
                }
                else -> false
            }
        }
    }

    private fun render(viewState: ViewState) {
        toolsLayouts[PALETTE].isGone = !viewState.isPaletteVisible
        toolsLayouts[PALETTE].render(viewState.colors)
        requireActivity().findViewById<DrawView>(R.id.drawView).render(viewState.canvasViewState)
    }

}