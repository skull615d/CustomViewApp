package me.igorfedorov.customviewapp.feature.canvas

import android.Manifest
import android.content.ContentValues
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.view.drawToBitmap
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import me.igorfedorov.customviewapp.R
import me.igorfedorov.customviewapp.ToolsLayout
import me.igorfedorov.customviewapp.base.utils.setThrottledClickListener
import me.igorfedorov.customviewapp.databinding.FragmentCanvasBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import me.igorfedorov.customviewapp.base.canvas_state.LINE as ENUM_LINE
import me.igorfedorov.customviewapp.base.canvas_state.SIZE as ENUM_SIZE
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts

import androidx.core.content.ContextCompat
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream


class CanvasFragment : Fragment(R.layout.fragment_canvas) {

    companion object {

        private const val PALETTE = 0
        private const val SIZE = 1
        private const val LINE = 2
        const val FOLDER_NAME = "MasterPict"

        fun newInstance() = CanvasFragment()
    }

    private val viewModel: CanvasFragmentViewModel by viewModel()

    private val binding: FragmentCanvasBinding by viewBinding()

    private val toolsLayouts: List<ToolsLayout> by lazy {
        listOf(
            requireActivity().findViewById(R.id.palette),
            requireActivity().findViewById(R.id.size),
            requireActivity().findViewById(R.id.line)
        )
    }
    private val launcher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if(isGranted){
            saveImageToFile()
        }else{
            //TODO
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar()

        setOnToolsClickListeners()

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

    private fun setOnToolsClickListeners() {
        binding.apply {
            palettePickerImageView.setThrottledClickListener {
                viewModel.processUiEvent(UIEvent.OnPaletteToolsClicked)
            }
            sizePickerImageView.setThrottledClickListener {
                viewModel.processUiEvent(UIEvent.OnSizeToolsClicked)
            }
            linePickerImageView.setThrottledClickListener {
                viewModel.processUiEvent(UIEvent.OnLineToolsClicked)
            }
        }
    }

    private fun initToolbar() {
        requireActivity().findViewById<Toolbar>(R.id.toolbar).setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.show_palette -> {
                    viewModel.processUiEvent(UIEvent.OnShowTools)
                    true
                }
                R.id.delete_drawing -> {
                    requireActivity().findViewById<DrawView>(R.id.drawView).clear()
                    true
                }
                R.id.save -> {
                    val permissionResult = ContextCompat.checkSelfPermission(
                        requireContext(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )
                    if (permissionResult == PackageManager.PERMISSION_GRANTED) {
                        saveImageToFile()
                    } else {
                        launcher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    }
                    true
                }
                else -> false
            }
        }
    }

    private fun saveImageToFile() {
        val bitmap = requireActivity().findViewById<DrawView>(R.id.drawView).drawToBitmap()
        if (android.os.Build.VERSION.SDK_INT >= 29) {
            val values = contentValues()
            values.put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/" + FOLDER_NAME)
            values.put(MediaStore.Images.Media.IS_PENDING, true)
            // RELATIVE_PATH and IS_PENDING are introduced in API 29.

            val uri: Uri? = requireContext().contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
            if (uri != null) {
                saveImageToStream(bitmap, requireContext().contentResolver.openOutputStream(uri))
                values.put(MediaStore.Images.Media.IS_PENDING, false)
                requireContext().contentResolver.update(uri, values, null, null)
            }
        } else {
            val directory = File(Environment.getExternalStorageDirectory().toString() + File.separator + FOLDER_NAME)
            // getExternalStorageDirectory is deprecated in API 29

            if (!directory.exists()) {
                directory.mkdirs()
            }
            val fileName = System.currentTimeMillis().toString() + ".png"
            val file = File(directory, fileName)
            saveImageToStream(bitmap, FileOutputStream(file))
            val values = contentValues()
            values.put(MediaStore.Images.Media.DATA, file.absolutePath)
            // .DATA is deprecated in API 29
            requireContext().contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        }
    }

    private fun contentValues() : ContentValues {
        val values = ContentValues()
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/png")
        values.put(MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis() / 1000)
        values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis())
        return values
    }

    private fun saveImageToStream(bitmap: Bitmap, outputStream: OutputStream?) {
        if (outputStream != null) {
            try {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                outputStream.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun render(viewState: ViewState) {

        renderPalettePickerImageView(viewState)
        renderLinePickerImageView(viewState)
        renderSizePickerImageView(viewState)

        toolsLayouts[PALETTE].isGone = !viewState.isPaletteToolsVisible
        toolsLayouts[PALETTE].render(viewState.colors)
        toolsLayouts[SIZE].isGone = !viewState.isSizeToolsVisible
        toolsLayouts[SIZE].render(viewState.sizes)
        toolsLayouts[LINE].isGone = !viewState.isLineToolsVisible
        toolsLayouts[LINE].render(viewState.lines)
        requireActivity().findViewById<DrawView>(R.id.drawView).render(viewState.canvasViewState)
    }

    private fun renderSizePickerImageView(viewState: ViewState) {
        binding.sizePickerImageView.isGone = !viewState.isToolsVisible
        binding.sizePickerImageView.setImageResource(
            when (viewState.canvasViewState.size) {
                ENUM_SIZE.SMALL -> {
                    R.drawable.ic_size_small
                }
                ENUM_SIZE.MEDIUM -> {
                    R.drawable.ic_size_medium
                }
                ENUM_SIZE.LARGE -> {
                    R.drawable.ic_size_large
                }
            }
        )
    }

    private fun renderLinePickerImageView(viewState: ViewState) {
        binding.linePickerImageView.isGone = !viewState.isToolsVisible
    }

    private fun renderPalettePickerImageView(viewState: ViewState) {
        binding.palettePickerImageView.apply {
            isGone = !viewState.isToolsVisible
            setBackgroundColor(
                resources.getColor(viewState.canvasViewState.color.value, null)
            )
        }
    }
}