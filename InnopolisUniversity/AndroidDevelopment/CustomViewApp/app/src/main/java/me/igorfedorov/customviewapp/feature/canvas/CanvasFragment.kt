package me.igorfedorov.customviewapp.feature.canvas

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import me.igorfedorov.customviewapp.R
import me.igorfedorov.customviewapp.ToolsLayout
import org.koin.androidx.viewmodel.ext.android.viewModel
import me.igorfedorov.customviewapp.base.canvas_state.LINE as ENUM_LINE
import me.igorfedorov.customviewapp.base.canvas_state.SIZE as ENUM_SIZE

class CanvasFragment : Fragment(R.layout.fragment_canvas) {

    companion object {

        private const val PALETTE = 0
        private const val SIZE = 1
        private const val LINE = 2

        fun newInstance() = CanvasFragment()
    }

    private val viewModel: CanvasFragmentViewModel by viewModel()

    private val toolsLayouts: List<ToolsLayout> by lazy {
        listOf(
            requireActivity().findViewById(R.id.palette),
            requireActivity().findViewById(R.id.size),
            requireActivity().findViewById(R.id.line)
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar()

        toolsLayouts[PALETTE].setOnClickListener {
            viewModel.processUiEvent(UIEvent.OnColorClicked(it))
        }
        toolsLayouts[SIZE].setOnClickListener {
            viewModel.processUiEvent(UIEvent.OnSizeClicked(ENUM_SIZE.values()[it]))
        }
        toolsLayouts[LINE].setOnClickListener {
            viewModel.processUiEvent(UIEvent.OnLineClicked(ENUM_LINE.values()[it]))
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
        toolsLayouts[PALETTE].isGone = !viewState.isToolsVisible
        toolsLayouts[PALETTE].render(viewState.colors)
        toolsLayouts[SIZE].isGone = !viewState.isToolsVisible
        toolsLayouts[SIZE].render(viewState.sizes)
        toolsLayouts[LINE].isGone = !viewState.isToolsVisible
        toolsLayouts[LINE].render(viewState.lines)
        requireActivity().findViewById<DrawView>(R.id.drawView).render(viewState.canvasViewState)
    }

}